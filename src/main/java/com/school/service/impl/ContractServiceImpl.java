package com.school.service.impl;

import com.school.customException.ServiceLayerException;
import com.school.database.dao.contracts.ContractDao;
import com.school.database.entity.Contract;
import com.school.database.entity.Options;
import com.school.dto.ContractDto;
import com.school.service.contracts.ClientService;
import com.school.service.contracts.ContractService;
import com.school.service.contracts.OptionsService;
import com.school.service.contracts.TariffService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ContractServiceImpl implements ContractService {

    private final ContractDao contractDao;
    private final TariffService tariffService;
    private final ClientService clientService;
    private final OptionsService optionsService;

    ContractServiceImpl(ContractDao contractDao, TariffService tariffService, ClientService clientService,
                        OptionsService optionsService) {
        this.contractDao = contractDao;
        this.tariffService = tariffService;
        this.clientService = clientService;
        this.optionsService = optionsService;
    }

    @Override
    public List<Contract> getAll() {
        return contractDao.getAll();
    }

    @Override
    public boolean checkOptionsComboToRight(List<Options> chosenOptions) {
        Map<Integer, Integer> optionTypeCount = new HashMap<>();
        for (Options tmp : chosenOptions) {
            Integer count = optionTypeCount.get(tmp.getOptionType().getId());
            optionTypeCount.put(tmp.getOptionType().getId(), count == null ? 1 : count + 1);

        }

        for (Map.Entry<Integer, Integer> entry : optionTypeCount.entrySet()) {
            if (entry.getValue() > 1 )
                return false;
        }


        return true;
    }

    @Override
    public void save(ContractDto contractDto) {
        Contract contract = contractDto.getContract();

        if (contractDto.getStringsTariff() != null) {
            int tariffId = Integer.parseInt(contractDto.getStringsTariff()[0]);
            contract.setContractTariff(tariffService.get(tariffId));
        } else {
            contract.setContractTariff(tariffService.get(contract.getContractTariff().getId()));
        }

        if (contractDto.getStringsClients() != null) {
            int clientId = Integer.parseInt(contractDto.getStringsClients()[0]);
            contract.setContractClient(clientService.get(clientId));
        }

        List<Options> chosenOptions = new ArrayList<>();

        if (contractDto.getStringsOptions() != null) {
             chosenOptions = optionsService.getOptionsFromChosenList(contractDto.getChosenOptionsList());
        }

        if (!checkOptionsComboToRight(chosenOptions)) {
            throw new ServiceLayerException("Chosen combination of options is forbidden. You must choose only one option from one category");
        }

        contract.setConnectedOptions(chosenOptions);

        contractDao.save(contract);
    }

    @Override
    public Contract get(int id) {
        Contract contract = contractDao.get(id);

        if (contract == null) {
            throw new ServiceLayerException("User try to get his contract, but contract " +
                    "wasn't created");
        }
        return contract;
    }

    @Override
    public void delete(int id) {
        contractDao.delete(id);
    }
}
