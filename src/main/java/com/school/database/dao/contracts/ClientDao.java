package com.school.database.dao.contracts;

import com.school.database.entity.Client;

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
    Client get(int id);

    /**
     * Method search user by client's email address
     * @param email email address of the client which we want to get
     * @return client which we have been looking for
     */
    Client getByEmail(String email);

    /**
     * Method return all client which registered in system
     * @return list which contains all clients from system
     */
    List<Client> getAll();

    /**
     * Method check if in system contains another client with client's email or not
     * @param client client email address we want check to unique status
     * @return number of users with same email
     */
    int checkUserEmailToUnique(Client client);

    /**
     * Method returns list of client for show it on one page
     * @param pageSize number of records on one page
     * @param sortColumn filed which will be used as field to compare clients
     * @param pageNumber number of page where client will be shown
     * @return list of client for show on page
     */
    List<Client> getPageOfClients(int pageSize, String sortColumn, int pageNumber);

    /**
     * Method counts number of pages on which records could be separated with this size of page
     * @param sizeOfPage number of record on one page
     * @return number of pages
     */
    int getNumberOfPages(int sizeOfPage);

    /**
     * Method saves or update client
     * @param client client which need to be saved or to be updated
     */
    void save(Client client);

    /**
     * Method deletes client
     * @param id id of client which should be deleted
     */
    void delete(int id);

}
