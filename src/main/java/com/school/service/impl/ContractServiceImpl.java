package com.school.service.impl;

import com.school.customException.BusinessLogicException;
import com.school.customException.ServiceLayerException;
import com.school.database.dao.contracts.ContractDao;
import com.school.database.entity.Contract;
import com.school.database.entity.Options;
import com.school.dto.ContractDto;
import com.school.service.contracts.ClientService;
import com.school.service.contracts.ContractService;
import com.school.service.contracts.TariffService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {

    private final ContractDao contractDao;
    private final TariffService tariffService;
    private final ClientService clientService;

    ContractServiceImpl(ContractDao contractDao, TariffService tariffService, ClientService clientService) {
        this.contractDao = contractDao;
        this.tariffService = tariffService;
        this.clientService = clientService;
    }

    @Override
    public List<Contract> getAll() {
        return contractDao.getAll();
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

        List<Options> chosenOptions = contractDto.wrapStringsToConnectedOptions(contract.getContractTariff().getOptions());

        if (!contractDto.checkChosenOptionForCorrect(chosenOptions)) {
            throw new ServiceLayerException("Chosen combination of options is forbidden. You must choose only one option from one category");
        }

        if (contractDto.getStringsOptions() != null) {
            contract.setConnectedOptions(chosenOptions);
        } else if (contractDto.getOperationType().equals("update")) {
            contract.setConnectedOptions(contractDto.wrapStringsToConnectedOptions(chosenOptions));
        }

        contractDao.save(contract);
    }

    @Override
    public Contract get(int id) {
        return contractDao.get(id);
    }

    @Override
    public void delete(int id) {
        contractDao.delete(id);
    }
}
