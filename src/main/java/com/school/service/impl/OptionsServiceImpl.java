package com.school.service.impl;

import com.school.customException.ServiceLayerException;
import com.school.database.dao.contracts.OptionsDao;
import com.school.database.entity.OptionType;
import com.school.database.entity.Options;
import com.school.dto.OptionsDto;
import com.school.service.contracts.OptionTypeService;
import com.school.service.contracts.OptionsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionsServiceImpl implements OptionsService {

    private final OptionsDao optionsDao;
    private final OptionTypeService optionTypeService;

    OptionsServiceImpl(OptionsDao optionsDao, OptionTypeService optionTypeService) {
        this.optionsDao = optionsDao;
        this.optionTypeService = optionTypeService;
    }

    @Override
    public List<Options> getAll() {
        return optionsDao.getAll();
    }

    @Override
    public List<Options> getAllAvailable() {
        return optionsDao.getAllAvailable();
    }

    @Override
    public List<Options> getOptionsFromChosenList(List<Integer> list) {
        return optionsDao.getOptionsFromChosenList(list);
    }

    @Override
    public void save(OptionsDto optionsDto) {
        Options option = optionsDto.getOptions();
        if (optionsDto.getStringOptionCategory() != null) {

            String[] chosenOptionType = optionsDto.getStringOptionCategory();
            OptionType optionType = optionTypeService.get(
                    Integer.parseInt(chosenOptionType[0]));
            option.setOptionType(optionType);
        }
        optionsDao.save(option);
    }

    @Override
    public Options get(int id) {
        return optionsDao.get(id);
    }

    @Override
    public void delete(int id) {
        Options options = get(id);
        if (options.getConnectedToContracts().size() > 0) {
            throw new ServiceLayerException("Couldn't delete option " + options.getOptionsName() +
                    "\nOption couldn't be deleted if it connected to although one contract");
        }
        optionsDao.delete(id);
    }
}
