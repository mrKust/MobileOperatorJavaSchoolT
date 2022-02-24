package com.school.service;

import com.school.database.dao.Dao;
import com.school.database.entity.Tariff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TariffServiceImpl  implements ServiceMVC<Tariff>{

    @Autowired
    private Dao<Tariff> tariffDao;

    @Override
    @Transactional
    public List<Tariff> getAll() {
        return tariffDao.getAll();
    }
}
