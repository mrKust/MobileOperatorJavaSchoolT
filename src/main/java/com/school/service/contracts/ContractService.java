package com.school.service.contracts;

import com.school.database.entity.Client;
import com.school.database.entity.Contract;
import com.school.database.entity.Options;
import com.school.database.entity.Tariff;
import com.school.dto.ClientDto;
import com.school.dto.ContractDto;
import com.school.dto.TariffDto;

import java.util.List;

public interface ContractService {

    List<Contract> getAll();

    List<Contract> getAllContractsOfClient(String clientEmail);

    List<Contract> getPageOfContracts(ContractDto contractDto, Integer numberOfPage);

    List<Contract> getPageOfClientContracts(ContractDto contractDto, Integer numberOfPage, String clientEmail);

    int getNumberOfPages(int sizeOfPage);

    int getNumberOfClientContractPages(int sizeOfPage, String clientEmail);

    boolean checkOptionsComboToRight(List<Options> chosenOptions);

    void lock(ContractDto contractDto);

    void unlock(ContractDto contractDto);

    double countPricePerMonth(Contract contract, List<Options> beforeConnectedOptions);

    boolean optionsAlreadyConnectedToContract(int id, List<Options> connectedOptions);

    void save(ContractDto contractDto);

    void update(ContractDto contractDto);

    Contract get(int id);

    Contract getByPhoneNumber(String phoneNumber);

    void delete(int id);
}
