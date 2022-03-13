package com.school.service;

import com.school.database.dao.Dao;
import com.school.database.entity.Client;
import com.school.database.entity.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OptionsServiceImpl implements ServiceMVC<Options>{

    private final Dao<Options> optionsDao;

    OptionsServiceImpl(Dao<Options> optionsDao) {
        this.optionsDao = optionsDao;
    }

    @Override
    public List<Options> getAll() {
        return optionsDao.getAll();
    }

    @Override
    public void save(Options options) {
        optionsDao.save(options);
    }

    @Override
    public Options get(int id) {
        return optionsDao.get(id);
    }

    @Override
    public Options getByName(String name) {
        return optionsDao.getByName(name);
    }

    @Override
    public void delete(int id) {
        optionsDao.delete(id);
    }
}
