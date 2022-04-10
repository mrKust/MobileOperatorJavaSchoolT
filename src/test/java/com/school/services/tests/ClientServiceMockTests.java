package com.school.services.tests;

import com.school.customException.ServiceLayerException;
import com.school.database.dao.impl.ClientDaoImpl;
import com.school.database.entity.Client;
import com.school.database.entity.Contract;
import com.school.database.entity.Tariff;
import com.school.dto.ClientDto;
import com.school.dto.TariffDto;
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

    @Test
    public void getPageOfClientsTest1() {
        Mockito.when(clientDao.getPageOfClients(5, "surname", 1)).thenReturn(allClients);
        ClientDto clientDto = new ClientDto();
        List<Client> result = clientService.getPageOfClients(clientDto, 1);
        Assert.assertNotNull(result);
        Assert.assertEquals(allClients.size(), result.size());
    }

    @Test
    public void getNumberOfPagesTest1() {
        Mockito.when(clientDao.getPageOfClients(5, "surname", 1)).thenReturn(allClients);
        int result = clientService.getNumberOfPages(5);

        Assert.assertNotEquals(0, result);
        Assert.assertEquals(1, result);
    }

    @Test
    public void checkUserEmailToUniqueTest1() {
        Mockito.when(clientDao.checkUserEmailToUnique(client1)).thenReturn(true);
        boolean result = clientService.checkUserEmailToUnique(client1);

        Mockito.verify(clientDao).checkUserEmailToUnique(client1);
        Assert.assertTrue(result);
    }

    @Test
    public void saveClientTest1() {
        Mockito.when(clientDao.checkUserEmailToUnique(client1)).thenReturn(true);
        ClientDto clientDto = new ClientDto();
        clientDto.setClient(client1);

        clientService.save(clientDto);
        Mockito.verify(clientDao).save(client1);
    }

    @Test
    public void updateClientTest1() {
        Mockito.when(clientDao.get(client1.getId())).thenReturn(client1);
        ClientDto clientDto = new ClientDto();
        clientDto.setClient(client1);

        clientService.update(clientDto);
        Mockito.verify(clientDao).save(client1);
    }

    @Test(expected = ServiceLayerException.class)
    public void updateClientTest2() {
        Mockito.when(clientDao.get(client1.getId())).thenReturn(client1);
        ClientDto clientDto = new ClientDto();
        clientDto.setClient(client1);
        clientDto.setPasswordString("qwerty");
        clientDto.setPasswordString2("kek");

        clientService.update(clientDto);
        Mockito.verify(clientDao).save(client1);
    }

    @Test
    public void getClientTest1() {
        Mockito.when(clientDao.get(client1.getId())).thenReturn(client1);
        Client result = clientService.get(client1.getId());

        Mockito.verify(clientDao).get(client1.getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(client1.getId(), result.getId());
        Assert.assertEquals(client1.getFirstName(), result.getFirstName());
        Assert.assertEquals(client1.getSurname(), result.getSurname());
        Assert.assertEquals(client1.getDateOfBirth(), result.getDateOfBirth());
        Assert.assertEquals(client1.getPassportNumber(), result.getPassportNumber());
        Assert.assertEquals(client1.getAddress(), result.getAddress());
        Assert.assertEquals(client1.getEmailAddress(), result.getEmailAddress());
        Assert.assertEquals(client1.getPasswordLogIn(), result.getPasswordLogIn());
        Assert.assertEquals(client1.getContractClient(), result.getContractClient());
        Assert.assertEquals(client1.getUserRole(), result.getUserRole());
        Assert.assertEquals(String.valueOf(client1.getMoneyBalance()), String.valueOf(result.getMoneyBalance()));
    }

    @Test
    public void getByEmailTest1() {
        Mockito.when(clientDao.getByEmail(client1.getEmailAddress())).thenReturn(client1);
        Client result = clientService.getByEmail(client1.getEmailAddress());

        Mockito.verify(clientDao).getByEmail(client1.getEmailAddress());
        Assert.assertNotNull(result);
        Assert.assertEquals(client1.getId(), result.getId());
        Assert.assertEquals(client1.getFirstName(), result.getFirstName());
        Assert.assertEquals(client1.getSurname(), result.getSurname());
        Assert.assertEquals(client1.getDateOfBirth(), result.getDateOfBirth());
        Assert.assertEquals(client1.getPassportNumber(), result.getPassportNumber());
        Assert.assertEquals(client1.getAddress(), result.getAddress());
        Assert.assertEquals(client1.getEmailAddress(), result.getEmailAddress());
        Assert.assertEquals(client1.getPasswordLogIn(), result.getPasswordLogIn());
        Assert.assertEquals(client1.getContractClient(), result.getContractClient());
        Assert.assertEquals(client1.getUserRole(), result.getUserRole());
        Assert.assertEquals(String.valueOf(client1.getMoneyBalance()), String.valueOf(result.getMoneyBalance()));
    }

    @Test
    public void addMoneyTest1() {
        ClientDto clientDto = new ClientDto();
        clientDto.setClient(client1);
        clientDto.setMoney(1000);
        Mockito.when(clientDao.get(client1.getId())).thenReturn(client1);
        clientService.addMoney(clientDto);
        Mockito.verify(clientDao).save(client1);
    }

    @Test
    public void createInputPasswordTest1() {
        String result = clientService.createInputPassword();

        Assert.assertNotNull(result);
        Assert.assertEquals(6, result.length());
    }

    @Test(expected = ServiceLayerException.class)
    public void deleteClientTest1() {
        clientService.delete(client1.getId());

        Mockito.verify(clientDao).delete(client1.getId());
    }

    @Test(expected = ServiceLayerException.class)
    public void deleteClientTest2() {
        client1.setId(3);
        List<Contract> contractList = new ArrayList<>();
        contractList.add(new Contract());
        client1.setContractClient(contractList);
        Mockito.when(clientDao.get(client1.getId())).thenReturn(client1);
        clientService.delete(client1.getId());

        Mockito.verify(clientDao).delete(client1.getId());
    }

    @Test
    public void deleteClientTest3() {
        client1.setId(3);
        Mockito.when(clientDao.get(client1.getId())).thenReturn(client1);
        clientService.delete(client1.getId());

        Mockito.verify(clientDao).delete(client1.getId());
    }
}
