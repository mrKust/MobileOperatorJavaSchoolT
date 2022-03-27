package com.school.service.impl;

import com.school.customException.ServiceLayerException;
import com.school.database.dao.contracts.TariffDao;
import com.school.database.entity.Options;
import com.school.database.entity.Tariff;
import com.school.dto.TariffDto;
import com.school.service.contracts.OptionsService;
import com.school.service.contracts.TariffService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TariffServiceImpl  implements TariffService {

    private final TariffDao tariffDao;
    private final OptionsService optionsService;

    TariffServiceImpl(TariffDao tariffDao, OptionsService optionsService) {
        this.tariffDao = tariffDao;
        this.optionsService = optionsService;
    }

    @Override
    public List<Tariff> getAll() {
        return tariffDao.getAll();
    }


    @Override
    public void save(TariffDto tariffDto) {
        Tariff tariff = tariffDto.getTariff();

        tariff.setOptions(tariffDto.wrapStringsToList(optionsService.getAll()));
        //tariff.setOptions(optionsService.getListOfChosenOption(tariffDto.getChosenOptionsList()));
        tariffDao.save(tariff);
    }

    @Override
    public Tariff get(int id) {
        return tariffDao.get(id);
    }

    @Override
    public Tariff getByName(String name) {
        return tariffDao.getByName(name);
    }

    @Override
    public void delete(int id) {
        Tariff tariff = get(id);
        if (tariff.getContract() != null) {
            throw new ServiceLayerException("Couldn't delete tariff " + tariff.getTariffName() +
                    "\nTariff couldn't be deleted if it connected to although one contract");
        }
        tariffDao.delete(id);
    }
}
