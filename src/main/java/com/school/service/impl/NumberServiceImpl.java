package com.school.service.impl;

import com.school.customException.ServiceLayerException;
import com.school.database.dao.contracts.NumberDao;
import com.school.database.entity.Number;
import com.school.service.contracts.NumberService;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.regex.Pattern;

@Service
public class NumberServiceImpl implements NumberService {

    private static Pattern PHONE_NUMBER_PATTERN = Pattern.compile(
            "[0-9]{11}");

    private final NumberDao numberDao;

    NumberServiceImpl(NumberDao numberDao) {
        this.numberDao = numberDao;
    }

    @Override
    public List<Number> getAll() {
        return numberDao.getAll();
    }

    @Override
    public List<Number> getAllUsed() {
        return numberDao.getAllUsed();
    }

    @Override
    public List<Number> getAllUnused() {
        return numberDao.getAllUnused();
    }

    @Override
    public boolean checkNumberToUnique(Number number) {
        return numberDao.checkNumberToUnique(number);
    }

    @Override
    public void save(Number number) {

        if (!PHONE_NUMBER_PATTERN.matcher(number.getPhoneNumber()).matches()) {
            throw new ServiceLayerException("Couldn't add this phone number in phone's base\n" +
                    "It is presented in wrong pattern. Try again. Look at hint");
        }

        if (!checkNumberToUnique(number)) {
            throw new ServiceLayerException("Couldn't add this phone number in phone's base\n" +
                        "It is already contains in base");}

        numberDao.save(number);
    }

    @Override
    public void update(Number t) {
        numberDao.save(t);
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
