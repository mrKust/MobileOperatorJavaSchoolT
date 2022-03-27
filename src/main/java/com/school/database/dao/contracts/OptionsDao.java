package com.school.database.dao.contracts;

import com.school.database.entity.Options;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OptionsDao {

    @Transactional
    Options get(int id);

    @Transactional
    List<Options> getAll();

    @Transactional
    List<Options> getAllAvailable();

    @Transactional
    List<Options> getOptionsFromChosenList(List<Integer> list);

    @Transactional
    void save(Options options);

    @Transactional
    void delete(int id);

}
