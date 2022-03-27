package com.school.service.contracts;

import com.school.database.entity.Client;
import com.school.dto.ClientDto;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ClientService {

    @Transactional(propagation = Propagation.NESTED)
    List<Client> getAll();

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void save(ClientDto clientDto);

    @Transactional(propagation = Propagation.NESTED)
    Client get(int id);

    @Transactional(propagation = Propagation.NESTED)
    Client getByEmail(String email);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void delete(int id);
}
