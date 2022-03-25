package com.school.database.dao.contracts;

import com.school.database.entity.Number;

import java.util.List;

public interface NumberDao {

    Number get(int id);

    Number getByName(String name);

    List<Number> getAll();

    List<Number> getAllUsed();

    List<Number> getAllUnused();

    boolean checkNumberToUnique(Number number);

    void save(Number t);

    void delete(int id);

}
