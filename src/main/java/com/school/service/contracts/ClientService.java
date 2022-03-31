package com.school.service.contracts;

import com.school.database.entity.Client;
import com.school.database.entity.Contract;
import com.school.database.entity.Tariff;
import com.school.dto.ClientDto;
import com.school.dto.TariffDto;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ClientService {

    List<Client> getAll();

    List<Client> getPageOfClients(ClientDto clientDto, Integer numberOfPage);

    int getNumberOfPages(int sizeOfPage);

    boolean checkUserEmailToUnique(Client client);

    void save(ClientDto clientDto);

    void update(ClientDto clientDto);

    Client get(int id);

    Client getByEmail(String email);

    void addMoney(ClientDto clientDto);

    void delete(int id);
}
