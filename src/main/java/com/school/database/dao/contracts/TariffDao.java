package com.school.database.dao.contracts;

import com.school.database.entity.Options;
import com.school.database.entity.Tariff;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TariffDao {

    @Transactional
    Tariff get(int id);

    @Transactional
    Tariff getByName(String name);

    @Transactional
    List<Tariff> getAll();

    @Transactional
    List<Tariff> getAllAvailable();

    @Transactional
    List<Tariff> getPageOfTariffs(int pageSize, String sortColumn, int pageNumber);

    @Transactional
    int getNumberOfPages(int sizeOfPage);

    @Transactional
    void save(Tariff tariff);

    @Transactional
    void delete(int id);

}
