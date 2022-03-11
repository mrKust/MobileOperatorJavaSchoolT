package com.school.service;

import com.school.database.dao.Dao;
import com.school.database.entity.Number;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class NumberServiceImpl implements ServiceMVC<Number> {

    @Autowired
    private Dao<Number> numberDao;

    @Override
    @Transactional
    public List<Number> getAll() {
        return numberDao.getAll();
    }

    @Override
    @Transactional
    public void save(Number number) {
        numberDao.save(number);
    }

    @Override
    @Transactional
    public Number get(int id) {
        return numberDao.get(id);
    }

    @Override
    @Transactional
    public Number getByName(String name) {
        return numberDao.getByName(name);
    }

    @Override
    @Transactional
    public void delete(int id) {
        numberDao.delete(id);
    }
}
