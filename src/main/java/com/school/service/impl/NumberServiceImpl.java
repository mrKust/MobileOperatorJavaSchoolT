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

    /**
     * Described at {@link NumberService}
     * @return list of phone numbers
     */
    @Override
    public List<Number> getAll() {
        return numberDao.getAll();
    }

    /**
     * Described at {@link NumberService}
     * @return array of phone numbers which already used by clients
     */
    @Override
    public String[] getAllUsed() {
        return numberDao.getAllUsed().toArray(new String[0]);
    }

    /**
     * Described at {@link NumberService}
     * @return array of available to connect phone numbers
     */
    @Override
    public String[] getAllUnused() {
        return numberDao.getAllUnused().toArray(new String[0]);
    }

    /**
     * Described at {@link NumberService}
     * @param number number which is checked to unique status
     * @return true if unique, false otherwise
     */
    @Override
    public boolean checkNumberToUnique(Number number) {
        int numberOfSameRecords = numberDao.checkNumberToUnique(number);
        if (numberOfSameRecords == 0)
            return true;

        return false;
    }

    /**
     * Described at {@link NumberService}
     * @param number number which need to be saved or updated
     */
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

    /**
     * Described at {@link NumberService}
     * @param number number which need to be saved or updated
     */
    @Override
    public void update(Number number) {
        numberDao.save(number);
    }

    /**
     * Described at {@link NumberService}
     * @param id id of phone number which we are looking for
     * @return number with inputted id
     */
    @Override
    public Number get(int id) {
        return numberDao.get(id);
    }

    /**
     * Described at {@link NumberService}
     * @param phoneNumber value of phone number which we are looking for
     * @return phone number entity
     */
    @Override
    public Number getByPhoneNumber(String phoneNumber) {
        return numberDao.getByPhoneNumber(phoneNumber);
    }

    /**
     * Described at {@link NumberService}
     * @param id id of phone's number which need to be deleted
     */
    @Override
    public void delete(int id) {
        numberDao.delete(id);
    }
}
