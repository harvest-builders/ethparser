package pro.belbix.ethparser.repositories;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pro.belbix.ethparser.dto.HarvestDTO;

public interface HarvestRepository extends JpaRepository<HarvestDTO, String> {

    List<HarvestDTO> findAllByOrderByBlockDate();

    List<HarvestDTO> findAllByBlockDateGreaterThanOrderByBlockDate(long blockDate);

    @Query(nativeQuery = true, value = ""
        + "select (coalesce(deposit.d, 0) - coalesce(withdraw.w, 0)) result from (  "
        + "                  (select SUM(amount) d  "
        + "                  from harvest_tx t  "
        + "                  where t.method_name = 'Deposit'  "
        + "                      and t.vault = :vault  "
        + "                      and t.block_date <= :block_date) deposit,  "
        + "                  (select SUM(amount) w  "
        + "                  from harvest_tx t  "
        + "                  where t.method_name = 'Withdraw'  "
        + "                      and t.vault = :vault  "
        + "                      and t.block_date <= :block_date) withdraw  "
        + "              )")
    Double fetchTVL(@Param("vault") String vault, @Param("block_date") long blockDate);

    @Query(nativeQuery = true, value = "select * from harvest_tx "
        + "where vault = :vault and block_date <= :block_date order by block_date desc limit 0,1")
    HarvestDTO fetchLastByVaultAndDate(@Param("vault") String vault, @Param("block_date") long blockDate);

    @Query(nativeQuery = true, value = "select * from harvest_tx "
        + "where vault = :vault and last_usd_tvl != 0 and block_date <= :block_date order by block_date desc limit 0,1")
    HarvestDTO fetchLastByVaultAndDateNotZero(@Param("vault") String vault, @Param("block_date") long blockDate);

    @Query(nativeQuery = true, value = ""
        + "select count(*) from ("
        + "select t.owner from harvest_tx t where t.vault = :vault and t.block_date <= :block_date group by t.owner) tt"
    )
    Integer fetchOwnerCount(@Param("vault") String vault, @Param("block_date") long blockDate);

    @Query(nativeQuery = true, value = ""
        + "select count(t.owner) from ( "
        + "select deposit.owner owner, deposit.d_amnt, withdraw.w_amnt, (deposit.d_amnt - withdraw.w_amnt) result from "
        + "    (select owner, sum(amount) d_amnt "
        + "     from harvest_tx "
        + "     where method_name = 'Deposit' "
        + "       and (vault = :vault or vault = :oldVault /*previous version*/) "
        + "       and block_date <= :block_date "
        + "     group by owner) deposit "
        + "        left join "
        + "    (select owner, sum(amount) w_amnt from harvest_tx "
        + "     where method_name = 'Withdraw' "
        + "       and (vault = :vault or vault = :oldVault /*previous version*/) "
        + "       and block_date <= :block_date "
        + "     group by owner) withdraw ON deposit.owner = withdraw.owner "
        + "    ) t "
        + "where t.result is null or t.result > 0")
    Integer fetchActualOwnerCount(@Param("vault") String vault,
                                  @Param("oldVault") String oldVault,
                                  @Param("block_date") long blockDate);

    HarvestDTO findFirstByOrderByBlockDesc();

    HarvestDTO findFirstByVaultAndBlockDateLessThanEqualAndIdNotOrderByBlockDateDesc(String vault, long date,
                                                                                     String id);

    @Query("select t.lastTvl from HarvestDTO t where t.vault = :vault and t.blockDate <= :from order by t.blockDate desc")
    List<Double> fetchTvlFrom(@Param("from") long from, @Param("vault") String vault, Pageable pageable);

    @Query("select t.lastUsdTvl from HarvestDTO t where t.vault = :vault and t.blockDate <= :from  order by t.blockDate desc")
    List<Double> fetchUsdTvlFrom(@Param("from") long from, @Param("vault") String vault, Pageable pageable);

    @Query("select max(t.blockDate) - min(t.blockDate) as period from HarvestDTO t where "
        + "t.vault = :vault and t.blockDate <= :to order by t.blockDate desc")
    List<Long> fetchPeriodOfWork(@Param("vault") String vault,
                                 @Param("to") long to,
                                 Pageable pageable);

    @Query("select t from HarvestDTO t where "
        + "t.owner = :owner and t.blockDate > :from and t.blockDate <= :to order by t.blockDate asc")
    List<HarvestDTO> fetchAllByOwner(@Param("owner") String owner, @Param("from") long from, @Param("to") long to);

}
