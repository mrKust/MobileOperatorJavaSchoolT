package com.school.database.dao.contracts;

import com.school.database.entity.Options;

import java.util.List;

public interface OptionsDao {

    Options get(int id);

    List<Options> getAll();

    List<Options> getAllAvailable();

    void save(Options t);

    void delete(int id);

}
