package com.school.service.contracts;

import com.school.database.entity.Client;
import com.school.database.entity.Contract;
import com.school.database.entity.Options;
import com.school.dto.ClientDto;
import com.school.dto.ContractDto;

import java.util.List;

public interface ContractService {

    List<Contract> getAll();

    List<Contract> getAllContractsOfClient(int clientId);

    boolean checkOptionsComboToRight(List<Options> chosenOptions);

    void lock(ContractDto contractDto);

    void unlock(ContractDto contractDto);

    double countPricePerMonth(ContractDto contractDto);

    boolean optionsAlreadyConnectedToContract(int id, List<Options> connectedOptions);

    void save(ContractDto contractDto);

    void update(ContractDto contractDto);

    Contract get(int id);

    Contract getByPhoneNumber(String phoneNumber);

    void delete(int id);
}
