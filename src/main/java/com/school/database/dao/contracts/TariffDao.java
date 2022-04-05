package com.school.database.dao.contracts;

import com.school.database.entity.Tariff;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TariffDao {

    /**
     * Method returns tariff by id
     * @param id id of tariff which we are looking for
     * @return tariff with required id
     */
    @Transactional
    Tariff get(int id);

    /**
     * Method returns tariff by name
     * @param name name of tariff which we are looking for
     * @return tariff with required name
     */
    @Transactional
    Tariff getByName(String name);

    /**
     * Method returns all tariffs which contains in system
     * @return list of tariffs
     */
    @Transactional
    List<Tariff> getAll();

    /**
     * Method returns all tariffs which could be connected by clients
     * @return list of available to connect tariffs
     */
    @Transactional
    List<Tariff> getAllAvailable();

    /**
     * This method returns list of tariffs which will be shown on one page
     * @param pageSize number of records on one page
     * @param sortColumn filed which will be used as field to compare tariffs
     * @param pageNumber number of page where tariffs will be shown
     * @return list of tariffs for show on page
     */
    @Transactional
    List<Tariff> getPageOfTariffs(int pageSize, String sortColumn, int pageNumber);

    /**
     * Method counts number of pages on which records could be separated with this size of page
     * @param sizeOfPage number of record on one page
     * @return number of pages
     */
    @Transactional
    int getNumberOfPages(int sizeOfPage);

    /**
     * Method saves or updates tariff
     * @param tariff tariff which need to be saved or updated
     */
    @Transactional
    void save(Tariff tariff);

    /**
     * Method deletes tariffs
     * @param id id of tariff which should be deleted
     */
    @Transactional
    void delete(int id);

}
