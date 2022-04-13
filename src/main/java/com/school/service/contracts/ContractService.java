package com.school.service.contracts;

import com.school.database.entity.Contract;
import com.school.database.entity.Options;
import com.school.dto.ContractDto;

import javax.transaction.Transactional;
import java.util.List;

/**
 * This service describe operations with contracts
 */
@Transactional
public interface ContractService {

    /**
     * This method returns all contracts in system
     * @return list of contracts in system
     */
    List<Contract> getAll();

    /**
     * This method returns all contracts of one client
     * @param clientEmail email address of client who contracts we want to get
     * @return list of all contracts of one client
     */
    List<Contract> getAllContractsOfClient(String clientEmail);

    /**
     * This method returns list of contracts which will be shown on one page
     * @param contractDto contract data transfer object
     * @param numberOfPage number of page where contracts will be shown
     * @return list of contracts for show on page
     */
    List<Contract> getPageOfContracts(ContractDto contractDto, Integer numberOfPage);

    /**
     * This method returns list of client's contracts which will be shown on one page
     * @param contractDto contract data transfer object
     * @param numberOfPage number of page where contracts will be shown
     * @param clientEmail email address of client which contract we are looking for
     * @return list of contracts for show on page
     */
    List<Contract> getPageOfClientContracts(ContractDto contractDto, Integer numberOfPage, String clientEmail);

    /**
     * Method counts number of pages on which records could be separated with this size of page
     * @param sizeOfPage number of record on one page
     * @return number of pages
     */
    int getNumberOfPages(int sizeOfPage);

    /**
     *
     * Method counts number of pages on which records could be separated with this size of page
     * @param sizeOfPage number of record on one page
     * @param clientEmail email address of client which contract we are looking for
     * @return number of pages
     */
    int getNumberOfClientContractPages(int sizeOfPage, String clientEmail);

    /**
     * Method checks if options could be connected to one contract
     * @param chosenOptions list of options which need to be checked
     * @return true if options could be connected to one contract, false otherwise
     */
    boolean checkOptionsComboToRight(List<Options> chosenOptions);

    /**
     * Method set contract in lock status (can't switch tariff and change options)
     * @param contractDto contract data transfer object
     */
    void lock(ContractDto contractDto);

    /**
     * Method set contract in unlock status (can switch tariff and change options)
     * @param contractDto contract data transfer object
     */
    void unlock(ContractDto contractDto);

    /**
     * Method count month payment for contract
     * @param contract contract for counting
     * @return payment for month
     */
    double countPricePerMonth(Contract contract);

    /**
     * Method check if option have been already connected to contract
     * @param id id of checked option
     * @param connectedOptions list of previously connected option
     * @return true if already been connected, false otherwise
     */
    boolean optionsAlreadyConnectedToContract(int id, List<Options> connectedOptions);

    /**
     * Method saves contract
     * @param contractDto contract data transfer object
     */
    void save(ContractDto contractDto);

    /**
     * Method updates contract
     * @param contractDto contract data transfer object
     */
    void update(ContractDto contractDto);

    /**
     * This method returns contract
     * @param id id of searched contract
     * @return contract with id
     */
    Contract get(int id);

    /**
     * This method returns contract which was signed for searching phone number
     * @param phoneNumber phone number which locked for contract
     * @return contract with inputted phone number
     */
    Contract getByPhoneNumber(String phoneNumber);

    /**
     * Method deletes contract
     * @param id id of contract which should be deleted
     */
    void delete(int id);
}
