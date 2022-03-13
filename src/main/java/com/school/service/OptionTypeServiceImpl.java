package com.school.service;

import com.school.database.dao.Dao;
import com.school.database.entity.OptionType;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OptionTypeServiceImpl implements ServiceMVC<OptionType> {

    private final Dao<OptionType> optionsTypeDao;

    OptionTypeServiceImpl(Dao<OptionType> optionsTypeDao) {
        this.optionsTypeDao = optionsTypeDao;
    }

    @Override
    public List<OptionType> getAll() {
        return optionsTypeDao.getAll();
    }

    @Override
    public void save(OptionType optionsType) {
        optionsTypeDao.save(optionsType);
    }

    @Override
    public OptionType get(int id) {
        return optionsTypeDao.get(id);
    }

    @Override
    public OptionType getByName(String name) {
        return optionsTypeDao.getByName(name);
    }

    @Override
    public void delete(int id) {
        optionsTypeDao.delete(id);
    }
}
