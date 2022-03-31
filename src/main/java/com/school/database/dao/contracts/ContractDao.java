package com.school.database.dao.contracts;

import com.school.database.entity.Client;
import com.school.database.entity.Contract;
import com.school.database.entity.Tariff;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ContractDao {

    @Transactional
    Contract get(int id);

    @Transactional
    List<Contract> getAllContractsOfClient(String clientEmail);

    @Transactional
    Contract getByPhoneNumber(String phoneNumber);

    @Transactional
    List<Contract> getAll();

    @Transactional
    List<Contract> getPageOfContracts(int pageSize, String sortColumn, int pageNumber);

    @Transactional
    int getNumberOfPages(int sizeOfPage);

    @Transactional
    List<Contract> getPageOfClientContracts(int pageSize, String sortColumn, int pageNumber, String clientEmail);

    @Transactional
    int getNumberOfClientContractPages(int sizeOfPage, String clientEmail);

    @Transactional
    void save(Contract contract);

    @Transactional
    void delete(int id);

}
