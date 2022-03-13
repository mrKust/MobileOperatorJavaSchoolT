package com.school.service;

import com.school.database.dao.Dao;
import com.school.database.entity.Number;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

@Service
public class NumberServiceImpl implements ServiceMVC<Number> {

    private final Dao<Number> numberDao;

    NumberServiceImpl(Dao<Number> numberDao) {
        this.numberDao = numberDao;
    }

    @Override
    public List<Number> getAll() {
        return numberDao.getAll();
    }

    @Override
    public void save(Number number) {
        numberDao.save(number);
    }

    @Override
    public Number get(int id) {
        return numberDao.get(id);
    }

    @Override
    public Number getByName(String name) {
        return numberDao.getByName(name);
    }

    @Override
    public void delete(int id) {
        numberDao.delete(id);
    }
}
