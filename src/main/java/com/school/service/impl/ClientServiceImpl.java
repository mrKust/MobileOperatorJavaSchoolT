package com.school.service.impl;

import com.school.database.dao.Dao;
import com.school.database.entity.Client;
import com.school.service.contracts.ServiceMVC;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ServiceMVC<Client> {

    private final Dao<Client> clientDao;

    ClientServiceImpl(Dao<Client> clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    public List<Client> getAll() {
        return clientDao.getAll();
    }

    @Override
    public void save(Client client) {
        clientDao.save(client);
    }

    @Override
    public Client get(int id) {
        return clientDao.get(id);
    }

    @Override
    public Client getByName(String email) {
        return clientDao.getByName(email);
    }

    @Override
    public void delete(int id) {
        clientDao.delete(id);
    }
}
