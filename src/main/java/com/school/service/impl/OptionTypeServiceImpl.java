package com.school.service.impl;

import com.school.customException.ServiceLayerException;
import com.school.database.dao.contracts.OptionTypeDao;
import com.school.database.entity.OptionType;
import com.school.service.contracts.OptionTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionTypeServiceImpl implements OptionTypeService {

    private final OptionTypeDao optionsTypeDao;

    OptionTypeServiceImpl(OptionTypeDao optionsTypeDao) {
        this.optionsTypeDao = optionsTypeDao;
    }

    @Override
    public List<OptionType> getAll() {
        return optionsTypeDao.getAll();
    }

    @Override
    public void save(OptionType optionType) {

        if (!checkOptionTypeToUnique(optionType)) {
            throw new ServiceLayerException("Type with this name has already created");
        }

        optionsTypeDao.save(optionType);
    }

    @Override
    public OptionType get(int id) {
        return optionsTypeDao.get(id);
    }

    @Override
    public boolean checkOptionTypeToUnique(OptionType optionType) {
        return optionsTypeDao.checkOptionTypeToUnique(optionType);
    }

    @Override
    public void delete(int id) {
        OptionType optionType = get(id);
        if (optionType.getOptions().size() > 0) {
            throw new ServiceLayerException("Can't delete option type if some options use it");
        }

        optionsTypeDao.delete(id);
    }
}
