package com.school.service;

import com.school.database.dao.Dao;
import com.school.database.entity.OptionType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OptionTypeServiceImpl implements ServiceMVC<OptionType> {

    private final Dao<OptionType> optionsTypeDao;

    OptionTypeServiceImpl(Dao<OptionType> optionsTypeDao) {
        this.optionsTypeDao = optionsTypeDao;
    }

    @Override
    @Transactional
    public List<OptionType> getAll() {
        return optionsTypeDao.getAll();
    }

    @Override
    @Transactional
    public void save(OptionType optionsType) {
        optionsTypeDao.save(optionsType);
    }

    @Override
    @Transactional
    public OptionType get(int id) {
        return optionsTypeDao.get(id);
    }

    @Override
    @Transactional
    public OptionType getByName(String name) {
        return optionsTypeDao.getByName(name);
    }

    @Override
    @Transactional
    public void delete(int id) {
        optionsTypeDao.delete(id);
    }
}
