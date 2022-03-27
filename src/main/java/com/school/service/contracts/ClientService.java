package com.school.service.contracts;

import com.school.database.entity.Client;
import com.school.dto.ClientDto;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ClientService {

    List<Client> getAll();

    boolean checkUserEmailToUnique(Client client);

    void save(ClientDto clientDto);

    void update(ClientDto clientDto);

    void lock(ClientDto clientDto);

    void unlock(ClientDto clientDto);

    Client get(int id);

    Client getByEmail(String email);

    Client getByPhoneNumber(String phoneNumber);

    void delete(int id);
}
