package pro.belbix.ethparser.controllers;

import static pro.belbix.ethparser.service.AbiProviderService.ETH_NETWORK;
import static pro.belbix.ethparser.utils.CommonUtils.parseLong;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
import pro.belbix.ethparser.dto.v0.HardWorkDTO;
import pro.belbix.ethparser.dto.v0.HarvestDTO;
import pro.belbix.ethparser.dto.v0.RewardDTO;
import pro.belbix.ethparser.model.TvlHistory;
import pro.belbix.ethparser.repositories.v0.HardWorkRepository;
import pro.belbix.ethparser.repositories.v0.HarvestRepository;
import pro.belbix.ethparser.repositories.v0.RewardsRepository;
import pro.belbix.ethparser.service.HarvestTvlDBService;
import pro.belbix.ethparser.web3.contracts.ContractType;
import pro.belbix.ethparser.web3.contracts.db.ContractDbService;


@RestController
@Log4j2
@RequestMapping(value = "/csv")
public class CSVController {

    private static final int RETURN_LIMIT = 1000000;

    private final HarvestRepository harvestRepository;
    private final RewardsRepository rewardsRepository;
    private final HardWorkRepository hardWorkRepository;
    private final HarvestTvlDBService harvestTvlDBService;
    private final ContractDbService contractDbService;

    public CSVController(HarvestRepository harvestRepository, RewardsRepository rewardsRepository,
        HardWorkRepository hardWorkRepository, HarvestTvlDBService harvestTvlDBService,
        ContractDbService contractDbService) {
        this.harvestRepository = harvestRepository;
        this.rewardsRepository = rewardsRepository;
        this.hardWorkRepository = hardWorkRepository;
        this.harvestTvlDBService = harvestTvlDBService;
        this.contractDbService = contractDbService;
    }

    @RequestMapping(
        value = "/transactions/history/harvest/{name}",
        method = RequestMethod.GET
    )
    public void harvestHistoryDataForVault(
        HttpServletResponse response,
        @PathVariable("name") String address,
        @RequestParam(value = "start", required = false) String start,
        @RequestParam(value = "end", required = false) String end,
        @RequestParam(value = "network", required = false, defaultValue = ETH_NETWORK) String network
    ) {
        try {
            if (!address.startsWith("0x")) {
                address = contractDbService.getAddressByName(address, ContractType.VAULT, network)
                    .orElseThrow();
            }
            List<HarvestDTO> transactions = harvestRepository
                .findAllByVaultOrderByBlockDate(
                    address, parseLong(start, 0),
                    parseLong(end, Long.MAX_VALUE), network);
            writeCSV(response, transactions, HarvestDTO.class);
        } catch (Exception e) {
            log.error("Error while converting to CSV Harvest", e);
        }
    }

    @RequestMapping(
        value = "/transactions/history/reward/{name}",
        method = RequestMethod.GET
    )
    public void rewardHistoryDataForVault(
        HttpServletResponse response,
        @PathVariable("name") String address,
        @RequestParam(value = "start", required = false) String start,
        @RequestParam(value = "end", required = false) String end,
        @RequestParam(value = "network", required = false, defaultValue = ETH_NETWORK) String network
    ) {
        try {
            if (!address.startsWith("0x")) {
                address = contractDbService.getAddressByName(address, ContractType.VAULT, network)
                    .orElseThrow();
            }
            List<RewardDTO> transactions = rewardsRepository
                .getAllByVaultOrderByBlockDate(address, parseLong(start, 0),
                    parseLong(end, Long.MAX_VALUE), network);
            writeCSV(response, transactions, RewardDTO.class);
        } catch (Exception e) {
            log.error("Error while converting to CSV Rewards", e);
        }
    }

    @RequestMapping(
        value = "/transactions/history/hardwork/{name}",
        method = RequestMethod.GET
    )
    public void hardworkHistoryDataForVault(
        HttpServletResponse response,
        @PathVariable("name") String name,
        @RequestParam(value = "start", required = false) String start,
        @RequestParam(value = "end", required = false) String end,
        @RequestParam(value = "network", required = false, defaultValue = ETH_NETWORK) String network
    ) {
        try {
            if (!name.startsWith("0x")) {
                name = contractDbService.getAddressByName(name, ContractType.VAULT, network)
                    .orElseThrow();
            }
            List<HardWorkDTO> transactions = hardWorkRepository
                .findAllByVaultOrderByBlockDate(
                    name, network,
                    parseLong(start, 0),
                    parseLong(end, Long.MAX_VALUE));
            writeCSV(response, transactions, HardWorkDTO.class);
        } catch (Exception e) {
            log.error("Error while converting to CSV Rewards", e);
        }
    }

    @RequestMapping(
        value = "/transactions/history/tvl/{name}",
        method = RequestMethod.GET
    )
    public void tvlHistoryDataForVault(
        HttpServletResponse response,
        @PathVariable("name") String address,
        @RequestParam(value = "start", required = false) String start,
        @RequestParam(value = "end", required = false) String end,
        @RequestParam(value = "network", required = false, defaultValue = ETH_NETWORK) String network
    ) {
        try {
            if (!address.startsWith("0x")) {
                address = contractDbService.getAddressByName(address, ContractType.VAULT, network)
                    .orElseThrow();
            }
            List<TvlHistory> transactions = harvestTvlDBService
                .fetchTvlByVault(address, parseLong(start, 0), parseLong(end, Long.MAX_VALUE), network);
            writeCSV(response, transactions, TvlHistory.class);
        } catch (Exception e) {
            log.error("Error while converting to CSV Rewards", e);
        }
    }

    private <T> void writeCSV(
        HttpServletResponse response,
        List<T> transactions,
        Class<T> clazz
    ) throws IOException {
        response.setContentType("text/csv");
        String csvFileName = clazz.getSimpleName() + "_" + System.currentTimeMillis() + ".csv";
        String headerValue = String.format("attachment; filename=\"%s\"", csvFileName);
        response.setHeader("Content-Disposition", headerValue);

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),
            CsvPreference.STANDARD_PREFERENCE);
        String[] headers = collectFields(clazz);
        csvWriter.writeHeader(headers);

        int listSize = transactions.size();
        int returnLimit = listSize < RETURN_LIMIT ? 0 : listSize - RETURN_LIMIT;

        for (Object harvest : transactions.subList(returnLimit, listSize)) {
            csvWriter.write(harvest, headers);
        }
        response.getWriter().flush();
        csvWriter.close();
    }

    static String[] collectFields(Class<?> clazz) {
        List<String> fields = new ArrayList<>();
        for (Field field : clazz.getDeclaredFields()) {
            fields.add(field.getName());
        }
        return fields.toArray(new String[0]);
    }
}
