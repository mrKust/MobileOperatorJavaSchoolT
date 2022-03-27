package com.school.service.contracts;

import com.school.database.entity.Tariff;
import com.school.dto.TariffDto;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TariffService {

    List<Tariff> getAll();

    List<Object> getTariffOptions(int id);

    void save(TariffDto tariffDto);

    Tariff get(int id);

    Tariff getByName(String name);

    void delete(int id);

}
