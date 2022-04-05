package com.school.database.dao.contracts;

import com.school.database.entity.OptionType;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OptionTypeDao {

    /**
     * Method returns option type with required id
     * @param id id of option type which we are looking for
     * @return option type with required id
     */
    @Transactional
    OptionType get(int id);

    /**
     * Method returns all option types which contained in system
     * @return list of option types
     */
    @Transactional
    List<OptionType> getAll();

    /**
     * Method saves or updates option type
     * @param optionType option type which need to be saved or updated
     */
    @Transactional
    void save(OptionType optionType);

    /**
     * Method check if option type with this option's type name already stored in system
     * @param optionType option type which checked to unique status
     * @return true if unique, false otherwise
     */
    @Transactional
    boolean checkOptionTypeToUnique(OptionType optionType);

    /**
     * Method deletes option type
     * @param id id of option type which need to be deleted
     */
    @Transactional
    void delete(int id);

}
