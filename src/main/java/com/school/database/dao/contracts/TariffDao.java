package com.school.database.dao.contracts;

import com.school.database.entity.Options;
import com.school.database.entity.Tariff;

import java.util.List;

public interface TariffDao {

    Tariff get(int id);

    Tariff getByName(String name);

    List<Tariff> getAll();

    List<Object> getTariffOptions(int id);

    void save(Tariff t);

    void delete(int id);

}
