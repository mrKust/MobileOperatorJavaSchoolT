package com.school.service.impl;

import com.school.database.dao.Dao;
import com.school.database.entity.Contract;
import com.school.service.contracts.ServiceMVC;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractServiceImpl implements ServiceMVC<Contract> {

    private final Dao<Contract> contractDao;

    ContractServiceImpl(Dao<Contract> contractDao) {
        this.contractDao = contractDao;
    }

    @Override
    public List<Contract> getAll() {
        return contractDao.getAll();
    }

    @Override
    public void save(Contract contract) {
        contractDao.save(contract);
    }

    @Override
    public Contract get(int id) {
        return contractDao.get(id);
    }

    @Override
    public Contract getByName(String phoneNumber) {
        return contractDao.getByName(phoneNumber);
    }

    @Override
    public void delete(int id) {
        contractDao.delete(id);
    }
}
