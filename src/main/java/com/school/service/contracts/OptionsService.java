package com.school.service.contracts;

import com.school.database.entity.Options;
import com.school.dto.OptionsDto;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OptionsService {

    List<Options> getAll();

    List<Options> getAllAvailable();

    List<Options> getOptionsFromChosenList(List<Integer> list);

    List<Options> getPageOfOptions(OptionsDto optionsDto, Integer numberOfPage);

    int getNumberOfPages(int sizeOfPage);

    void save(OptionsDto optionsDto);

    Options get(int id);

    void delete(int id);
}
