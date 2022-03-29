package com.school.database.dao.contracts;

import com.school.database.entity.Client;
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
    void save(Client client);

    @Transactional
    void delete(int id);

}
