package com.school.service.impl;

import com.school.customException.ServiceLayerException;
import com.school.database.dao.contracts.OptionsDao;
import com.school.database.dao.contracts.TariffDao;
import com.school.database.entity.Options;
import com.school.database.entity.Tariff;
import com.school.dto.TariffDto;
import com.school.service.contracts.TariffService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TariffServiceImpl  implements TariffService {

    private final TariffDao tariffDao;
    private final OptionsDao optionsDao;

    TariffServiceImpl(TariffDao tariffDao, OptionsDao optionsDao) {
        this.tariffDao = tariffDao;
        this.optionsDao = optionsDao;
    }

    @Override
    public List<Tariff> getAll() {
        return tariffDao.getAll();
    }

    @Override
    public List<Object> getTariffOptions(int id) {
        return tariffDao.getTariffOptions(id);
    }

    @Override
    public void save(TariffDto tariffDto) {
        Tariff tariff = tariffDto.getTariff();

        tariff.setOptions(tariffDto.wrapStringsToList(optionsDao.getAll()));
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
