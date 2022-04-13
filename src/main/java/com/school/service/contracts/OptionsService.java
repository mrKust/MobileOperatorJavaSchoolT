package com.school.service.contracts;

import com.school.database.entity.Options;
import com.school.dto.OptionsDto;

import javax.transaction.Transactional;
import java.util.List;

/**
 * This service describe operations with options
 */
@Transactional
public interface OptionsService {

    /**
     * Method return all options which contains in system
     * @return list of all options in system
     */
    List<Options> getAll();

    /**
     * Method returns all options which is ready to be connected by client
     * @return list of available to connect options
     */
    List<Options> getAllAvailable();

    /**
     * Method returns all options which id's contains in list
     * @param list list with ids of options which we want to get
     * @return list of options
     */
    List<Options> getOptionsFromChosenList(List<Integer> list);

    /**
     * This method returns list of options which will be shown on one page
     * @param optionsDto option data transfer object
     * @param numberOfPage number of page where options will be shown
     * @return list of options for show on page
     */
    List<Options> getPageOfOptions(OptionsDto optionsDto, Integer numberOfPage);

    /**
     * Method counts number of pages on which records could be separated with this size of page
     * @param sizeOfPage number of record on one page
     * @return number of pages
     */
    int getNumberOfPages(int sizeOfPage);

    /**
     * Method saves option
     * @param optionsDto option data transfer object
     */
    void save(OptionsDto optionsDto);

    /**
     * Method updates option
     * @param optionsDto option data transfer object
     */
    void update(OptionsDto optionsDto);

    /**
     * Method returns option by id
     * @param id id of option which need to be returned
     * @return option with this id
     */
    Options get(int id);

    /**
     * Method deletes option
     * @param id option's id which need to be deleted
     */
    void delete(int id);
}
