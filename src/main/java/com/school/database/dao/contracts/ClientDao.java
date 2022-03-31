package com.school.database.dao.contracts;

import com.school.database.entity.Client;
import com.school.database.entity.Tariff;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ClientDao {

    @Transactional
    Client get(int id);

    @Transactional
    Client getByEmail(String email);

    @Transactional
    List<Client> getAll();

    @Transactional
    boolean checkUserEmailToUnique(Client client);

    @Transactional
    List<Client> getPageOfClients(int pageSize, String sortColumn, int pageNumber);

    @Transactional
    int getNumberOfPages(int sizeOfPage);

    @Transactional
    void save(Client client);

    @Transactional
    void delete(int id);

}
