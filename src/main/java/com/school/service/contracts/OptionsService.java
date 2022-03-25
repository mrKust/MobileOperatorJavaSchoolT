package com.school.service.contracts;

import com.school.database.entity.Options;
import com.school.dto.OptionsDto;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OptionsService {

    @Transactional(propagation = Propagation.NESTED)
    List<Options> getAll();

    @Transactional(propagation = Propagation.NESTED)
    List<Options> getAllAvailable();

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void save(OptionsDto t);

    @Transactional(propagation = Propagation.NESTED)
    Options get(int id);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void delete(int id);
}
