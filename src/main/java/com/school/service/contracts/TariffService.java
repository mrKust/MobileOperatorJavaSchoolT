package com.school.service.contracts;

import com.school.database.entity.Tariff;
import com.school.dto.TariffDto;

import javax.transaction.Transactional;
import java.util.List;

/**
 * This service describe operations with tariff
 */
@Transactional
public interface TariffService {

    /**
     * Method returns all tariffs which contains in system
     * @return list of tariffs
     */
    List<Tariff> getAll();

    /**
     * Method returns all tariffs which could be connected by clients
     * @return list of available to connect tariffs
     */
    List<Tariff> getAllAvailable();

    /**
     * This method returns list of tariffs which will be shown on one page
     * @param tariffDto tariff data transfer object
     * @param numberOfPage number of page where tariffs will be shown
     * @return list of tariffs for show on page
     */
    List<Tariff> getPageOfTariffs(TariffDto tariffDto, Integer numberOfPage);

    /**
     * Method counts number of pages on which records could be separated with this size of page
     * @param sizeOfPage number of record on one page
     * @return number of pages
     */
    int getNumberOfPages(int sizeOfPage);

    /**
     * Method saves tariff
     * @param tariffDto tariff data transfer object
     */
    void save(TariffDto tariffDto);

    /**
     * Method updates tariff
     * @param tariffDto tariff data transfer object
     */
    void update(TariffDto tariffDto);

    /**
     * Method sends in MQ server message about tariff changes
     */
    void notificationAboutTariffUpdate();

    /**
     * Method returns tariff by id
     * @param id id of tariff which we are looking for
     * @return tariff with required id
     */
    Tariff get(int id);

    /**
     * Method deletes tariffs
     * @param id id of tariff which should be deleted
     */
    void delete(int id);

}
