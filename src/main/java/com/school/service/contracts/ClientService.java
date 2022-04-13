package com.school.service.contracts;

import com.school.database.entity.Client;
import com.school.dto.ClientDto;

import javax.transaction.Transactional;
import java.util.List;

/**
 * This service describe operations with clients
 */
@Transactional
public interface ClientService {

    /**
     * This method return all clients which contains in system
     * @return list with all clients from system
     */
    List<Client> getAll();

    /**
     * Method returns list of client for show it on one page
     * @param clientDto client data transfer object
     * @param numberOfPage number of page where client will be shown
     * @return list of client for show on page
     */
    List<Client> getPageOfClients(ClientDto clientDto, Integer numberOfPage);

    /**
     * Method counts number of pages on which records could be separated with this size of page
     * @param sizeOfPage number of record on one page
     * @return number of pages
     */
    int getNumberOfPages(int sizeOfPage);

    /**
     * Method check if in system contains another client with client's email or not
     * @param client client email address we want check to unique status
     * @return true if unique, false otherwise
     */
    boolean checkUserEmailToUnique(Client client);

    /**
     * Method saves client
     * @param clientDto client data transfer object
     */
    void save(ClientDto clientDto);

    /**
     * Method updates client
     * @param clientDto client data transfer object
     */
    void update(ClientDto clientDto);

    /**
     * Method search user by client's id
     * @param id id of client which we want to get
     * @return client which we have been looking for
     */
    Client get(int id);

    /**
     * Method search user by client's email address
     * @param email email address of the client which we want to get
     * @return client which we have been looking for
     */
    Client getByEmail(String email);

    /**
     * Method add money amount to client's balance
     * @param clientDto client data transfer object
     */
    void addMoney(ClientDto clientDto);

    /**
     * This method sends credentials to new user by email
     * @param recipientEmail email of registered user
     * @param password password of new user
     * @param name first name of the user
     */
    void sendPasswordToNewUser(String recipientEmail, String password, String name);

    /**
     * This method creates random password for new user
     * @return 6 length password
     */
    String createInputPassword();

    /**
     * Method deletes client
     * @param id id of client which should be deleted
     */
    void delete(int id);
}
