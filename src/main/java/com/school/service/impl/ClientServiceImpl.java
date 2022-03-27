package com.school.service.impl;

import com.school.customException.BusinessLogicException;
import com.school.customException.ServiceLayerException;
import com.school.database.dao.contracts.ClientDao;
import com.school.database.entity.Client;
import com.school.database.entity.Number;
import com.school.dto.ClientDto;
import com.school.service.contracts.ClientService;
import com.school.service.contracts.NumberService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class ClientServiceImpl implements ClientService {

    private static Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"
    );

    private final ClientDao clientDao;
    private final NumberService numberService;

    ClientServiceImpl(ClientDao clientDao, NumberService numberService) {
        this.clientDao = clientDao;
        this.numberService = numberService;
    }

    @Override
    public List<Client> getAll() {
        return clientDao.getAll();
    }

    @Override
    public boolean checkUserEmailToUnique(Client client) {
        return clientDao.checkUserEmailToUnique(client);
    }

    @Override
    public void save(ClientDto clientDto) {
        Client client = clientDto.getClient();

        if (!EMAIL_ADDRESS_PATTERN.matcher(client.getEmailAddress()).matches()) {
            throw new ServiceLayerException("Can't save user with this email." +
                    "E-mail incorrect form");
        }

        if (!checkUserEmailToUnique(client)) {
            throw new ServiceLayerException("Can't save user with this email." +
                    "User with this email already registered");
        }

        Number number = null;
        String roleCast = clientDto.getClient().getUserRole().replace(",", "");
        client.setUserRole(roleCast);
        if (clientDto.getClient().getUserRole().equals("client")) {
            number = numberService.getByPhoneNumber(clientDto.getClient().getPhoneNumber());
            number.setAvailableToConnectStatus(false);
            numberService.update(number);
        }

        String encodedPassword = new BCryptPasswordEncoder().encode(clientDto.getClient().getPasswordLogIn());
        clientDto.getClient().setPasswordLogIn(encodedPassword);

        clientDao.save(client);
    }

    @Override
    public void update(ClientDto clientDto) {
        Client client = clientDto.getClient();
        if (client.getPasswordLogIn() == null)
            if ( ( (clientDto.getPasswordString() != null) && (clientDto.getPasswordString2() != null) ) &&
                    ((!clientDto.getPasswordString().equals("")) && (!clientDto.getPasswordString2().equals(""))) ){
                if (clientDto.getPasswordString().equals(clientDto.getPasswordString2())) {
                    String encodedPassword = new BCryptPasswordEncoder().encode(clientDto.getPasswordString());
                    clientDto.getClient().setPasswordLogIn(encodedPassword);
                } else {
                    throw new ServiceLayerException("Can't update password. New passwords doesn't match");
                }
            } else {

                client.setPasswordLogIn(this.get(clientDto.getClient().getId()).getPasswordLogIn());
            }
        clientDao.save(client);
    }

    @Override
    public void lock(ClientDto clientDto) {

        Client client = this.get(clientDto.getId());

        client.setClientNumberReadyToWorkStatus(false);
        client.setRoleOfUserWhoBlockedNumber(clientDto.getBlockedRole());

        clientDao.save(client);
    }

    @Override
    public void unlock(ClientDto clientDto) {

        Client client = this.get(clientDto.getId());

        client.setClientNumberReadyToWorkStatus(true);
        client.setRoleOfUserWhoBlockedNumber(null);

        clientDao.save(client);
    }

    @Override
    public Client get(int id) {
        return clientDao.get(id);
    }

    @Override
    public Client getByEmail(String email) {
        return clientDao.getByEmail(email);
    }

    @Override
    public Client getByPhoneNumber(String phoneNumber) {
        return clientDao.getByPhoneNumber(phoneNumber);
    }

    @Override
    public void delete(int id) {

        Client client = get(id);
        if (client.getUserRole().equals("client")) {
            Number number = numberService.getByPhoneNumber(client.getPhoneNumber());
            number.setAvailableToConnectStatus(true);
            numberService.update(number);

        }
        clientDao.delete(id);
    }
}
