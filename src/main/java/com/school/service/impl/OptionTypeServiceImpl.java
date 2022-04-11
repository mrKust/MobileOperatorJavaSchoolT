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

    /**
     * Described at {@link OptionTypeService}
     * @return list of option types
     */
    @Override
    public List<OptionType> getAll() {
        return optionsTypeDao.getAll();
    }

    /**
     * Described at {@link OptionTypeService}
     * @param optionType option type which need to be saved or updated
     */
    @Override
    public void save(OptionType optionType) {

        if (!checkOptionTypeToUnique(optionType)) {
            throw new ServiceLayerException("Type with this name has already created");
        }

        optionsTypeDao.save(optionType);
    }

    /**
     * Described at {@link OptionTypeService}
     * @param id id of option type which we are looking for
     * @return option type with required id
     */
    @Override
    public OptionType get(int id) {
        return optionsTypeDao.get(id);
    }

    /**
     * Described at {@link OptionTypeService}
     * @param optionType option type which checked to unique status
     * @return true if unique, false otherwise
     */
    @Override
    public boolean checkOptionTypeToUnique(OptionType optionType) {
        return optionsTypeDao.checkOptionTypeToUnique(optionType);
    }

    /**
     * Described at {@link OptionTypeService}
     * @param id id of option type which need to be deleted
     */
    @Override
    public void delete(int id) {
        OptionType optionType = get(id);
        if (optionType.getOptions().size() > 0) {
            throw new ServiceLayerException("Can't delete option type if some options use it");
        }

        optionsTypeDao.delete(id);
    }
}
