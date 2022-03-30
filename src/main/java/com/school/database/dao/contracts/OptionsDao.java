package com.school.database.dao.contracts;

import com.school.database.entity.Options;
import com.school.dto.OptionsDto;
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
    List<Options> getPageOfOptions(int pageSize, String sortColumn, int pageNumber);

    @Transactional
    int getNumberOfPages(int sizeOfPage);

    @Transactional
    void save(Options options);

    @Transactional
    void delete(int id);

}
