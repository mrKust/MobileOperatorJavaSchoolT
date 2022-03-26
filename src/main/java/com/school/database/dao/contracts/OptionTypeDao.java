package com.school.database.dao.contracts;

import com.school.database.entity.OptionType;

import java.util.List;

public interface OptionTypeDao {

    OptionType get(int id);

    List<OptionType> getAll();

    void save(OptionType optionType);

    boolean checkOptionTypeToUnique(OptionType optionType);

    void delete(int id);

}
