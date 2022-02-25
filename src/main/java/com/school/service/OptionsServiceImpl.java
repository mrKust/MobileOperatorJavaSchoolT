package com.school.service;

import com.school.database.dao.Dao;
import com.school.database.entity.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OptionsServiceImpl implements ServiceMVC<Options>{

    @Autowired
    private Dao<Options> optionsDao;

    @Override
    @Transactional
    public List<Options> getAll() {
        return optionsDao.getAll();
    }

    @Override
    @Transactional
    public void save(Options options) {
        optionsDao.save(options);
    }

    @Override
    @Transactional
    public Options get(int id) {
        return optionsDao.get(id);
    }
}
