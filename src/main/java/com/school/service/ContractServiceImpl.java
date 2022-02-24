package com.school.service;

import com.school.database.dao.Dao;
import com.school.database.entity.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContractServiceImpl implements ServiceMVC<Contract>{

    @Autowired
    private Dao<Contract> contractDao;

    @Override
    @Transactional
    public List<Contract> getAll() {
        return contractDao.getAll();
    }
}
