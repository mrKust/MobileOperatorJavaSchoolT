package com.school.services.tests;

import com.school.database.dao.impl.ClientDaoImpl;
import com.school.database.entity.Client;
import com.school.database.entity.Contract;
import com.school.service.impl.ClientServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceMockTests {

    private Client client1 = new Client();
    private Client client2 = new Client();

    private List<Client> allClients = new ArrayList<>();

    @Mock
    ClientDaoImpl clientDao;

    @InjectMocks
    ClientServiceImpl clientService;

    @Before
    public void init() {
        client1.setId(1);
        client1.setFirstName("Dmitry");
        client1.setSurname("Vasilyev");
        client1.setDateOfBirth("08-18-1999");
        client1.setPassportNumber("9192354657");
        client1.setAddress("Naberejnaya st. 28, 93");
        client1.setEmailAddress("dimvas2010@yandex.ru");
        client1.setPasswordLogIn("qwerty");
        client1.setContractClient(new ArrayList<Contract>());
        client1.setUserRole("control");
        client1.setMoneyBalance(1000.00);

        client2.setId(2);
        client2.setFirstName("Sergei");
        client2.setSurname("Ivanov");
        client2.setDateOfBirth("11-12-1972");
        client2.setPassportNumber("9192354657");
        client2.setAddress("Kolotushkina st. 82, 39");
        client2.setEmailAddress("ivanov@yandex.ru");
        client2.setPasswordLogIn("qaz");
        client2.setContractClient(new ArrayList<Contract>());
        client2.setUserRole("client");
        client2.setMoneyBalance(500.50);

        allClients.add(client1);
        allClients.add(client2);
    }

    @Test
    public void getAllClientsTest1() {
        Mockito.when(clientDao.getAll()).thenReturn(allClients);
        List<Client> result = clientService.getAll();

        Mockito.verify(clientDao).getAll();
        Assert.assertNotNull(result);
        Assert.assertEquals(allClients.size(), result.size());
        for (int i = 0; i < result.size(); i++) {
            Client expectedClient = allClients.get(i);
            Client resultClient = result.get(i);
            Assert.assertEquals(expectedClient.getId(), resultClient.getId());
            Assert.assertEquals(expectedClient.getFirstName(), resultClient.getFirstName());
            Assert.assertEquals(expectedClient.getSurname(), resultClient.getSurname());
            Assert.assertEquals(expectedClient.getDateOfBirth(), resultClient.getDateOfBirth());
            Assert.assertEquals(expectedClient.getPassportNumber(), resultClient.getPassportNumber());
            Assert.assertEquals(expectedClient.getAddress(), resultClient.getAddress());
            Assert.assertEquals(expectedClient.getEmailAddress(), resultClient.getEmailAddress());
            Assert.assertEquals(expectedClient.getPasswordLogIn(), resultClient.getPasswordLogIn());
            Assert.assertEquals(expectedClient.getContractClient(), resultClient.getContractClient());
            Assert.assertEquals(expectedClient.getUserRole(), resultClient.getUserRole());
            Assert.assertEquals(String.valueOf(expectedClient.getMoneyBalance()), String.valueOf(resultClient.getMoneyBalance()));
        }
    }
}
