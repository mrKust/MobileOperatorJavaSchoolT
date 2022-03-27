package com.school.database.dao.contracts;

import com.school.database.entity.Number;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NumberDao {

    @Transactional
    Number get(int id);

    @Transactional
    Number getByPhoneNumber(String phoneNumber);

    @Transactional
    List<Number> getAll();

    @Transactional
    List<String> getAllUsed();

    @Transactional
    List<String> getAllUnused();

    @Transactional
    boolean checkNumberToUnique(Number number);

    @Transactional
    void save(Number number);

    @Transactional
    void delete(int id);

}
