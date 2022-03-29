package com.school.service.impl;

import com.school.customException.ServiceLayerException;
import com.school.database.dao.contracts.ContractDao;
import com.school.database.entity.Client;
import com.school.database.entity.Contract;
import com.school.database.entity.Number;
import com.school.database.entity.Options;
import com.school.dto.ClientDto;
import com.school.dto.ContractDto;
import com.school.service.contracts.*;
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
    private final NumberService numberService;

    ContractServiceImpl(ContractDao contractDao, TariffService tariffService, ClientService clientService,
                        OptionsService optionsService, NumberService numberService) {
        this.contractDao = contractDao;
        this.tariffService = tariffService;
        this.clientService = clientService;
        this.optionsService = optionsService;
        this.numberService = numberService;
    }

    @Override
    public List<Contract> getAll() {
        return contractDao.getAll();
    }

    @Override
    public List<Contract> getAllContractsOfClient(int clientId) {
        return contractDao.getAllContractsOfClient(clientId);
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

        Number number = numberService.getByPhoneNumber(contract.getPhoneNumber());
        number.setAvailableToConnectStatus(false);
        numberService.update(number);

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
    public void update(ContractDto contractDto) {
        Contract contract = contractDto.getContract();
        contractDao.save(contract);
    }

    @Override
    public void lock(ContractDto contractDto) {

        Contract contract = this.get(contractDto.getId());
        contract.setContractBlockStatus(true);
        contract.setRoleOfUserWhoBlockedContract(contractDto.getBlockedRole());

        contractDao.save(contract);
    }

    @Override
    public void unlock(ContractDto contractDto) {

        Contract contract = this.get(contractDto.getId());

        contract.setContractBlockStatus(false);
        contract.setRoleOfUserWhoBlockedContract(null);

        contractDao.save(contract);
    }

    @Override
    public Contract get(int id) {
        Contract contract = contractDao.get(id);

        return contract;
    }

    @Override
    public Contract getByPhoneNumber(String phoneNumber) {
        return contractDao.getByPhoneNumber(phoneNumber);
    }

    @Override
    public void delete(int id) {
        Contract contract = get(id);
        numberService.getByPhoneNumber(contract.getPhoneNumber()).setAvailableToConnectStatus(true);
        contractDao.delete(id);
    }
}
