package com.school.service.contracts;

import com.school.database.entity.Client;
import com.school.dto.ClientDto;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ClientService {

    List<Client> getAll();

    void save(ClientDto clientDto);

    Client get(int id);

    Client getByEmail(String email);

    void delete(int id);
}
