package com.school.service.contracts;

import com.school.database.entity.Number;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NumberService {

    List<Number> getAll();

    String[] getAllUsed();

    String[] getAllUnused();

    boolean checkNumberToUnique(Number number);

    void save(Number number);

    void update(Number number);

    Number get(int id);

    Number getByPhoneNumber(String phoneNumber);

    void delete(int id);



}
