package com.school.service.impl;

import com.school.customException.ServiceLayerException;
import com.school.database.dao.contracts.ContractDao;
import com.school.database.entity.*;
import com.school.database.entity.Number;
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
    public List<Contract> getAllContractsOfClient(String clientEmail) {
        return contractDao.getAllContractsOfClient(clientEmail);
    }

    @Override
    public List<Contract> getPageOfContracts(ContractDto contractDto, Integer numberOfPage) {
        if (contractDto.getPageSize() == 0)
            contractDto.setPageSize(5);
        if (contractDto.getSortColumn() == null)
            contractDto.setSortColumn("phoneNumber");
        if (numberOfPage == null)
            numberOfPage = 1;
        return contractDao.getPageOfContracts(contractDto.getPageSize(), contractDto.getSortColumn(),
                numberOfPage);
    }

    @Override
    public List<Contract> getPageOfClientContracts(ContractDto contractDto, Integer numberOfPage,
                                                   String clientEmail) {
        if (contractDto.getPageSize() == 0)
            contractDto.setPageSize(5);
        if (contractDto.getSortColumn() == null)
            contractDto.setSortColumn("phoneNumber");
        if (numberOfPage == null)
            numberOfPage = 1;
        return contractDao.getPageOfClientContracts(contractDto.getPageSize(),
                contractDto.getSortColumn(), numberOfPage, clientEmail);
    }

    @Override
    public int getNumberOfPages(int sizeOfPage) {
        return contractDao.getNumberOfPages(sizeOfPage);
    }

    @Override
    public int getNumberOfClientContractPages(int sizeOfPage, String clientEmail) {
        return contractDao.getNumberOfClientContractPages(sizeOfPage, clientEmail);
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

        int tariffId = Integer.parseInt(contractDto.getStringsTariff()[0]);
        Tariff connectedTariff = tariffService.get(tariffId);
        contract.setContractTariff(connectedTariff);

        if (contractDto.getStringsClients() == null) {
            contract.setContractClient(clientService.get(contractDto.getId()));
        } else {
            int clientId = Integer.parseInt(contractDto.getStringsClients()[0]);
            contract.setContractClient(clientService.get(clientId));
        }

        contract.setPriceForContractPerMonth(connectedTariff.getPrice());

        Number number = numberService.getByPhoneNumber(contract.getPhoneNumber());
        if (number.isAvailableToConnectStatus() != false) {
            number.setAvailableToConnectStatus(false);
            numberService.update(number);
        } else
            throw new ServiceLayerException("This number already connected to different contract");

        contractDao.save(contract);
    }

    @Override
    public void update(ContractDto contractDto) {
        Contract contract = get(contractDto.getContract().getId());

        int tariffId = Integer.parseInt(contractDto.getStringsTariff()[0]);

        if (tariffId != contract.getContractTariff().getId()) {
            contract.setContractTariff(tariffService.get(tariffId));
        }

        List<Options> chosenOptions = new ArrayList<>();

        if (contractDto.getStringsOptions() != null) {
            chosenOptions = optionsService.getOptionsFromChosenList(contractDto.getChosenOptionsList());
        }

        if (!checkOptionsComboToRight(chosenOptions)) {
            throw new ServiceLayerException("Chosen combination of options is forbidden. You must choose only one option from one category");
        }

        contract.setConnectedOptions(chosenOptions);
        contract.setPriceForContractPerMonth(countPricePerMonth(contract));

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
    public double countPricePerMonth(Contract contract) {
        Client client = contract.getContractClient();
        List<Options> connectedOptions = get(contract.getId()).getConnectedOptions();
        double priceForMonth = contract.getContractTariff().getPrice();
        for (Options tmp : contract.getConnectedOptions()) {
            if (!optionsAlreadyConnectedToContract(tmp.getId(), connectedOptions)) {
                if (client.getMoneyBalance() > 0) {
                    client.setMoneyBalance(client.getMoneyBalance() - tmp.getCostToAdd());
                    priceForMonth += tmp.getPrice();
                } else {
                    throw new ServiceLayerException("You don't have money to connect option " +
                            tmp.getOptionsName()+ " " + tmp.getOptionType().getOptionType() +
                            ". Lack of funds is " + (contract.getContractClient()
                            .getMoneyBalance() - tmp.getCostToAdd()) );
                }
            } else priceForMonth += tmp.getPrice();
        }
        return priceForMonth;
    }

    @Override
    public boolean optionsAlreadyConnectedToContract(int id, List<Options> connectedOptions) {
        for (Options tmp: connectedOptions) {
            if (tmp.getId() == id)
                return true;
        }
        return false;
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
        Number number = numberService.getByPhoneNumber(contract.getPhoneNumber());
        number.setAvailableToConnectStatus(true);
        numberService.update(number);
        contractDao.delete(id);
    }
}
