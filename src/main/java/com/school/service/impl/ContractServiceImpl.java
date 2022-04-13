package com.school.service.impl;

import com.school.customException.ServiceLayerException;
import com.school.database.dao.contracts.ContractDao;
import com.school.database.entity.*;
import com.school.database.entity.Number;
import com.school.dto.ContractDto;
import com.school.service.contracts.*;
import org.springframework.stereotype.Service;

import java.util.*;

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

    /**
     * Described at {@link ContractService}
     * @return list of contracts in system
     */
    @Override
    public List<Contract> getAll() {
        return contractDao.getAll();
    }

    /**
     * Described at {@link ContractService}
     * @param clientEmail email address of client who contracts we want to get
     * @return list of all contracts of one client
     */
    @Override
    public List<Contract> getAllContractsOfClient(String clientEmail) {
        return contractDao.getAllContractsOfClient(clientEmail);
    }

    /**
     * Described at {@link ContractService}
     * @param contractDto contract data transfer object
     * @param numberOfPage number of page where contracts will be shown
     * @return list of contracts for show on page
     */
    @Override
    public List<Contract> getPageOfContracts(ContractDto contractDto, Integer numberOfPage) {
        List<Integer> sizeParams = new ArrayList<>(Arrays.asList(5, 10, 15));
        if ( (contractDto.getPageSize() == 0) || (!sizeParams.contains(contractDto.getPageSize())))
            contractDto.setPageSize(5);
        List<String> sortParams = new ArrayList<>(Arrays.asList("phoneNumber", "priceForContractPerMonth",
                "contractBlockStatus", "roleOfUserWhoBlockedContract"));
        if ( (contractDto.getSortColumn() == null) || (!sortParams.contains(contractDto.getSortColumn())))
            contractDto.setSortColumn("phoneNumber");
        if (numberOfPage == null)
            numberOfPage = 1;
        return contractDao.getPageOfContracts(contractDto.getPageSize(), contractDto.getSortColumn(),
                numberOfPage);
    }

    /**
     * Described at {@link ContractService}
     * @param contractDto contract data transfer object
     * @param numberOfPage number of page where contracts will be shown
     * @param clientEmail email address of client which contract we are looking for
     * @return list of contracts for show on page
     */
    @Override
    public List<Contract> getPageOfClientContracts(ContractDto contractDto, Integer numberOfPage,
                                                   String clientEmail) {
        List<Integer> sizeParams = new ArrayList<>(Arrays.asList(5, 10, 15));
        if ( (contractDto.getPageSize() == 0) || (!sizeParams.contains(contractDto.getPageSize())))
            contractDto.setPageSize(5);
        List<String> sortParams = new ArrayList<>(Arrays.asList("phoneNumber", "priceForContractPerMonth",
                "contractBlockStatus", "roleOfUserWhoBlockedContract"));
        if ( (contractDto.getSortColumn() == null) || (!sortParams.contains(contractDto.getSortColumn())))
            contractDto.setSortColumn("phoneNumber");
        if (numberOfPage == null)
            numberOfPage = 1;
        return contractDao.getPageOfClientContracts(contractDto.getPageSize(),
                contractDto.getSortColumn(), numberOfPage, clientEmail);
    }

    /**
     * Described at {@link ContractService}
     * @param sizeOfPage number of record on one page
     * @return number of pages
     */
    @Override
    public int getNumberOfPages(int sizeOfPage) {
        return contractDao.getNumberOfPages(sizeOfPage);
    }

    /**
     * Described at {@link ContractService}
     * @param sizeOfPage number of record on one page
     * @param clientEmail email address of client which contract we are looking for
     * @return number of pages
     */
    @Override
    public int getNumberOfClientContractPages(int sizeOfPage, String clientEmail) {
        return contractDao.getNumberOfClientContractPages(sizeOfPage, clientEmail);
    }

    /**
     * Described at {@link ContractService}
     * @param chosenOptions list of options which need to be checked
     * @return true if options could be connected to one contract, false otherwise
     */
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

    /**
     * Described at {@link ContractService}
     * @param contractDto contract data transfer object
     */
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

    /**
     * Described at {@link ContractService}
     * @param contractDto contract data transfer object
     */
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

    /**
     * Described at {@link ContractService}
     * @param contractDto contract data transfer object
     */
    @Override
    public void lock(ContractDto contractDto) {

        Contract contract = this.get(contractDto.getId());
        contract.setContractBlockStatus(true);
        contract.setRoleOfUserWhoBlockedContract(contractDto.getBlockedRole());

        contractDao.save(contract);
    }

    /**
     * Described at {@link ContractService}
     * @param contractDto contract data transfer object
     */
    @Override
    public void unlock(ContractDto contractDto) {

        Contract contract = this.get(contractDto.getId());

        contract.setContractBlockStatus(false);
        contract.setRoleOfUserWhoBlockedContract(null);

        contractDao.save(contract);
    }

    /**
     * Described at {@link ContractService}
     * @param contract contract for counting
     * @return payment for month
     */
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

    /**
     * Described at {@link ContractService}
     * @param id id of checked option
     * @param connectedOptions list of previously connected option
     * @return true if already been connected, false otherwise
     */
    @Override
    public boolean optionsAlreadyConnectedToContract(int id, List<Options> connectedOptions) {
        for (Options tmp: connectedOptions) {
            if (tmp.getId() == id)
                return true;
        }
        return false;
    }

    /**
     * Described at {@link ContractService}
     * @param id id of searched contract
     * @return contract with id
     */
    @Override
    public Contract get(int id) {
        Contract contract = contractDao.get(id);

        return contract;
    }

    /**
     * Described at {@link ContractService}
     * @param phoneNumber phone number which locked for contract
     * @return contract with inputted phone number
     */
    @Override
    public Contract getByPhoneNumber(String phoneNumber) {
        return contractDao.getByPhoneNumber(phoneNumber);
    }

    /**
     * Described at {@link ContractService}
     * @param id id of contract which should be deleted
     */
    @Override
    public void delete(int id) {
        Contract contract = get(id);
        Number number = numberService.getByPhoneNumber(contract.getPhoneNumber());
        number.setAvailableToConnectStatus(true);
        numberService.update(number);
        contractDao.delete(id);
    }
}
