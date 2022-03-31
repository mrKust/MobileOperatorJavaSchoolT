package com.school.service.contracts;

import com.school.database.entity.Options;
import com.school.database.entity.Tariff;
import com.school.dto.OptionsDto;
import com.school.dto.TariffDto;


import java.util.List;

public interface TariffService {

    List<Tariff> getAll();

    List<Tariff> getAllAvailable();

    List<Tariff> getPageOfOptions(TariffDto tariffDto, Integer numberOfPage);

    int getNumberOfPages(int sizeOfPage);

    void save(TariffDto tariffDto);

    Tariff get(int id);

    Tariff getByName(String name);

    void delete(int id);

}
