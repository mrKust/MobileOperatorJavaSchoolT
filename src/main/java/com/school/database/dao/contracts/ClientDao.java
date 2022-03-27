package com.school.database.dao.contracts;

import com.school.database.entity.Client;

import java.util.List;

public interface ClientDao {

    Client get(int id);

    Client getByEmail(String email);

    List<Client> getAll();

    void save(Client client);

    void delete(int id);

}
