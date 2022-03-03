package com.school.service;

import com.school.database.dao.Dao;
import com.school.database.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientServiceImpl implements ServiceMVC<Client> {

    @Autowired
    private Dao<Client> clientDao;

    @Override
    @Transactional
    public List<Client> getAll() {
        return clientDao.getAll();
    }

    @Override
    @Transactional
    public void save(Client client) {
        clientDao.save(client);
    }

    @Override
    @Transactional
    public Client get(int id) {
        return clientDao.get(id);
    }

    @Override
    @Transactional
    public Client getByName(String email) {
        return clientDao.getByName(email);
    }

    @Override
    @Transactional
    public void delete(int id) {
        clientDao.delete(id);
    }
}
