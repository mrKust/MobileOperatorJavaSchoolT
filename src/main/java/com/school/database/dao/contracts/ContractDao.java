package com.school.database.dao.contracts;

import com.school.database.entity.Contract;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * This class give us list of operations with Contract entity class
 */
public interface ContractDao {

    /**
     * This method returns contract
     * @param id id of searched contract
     * @return contract with id
     */
    @Transactional
    Contract get(int id);

    /**
     * This method returns all contracts of one client
     * @param clientEmail email address of client who contracts we want to get
     * @return list of all contracts of one client
     */
    @Transactional
    List<Contract> getAllContractsOfClient(String clientEmail);

    /**
     * This method returns contract which was signed for searching phone number
     * @param phoneNumber phone number which locked for contract
     * @return contract with inputted phone number
     */
    @Transactional
    Contract getByPhoneNumber(String phoneNumber);

    /**
     * This method returns all contracts in system
     * @return list of contracts in system
     */
    @Transactional
    List<Contract> getAll();

    /**
     * This method returns list of contracts which will be shown on one page
     * @param pageSize number of records on one page
     * @param sortColumn filed which will be used as field to compare contracts
     * @param pageNumber number of page where contracts will be shown
     * @return list of contracts for show on page
     */
    @Transactional
    List<Contract> getPageOfContracts(int pageSize, String sortColumn, int pageNumber);

    /**
     * Method counts number of pages on which records could be separated with this size of page
     * @param sizeOfPage number of record on one page
     * @return number of pages
     */
    @Transactional
    int getNumberOfPages(int sizeOfPage);

    /**
     * This method returns list of client's contracts which will be shown on one page
     * @param pageSize number of records on one page
     * @param sortColumn filed which will be used as field to compare contracts
     * @param pageNumber number of page where contracts will be shown
     * @param clientEmail email address of client which contract we are looking for
     * @return list of contracts for show on page
     */
    @Transactional
    List<Contract> getPageOfClientContracts(int pageSize, String sortColumn, int pageNumber, String clientEmail);

    /**
     *
     * Method counts number of pages on which records could be separated with this size of page
     * @param sizeOfPage number of record on one page
     * @param clientEmail email address of client which contract we are looking for
     * @return number of pages
     */
    @Transactional
    int getNumberOfClientContractPages(int sizeOfPage, String clientEmail);

    /**
     * Method saves or update contract
     * @param contract contract which need to be saved or updated
     */
    @Transactional
    void save(Contract contract);

    /**
     * Method deletes contract
     * @param id id of contract which should be deleted
     */
    @Transactional
    void delete(int id);

}
