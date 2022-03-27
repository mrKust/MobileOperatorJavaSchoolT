package com.school.service.impl;

import com.school.database.dao.contracts.ClientDao;
import com.school.database.entity.Client;
import com.school.dto.ClientDto;
import com.school.service.contracts.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientDao clientDao;

    ClientServiceImpl(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    public List<Client> getAll() {
        return clientDao.getAll();
    }

    @Override
    public void save(ClientDto clientDto) {
        clientDao.save(clientDto.getClient());
    }

    @Override
    public Client get(int id) {
        return clientDao.get(id);
    }

    @Override
    public Client getByEmail(String email) {
        return clientDao.getByEmail(email);
    }

    @Override
    public void delete(int id) {
        clientDao.delete(id);
    }
}
