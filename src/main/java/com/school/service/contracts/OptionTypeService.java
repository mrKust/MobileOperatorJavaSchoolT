package com.school.service.contracts;

import com.school.database.entity.OptionType;

import java.util.List;

/**
 * This service describe operations with option types
 */
public interface OptionTypeService {

    /**
     * Method returns all option types which contained in system
     * @return list of option types
     */
    List<OptionType> getAll();

    /**
     * Method saves or updates option type
     * @param optionType option type which need to be saved or updated
     */
    void save(OptionType optionType);

    /**
     * Method returns option type with required id
     * @param id id of option type which we are looking for
     * @return option type with required id
     */
    OptionType get(int id);

    /**
     * Method check if option type with this option's type name already stored in system
     * @param optionType option type which checked to unique status
     * @return true if unique, false otherwise
     */
    boolean checkOptionTypeToUnique(OptionType optionType);

    /**
     * Method deletes option type
     * @param id id of option type which need to be deleted
     */
    void delete(int id);
}
