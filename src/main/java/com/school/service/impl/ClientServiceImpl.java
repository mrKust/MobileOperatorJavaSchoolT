package com.school.service.impl;

import com.school.customException.ServiceLayerException;
import com.school.database.dao.contracts.ClientDao;
import com.school.database.entity.Client;
import com.school.dto.ClientDto;
import com.school.service.contracts.ClientService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Pattern;

@Service
public class ClientServiceImpl implements ClientService {

    private static Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"
    );

    private final ClientDao clientDao;

    ClientServiceImpl(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    public List<Client> getAll() {
        return clientDao.getAll();
    }

    @Override
    public List<Client> getPageOfClients(ClientDto clientDto, Integer numberOfPage) {
        if (clientDto.getPageSize() == 0)
            clientDto.setPageSize(5);
        if (clientDto.getSortColumn() == null)
            clientDto.setSortColumn("surname");
        if (numberOfPage == null)
            numberOfPage = 1;
        return clientDao.getPageOfClients(clientDto.getPageSize(), clientDto.getSortColumn(),
                numberOfPage);
    }

    @Override
    public int getNumberOfPages(int sizeOfPage) {
        return clientDao.getNumberOfPages(sizeOfPage);
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

        String roleCast = clientDto.getClient().getUserRole().replace(",", "");
        client.setUserRole(roleCast);

        String password = clientDto.getClient().getPasswordLogIn();
        if (clientDto.getClient().getPasswordLogIn().length() < 255) {
            String encodedPassword = new BCryptPasswordEncoder().encode(clientDto.getClient().getPasswordLogIn());
            clientDto.getClient().setPasswordLogIn(encodedPassword);
        } else throw new ServiceLayerException("Password which you input is to long");

        clientDao.save(client);
        sendPasswordToNewUser(client.getEmailAddress(), password, client.getFirstName());
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
    public Client get(int id) {
        return clientDao.get(id);
    }

    @Override
    public Client getByEmail(String email) {
        return clientDao.getByEmail(email);
    }

    @Override
    public void addMoney(ClientDto clientDto) {
        double moneyAmount = clientDto.getClient().getMoneyBalance();
        moneyAmount += clientDto.getMoney();
        clientDto.getClient().setMoneyBalance(moneyAmount);
        update(clientDto);
    }

    @Override
    public void sendPasswordToNewUser(String recipientEmail, String password, String name) {
        Properties prop = new Properties();

        try {
            prop.load(ClientServiceImpl.class.getClassLoader().getResourceAsStream("mail.properties"));
            Session session = Session.getInstance(prop, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(prop.getProperty("email"), prop.getProperty("password"));
                }
            });
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("mr.kustik@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Your account in e-care have been creating");
            String msg = "Dear " + name + ", account for e-care project on this email were created.\rYour credentials:\n" +
                    "login: " + recipientEmail + "\rPassword: " + password + "\r" +
                    "If you get this message, but you don't ask about it. Sorry you address " +
                    "accidentally concur with input data in school project. Sorry again:)";
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            message.setContent(multipart);
            Transport.send(message);
        } catch (MessagingException | IOException e) {
            throw new ServiceLayerException("Can't add new user e-mail couldn't be send -" + e.getMessage());
        }

    }

    @Override
    public String createInputPassword() {
        StringBuilder password = new StringBuilder();
        Random random = new Random();

        String setOfCharacters = "abcdefghxyz1234567-/@";
        for (int i = 0; i < 6; i++) {
            int randomInt = random.nextInt(setOfCharacters.length());
            password.append(setOfCharacters.charAt(randomInt));
        }

        return password.toString();
    }

    @Override
    public void delete(int id) {

        if (id == 1)
            throw new ServiceLayerException("You can't delete superadmin!");

        Client client = get(id);

        if (client.getContractClient().size() > 0) {
            throw new ServiceLayerException("Can't delete user with existing contract. Before " +
                    "deleting user end contract with him");
        }
        clientDao.delete(id);
    }
}
