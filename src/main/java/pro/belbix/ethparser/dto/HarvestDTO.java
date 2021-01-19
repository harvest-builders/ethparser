package pro.belbix.ethparser.dto;

import java.math.BigInteger;
import java.time.Instant;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Data;

@Entity
@Table(name = "harvest_tx", indexes = {
    @Index(name = "idx_harvest_tx", columnList = "blockDate"),
    @Index(name = "idx_harvest_tx2", columnList = "methodName, vault")
})
@Cacheable(false)
@Data
public class HarvestDTO implements DtoI {

    @Id
    private String id;
    private String hash;
    private Long block;
    private boolean confirmed = false;
    private Long blockDate;
    private String methodName;
    private String owner;
    private Double amount;
    private Double amountIn;
    private String vault;
    private Double lastGas;
    private Double lastTvl;
    private Double lastUsdTvl;
    private Integer ownerCount;
    private Double sharePrice;
    private Long usdAmount;
    @Column(columnDefinition = "TEXT") //todo create price entity
    private String prices;
    private String lpStat;
    private Double lastAllUsdTvl;
    private Double ownerBalance;
    private Double ownerBalanceUsd;
    private Integer allOwnersCount;
    private Integer allPoolsOwnersCount;
    private boolean migrated = false;
    @Transient
    private HarvestDTO migration;


    public String print() {
        return Instant.ofEpochSecond(blockDate) + " "
            + methodName + " "
            + "usd: " + usdAmount + " "
            + "f: " + amount + " "
            + vault
            + " " + hash
            + " " + String.format("%f.0", lastUsdTvl);
    }
}
