package com.school.database.dao.contracts;

import com.school.database.entity.OptionType;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OptionTypeDao {

    @Transactional
    OptionType get(int id);

    @Transactional
    List<OptionType> getAll();

    @Transactional
    void save(OptionType optionType);

    @Transactional
    boolean checkOptionTypeToUnique(OptionType optionType);

    @Transactional
    void delete(int id);

}
