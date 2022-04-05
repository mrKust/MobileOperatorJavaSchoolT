package com.school.database.dao.contracts;

import com.school.database.entity.Options;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OptionsDao {

    /**
     * Method returns option by id
     * @param id id of option which need to be returned
     * @return option with this id
     */
    @Transactional
    Options get(int id);

    /**
     * Method return all options which contains in system
     * @return list of all options in system
     */
    @Transactional
    List<Options> getAll();

    /**
     * Method returns all options which is ready to be connected by client
     * @return list of available to connect options
     */
    @Transactional
    List<Options> getAllAvailable();

    /**
     * Method returns all options which id's contains in list
     * @param list list with ids of options which we want to get
     * @return list of options
     */
    @Transactional
    List<Options> getOptionsFromChosenList(List<Integer> list);

    /**
     * Method returns option's names and prices for one tariff
     * @param list list of ids option's which available for tariff
     * @return list of options
     */
    @Transactional
    List<Options> getAvailableOptionsNamesAndPricesForTariff(List<Integer> list);

    /**
     * This method returns list of options which will be shown on one page
     * @param pageSize number of records on one page
     * @param sortColumn filed which will be used as field to compare options
     * @param pageNumber number of page where options will be shown
     * @return list of options for show on page
     */
    @Transactional
    List<Options> getPageOfOptions(int pageSize, String sortColumn, int pageNumber);

    /**
     * Method counts number of pages on which records could be separated with this size of page
     * @param sizeOfPage number of record on one page
     * @return number of pages
     */
    @Transactional
    int getNumberOfPages(int sizeOfPage);

    /**
     * Method saves or updates option
     * @param options option which need to be saved or updated
     */
    @Transactional
    void save(Options options);

    /**
     * Method deletes option
     * @param id option's id which need to be deleted
     */
    @Transactional
    void delete(int id);

}
