package com.school.service.contracts;

import com.school.database.entity.Number;
import com.school.database.entity.OptionType;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OptionTypeService {

    @Transactional(propagation = Propagation.NESTED)
    List<OptionType> getAll();

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void save(OptionType t);

    @Transactional(propagation = Propagation.NESTED)
    OptionType get(int id);

    @Transactional(propagation = Propagation.NESTED)
    boolean checkOptionTypeToUnique(OptionType optionType);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void delete(int id);
}
