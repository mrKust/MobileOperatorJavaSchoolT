package com.school.database.dao.contracts;

import com.school.database.entity.Number;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NumberDao {

    /**
     * Method returns phone number by it's id
     * @param id id of phone number which we are looking for
     * @return number with inputted id
     */
    @Transactional
    Number get(int id);

    /**
     * Method returns phone number entity by value of phone number
     * @param phoneNumber value of phone number which we are looking for
     * @return phone number entity
     */
    @Transactional
    Number getByPhoneNumber(String phoneNumber);

    /**
     * Method returns all phone numbers which contains in system
     * @return list of phone numbers
     */
    @Transactional
    List<Number> getAll();

    /**
     * Method returns all phone numbers which connected to any contracts
     * @return list of phone numbers which already used by clients
     */
    @Transactional
    List<String> getAllUsed();

    /**
     * Method returns all phone numbers which don't connected to any contracts
     * @return list of available to connect phone numbers
     */
    @Transactional
    List<String> getAllUnused();

    /**
     * Method checks if phone number with this phone number's value already contains in system
     * @param number number which is checked to unique status
     * @return true if unique, false otherwise
     */
    @Transactional
    boolean checkNumberToUnique(Number number);

    /**
     * Method saves or updates phone number
     * @param number number which need to be saved or updated
     */
    @Transactional
    void save(Number number);

    /**
     * Method deletes phone number
     * @param id id of phone's number which need to be deleted
     */
    @Transactional
    void delete(int id);

}
