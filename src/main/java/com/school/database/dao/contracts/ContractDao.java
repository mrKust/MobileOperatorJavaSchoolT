package com.school.database.dao.contracts;

import com.school.database.entity.Contract;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ContractDao {

    @Transactional
    Contract get(int id);

    @Transactional
    List<Contract> getAll();

    @Transactional
    void save(Contract contract);

    @Transactional
    void delete(int id);

}
