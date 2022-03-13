package com.school.service;

import com.school.database.dao.Dao;
import com.school.database.entity.Client;
import com.school.database.entity.Tariff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TariffServiceImpl  implements ServiceMVC<Tariff>{

    private final Dao<Tariff> tariffDao;

    TariffServiceImpl(Dao<Tariff> tariffDao) {
        this.tariffDao = tariffDao;
    }

    @Override
    public List<Tariff> getAll() {
        return tariffDao.getAll();
    }

    @Override
    public void save(Tariff tariff) {
        tariffDao.save(tariff);
    }

    @Override
    public Tariff get(int id) {
        return tariffDao.get(id);
    }

    @Override
    public Tariff getByName(String name) {
        return tariffDao.getByName(name);
    }

    @Override
    public void delete(int id) {
        tariffDao.delete(id);
    }
}
