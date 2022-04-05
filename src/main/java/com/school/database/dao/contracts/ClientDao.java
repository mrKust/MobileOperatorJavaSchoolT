package com.school.database.dao.contracts;

import com.school.database.entity.Client;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * This class give us list of operations with Client entity class
 */
public interface ClientDao {

    /**
     * Method search user by client's id
     * @param id id of client which we want to get
     * @return client which we have been looking for
     */
    @Transactional
    Client get(int id);

    /**
     * Method search user by client's email address
     * @param email email address of the client which we want to get
     * @return client which we have been looking for
     */
    @Transactional
    Client getByEmail(String email);

    /**
     * Method return all client which registered in system
     * @return list which contains all clients from system
     */
    @Transactional
    List<Client> getAll();

    /**
     * Method check if in system contains another client with client's email or not
     * @param client client email address we want check to unique status
     * @return true if unique, false otherwise
     */
    @Transactional
    boolean checkUserEmailToUnique(Client client);

    /**
     * Method returns list of client for show it on one page
     * @param pageSize number of records on one page
     * @param sortColumn filed which will be used as field to compare clients
     * @param pageNumber number of page where client will be shown
     * @return list of client for show on page
     */
    @Transactional
    List<Client> getPageOfClients(int pageSize, String sortColumn, int pageNumber);

    /**
     * Method counts number of pages on which records could be separated with this size of page
     * @param sizeOfPage number of record on one page
     * @return number of pages
     */
    @Transactional
    int getNumberOfPages(int sizeOfPage);

    /**
     * Method saves or update client
     * @param client client which need to be saved or to be updated
     */
    @Transactional
    void save(Client client);

    /**
     * Method deletes client
     * @param id
     */
    @Transactional
    void delete(int id);

}
