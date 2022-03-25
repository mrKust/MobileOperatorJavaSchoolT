package com.school.service.contracts;

import com.school.database.entity.Number;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NumberService {

    @Transactional(propagation = Propagation.NESTED)
    List<Number> getAll();

    @Transactional(propagation = Propagation.NESTED)
    List<Number> getAllUsed();

    @Transactional(propagation = Propagation.NESTED)
    List<Number> getAllUnused();

    @Transactional(propagation = Propagation.NESTED)
    boolean checkNumberToUnique(Number number);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void save(Number t);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void update(Number t);

    @Transactional(propagation = Propagation.NESTED)
    Number get(int id);

    @Transactional(propagation = Propagation.NESTED)
    Number getByName(String name);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void delete(int id);



}
