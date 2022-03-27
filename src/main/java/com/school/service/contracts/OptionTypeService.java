package com.school.service.contracts;

import com.school.database.entity.OptionType;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OptionTypeService {

    List<OptionType> getAll();

    void save(OptionType optionType);

    OptionType get(int id);

    boolean checkOptionTypeToUnique(OptionType optionType);

    void delete(int id);
}
