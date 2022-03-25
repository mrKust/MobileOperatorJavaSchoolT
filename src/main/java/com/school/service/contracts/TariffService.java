package com.school.service.contracts;

import com.school.database.entity.Options;
import com.school.database.entity.Tariff;
import com.school.dto.TariffDto;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TariffService {

    @Transactional(propagation = Propagation.NESTED)
    List<Tariff> getAll();

    @Transactional(propagation = Propagation.NESTED)
    List<Object> getTariffOptions(int id);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void save(TariffDto t);

    @Transactional(propagation = Propagation.NESTED)
    Tariff get(int id);

    @Transactional(propagation = Propagation.NESTED)
    Tariff getByName(String name);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void delete(int id);

}
