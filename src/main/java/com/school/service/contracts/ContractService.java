package com.school.service.contracts;

import com.school.database.entity.Contract;
import com.school.dto.ContractDto;

import java.util.List;

public interface ContractService {

    List<Contract> getAll();

    void save(ContractDto contractDto);

    Contract get(int id);

    void delete(int id);
}
