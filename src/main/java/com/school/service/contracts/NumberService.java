package com.school.service.contracts;

import com.school.database.entity.Number;

import java.util.List;

/**
 * This service describe operations with numbers
 */
public interface NumberService {

    /**
     * Method returns all phone numbers which contains in system
     * @return list of phone numbers
     */
    List<Number> getAll();

    /**
     * Method returns all phone numbers which connected to any contracts
     * @return array of phone numbers which already used by clients
     */
    String[] getAllUsed();

    /**
     * Method returns all phone numbers which don't connected to any contracts
     * @return array of available to connect phone numbers
     */
    String[] getAllUnused();

    /**
     * Method checks if phone number with this phone number's value already contains in system
     * @param number number which is checked to unique status
     * @return true if unique, false otherwise
     */
    boolean checkNumberToUnique(Number number);

    /**
     * Method saves phone number
     * @param number number which need to be saved or updated
     */
    void save(Number number);

    /**
     * Method updates phone number
     * @param number number which need to be saved or updated
     */
    void update(Number number);

    /**
     * Method returns phone number by it's id
     * @param id id of phone number which we are looking for
     * @return number with inputted id
     */
    Number get(int id);

    /**
     * Method returns phone number entity by value of phone number
     * @param phoneNumber value of phone number which we are looking for
     * @return phone number entity
     */
    Number getByPhoneNumber(String phoneNumber);

    /**
     * Method deletes phone number
     * @param id id of phone's number which need to be deleted
     */
    void delete(int id);



}
