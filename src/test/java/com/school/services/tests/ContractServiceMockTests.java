package com.school.services.tests;

import com.school.customException.ServiceLayerException;
import com.school.database.dao.impl.ContractDaoImpl;
import com.school.database.entity.*;
import com.school.database.entity.Number;
import com.school.dto.ClientDto;
import com.school.dto.ContractDto;
import com.school.service.impl.*;
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
public class ContractServiceMockTests {

    Contract contract1 = new Contract();
    Contract contract2 = new Contract();

    List<Contract> allContracts = new ArrayList<>();

    @Mock
    ContractDaoImpl contractDao;

    @Mock
    TariffServiceImpl tariffService;

    @Mock
    ClientServiceImpl clientService;

    @Mock
    OptionsServiceImpl optionsService;

    @Mock
    NumberServiceImpl numberService;

    @InjectMocks
    ContractServiceImpl contractService;

    @Before
    public void init() {
        contract1.setId(1);
        contract1.setPhoneNumber("89115396452");
        contract1.setContractBlockStatus(false);
        contract1.setRoleOfUserWhoBlockedContract(null);
        contract1.setPriceForContractPerMonth(300);
        contract1.setContractTariff(new Tariff());
        contract1.setContractClient(new Client());
        contract1.setConnectedOptions(new ArrayList<>());

        contract2.setId(2);
        contract2.setPhoneNumber("89217232415");
        contract2.setContractBlockStatus(true);
        contract2.setRoleOfUserWhoBlockedContract("control");
        contract2.setPriceForContractPerMonth(1000);
        contract2.setContractTariff(new Tariff());
        contract2.setContractClient(new Client());
        contract2.setConnectedOptions(new ArrayList<>());

        allContracts.add(contract1);
        allContracts.add(contract2);
    }

    @Test
    public void getAllContractsTest1() {
        Mockito.when(contractDao.getAll()).thenReturn(allContracts);
        List<Contract> result = contractService.getAll();

        Assert.assertNotNull(result);
        Assert.assertEquals(allContracts.size(), result.size());
        for (int i = 0; i < result.size(); i++) {
            Contract expectedContract = allContracts.get(i);
            Contract resultContract = result.get(i);
            Assert.assertEquals(expectedContract.getId(), resultContract.getId());
            Assert.assertEquals(expectedContract.getPhoneNumber(), resultContract.getPhoneNumber());
            Assert.assertEquals(expectedContract.isContractBlockStatus(), resultContract.isContractBlockStatus());
            Assert.assertEquals(expectedContract.getRoleOfUserWhoBlockedContract(), resultContract.getRoleOfUserWhoBlockedContract());
            Assert.assertEquals(String.valueOf(expectedContract.getPriceForContractPerMonth()), String.valueOf(resultContract.getPriceForContractPerMonth()));
            Assert.assertEquals(expectedContract.getContractTariff(), resultContract.getContractTariff());
            Assert.assertEquals(expectedContract.getContractClient(), resultContract.getContractClient());
            Assert.assertEquals(expectedContract.getConnectedOptions(), resultContract.getConnectedOptions());
        }
    }

    @Test
    public void getAllContractsOfClientTest1() {
        Client client = new Client();
        client.setEmailAddress("example@kek.ru");
        contract1.setContractClient(client);
        contract2.setContractClient(client);

        Mockito.when(contractDao.getAllContractsOfClient(client.getEmailAddress())).thenReturn(allContracts);
        List<Contract> result = contractService.getAllContractsOfClient(client.getEmailAddress());

        Assert.assertNotNull(result);
        Assert.assertEquals(allContracts.size(), result.size());
        for (int i = 0; i < result.size(); i++) {
            Contract expectedContract = allContracts.get(i);
            Contract resultContract = result.get(i);
            Assert.assertEquals(expectedContract.getId(), resultContract.getId());
            Assert.assertEquals(expectedContract.getPhoneNumber(), resultContract.getPhoneNumber());
            Assert.assertEquals(expectedContract.isContractBlockStatus(), resultContract.isContractBlockStatus());
            Assert.assertEquals(expectedContract.getRoleOfUserWhoBlockedContract(), resultContract.getRoleOfUserWhoBlockedContract());
            Assert.assertEquals(String.valueOf(expectedContract.getPriceForContractPerMonth()), String.valueOf(resultContract.getPriceForContractPerMonth()));
            Assert.assertEquals(expectedContract.getContractTariff(), resultContract.getContractTariff());
            Assert.assertEquals(expectedContract.getContractClient(), resultContract.getContractClient());
            Assert.assertEquals(expectedContract.getConnectedOptions(), resultContract.getConnectedOptions());
        }
    }

    @Test
    public void getPageOfContractsTest1() {
        Mockito.when(contractDao.getPageOfContracts(5, "phoneNumber", 1)).thenReturn(allContracts);
        ContractDto contractDto = new ContractDto();
        List<Contract> result =contractService.getPageOfContracts(contractDto, 1);
        Assert.assertNotNull(result);
        Assert.assertEquals(allContracts.size(), result.size());
    }

    @Test
    public void getNumberOfPagesTest1() {
        Mockito.when(contractDao.getNumberOfPages(5)).thenReturn(1);
        int result = contractService.getNumberOfPages(5);

        Assert.assertNotEquals(0, result);
        Assert.assertEquals(1, result);
    }

    @Test
    public void checkOptionsComboToRightTest1() {
        List<Options> optionsList = new ArrayList<>();
        OptionType optionType1 = new OptionType();
        optionType1.setId(1);
        OptionType optionType2 = new OptionType();
        optionType2.setId(1);
        Options options1 = new Options();
        options1.setOptionType(optionType1);
        Options options2 = new Options();
        options2.setOptionType(optionType2);
        optionsList.add(options1);
        optionsList.add(options2);

        boolean result = contractService.checkOptionsComboToRight(optionsList);
        Assert.assertFalse(result);
    }

    @Test
    public void checkOptionsComboToRightTest2() {
        List<Options> optionsList = new ArrayList<>();
        OptionType optionType1 = new OptionType();
        optionType1.setId(1);
        OptionType optionType2 = new OptionType();
        optionType2.setId(2);
        Options options1 = new Options();
        options1.setOptionType(optionType1);
        Options options2 = new Options();
        options2.setOptionType(optionType2);
        optionsList.add(options1);
        optionsList.add(options2);

        boolean result = contractService.checkOptionsComboToRight(optionsList);
        Assert.assertTrue(result);
    }

    @Test
    public void saveContractTest1() {
        ContractDto contractDto = new ContractDto();
        contractDto.setContract(contract1);
        String[] stringsTariff = new String[1];
        stringsTariff[0] = String.valueOf(1);
        contractDto.setStringsTariff(stringsTariff);
        Tariff tariff = new Tariff();
        tariff.setId(1);
        tariff.setPrice(100);
        Mockito.when(tariffService.get(1)).thenReturn(tariff);
        Number number = new Number();
        number.setAvailableToConnectStatus(true);
        Mockito.when(numberService.getByPhoneNumber(contract1.getPhoneNumber())).thenReturn(number);

        contractService.save(contractDto);
        Mockito.verify(contractDao).save(contractDto.getContract());
    }

    @Test(expected = ServiceLayerException.class)
    public void saveContractTest2() {
        ContractDto contractDto = new ContractDto();
        contractDto.setContract(contract1);
        String[] stringsTariff = new String[1];
        stringsTariff[0] = String.valueOf(1);
        contractDto.setStringsTariff(stringsTariff);
        Tariff tariff = new Tariff();
        tariff.setId(1);
        tariff.setPrice(100);
        Mockito.when(tariffService.get(1)).thenReturn(tariff);
        Number number = new Number();
        number.setAvailableToConnectStatus(false);
        Mockito.when(numberService.getByPhoneNumber(contract1.getPhoneNumber())).thenReturn(number);

        contractService.save(contractDto);
        Mockito.verify(contractDao).save(contractDto.getContract());
    }

    @Test
    public void saveContractTest3() {
        ContractDto contractDto = new ContractDto();
        contractDto.setContract(contract1);
        String[] stringsTariff = new String[1];
        stringsTariff[0] = String.valueOf(1);
        contractDto.setStringsTariff(stringsTariff);
        Tariff tariff = new Tariff();
        tariff.setId(1);
        tariff.setPrice(100);
        Mockito.when(tariffService.get(1)).thenReturn(tariff);
        String[] stringsClient = new String[1];
        stringsClient[0] = String.valueOf(1);
        contractDto.setStringsClients(stringsClient);
        Mockito.when(clientService.get(1)).thenReturn(new Client());
        Number number = new Number();
        number.setAvailableToConnectStatus(true);
        Mockito.when(numberService.getByPhoneNumber(contract1.getPhoneNumber())).thenReturn(number);

        contractService.save(contractDto);
        Mockito.verify(contractDao).save(contractDto.getContract());
    }

    @Test(expected = ServiceLayerException.class)
    public void updateContractTest1() {
        ContractDto contractDto = new ContractDto();
        contractDto.setContract(contract1);
        Mockito.when(contractDao.get(contractDto.getContract().getId())).thenReturn(contractDto.getContract());
        String[] stringsTariff = new String[1];
        stringsTariff[0] = String.valueOf(1);
        contractDto.setStringsTariff(stringsTariff);
        Tariff tariff = new Tariff();
        tariff.setId(1);
        tariff.setPrice(100);
        Mockito.when(tariffService.get(1)).thenReturn(tariff);

        List<Options> optionsList = new ArrayList<>();
        OptionType optionType = new OptionType();
        optionType.setId(1);
        Options options1 = new Options();
        options1.setOptionType(optionType);
        Options options2 = new Options();
        options2.setOptionType(optionType);
        optionsList.add(options1);
        optionsList.add(options2);
        contractDto.getContract().setConnectedOptions(optionsList);
        List<Integer> optionsIds = new ArrayList<>();
        optionsIds.add(options1.getId());
        optionsIds.add(options2.getId());
        String[] optionStrings = new String[2];
        optionStrings[0] = String.valueOf(options1.getId());
        optionStrings[1] = String.valueOf(options2.getId());
        contractDto.setStringsOptions(optionStrings);
        Mockito.when(optionsService.getOptionsFromChosenList(optionsIds)).thenReturn(optionsList);

        contractService.update(contractDto);
        Mockito.verify(contractDao).save(contractDto.getContract());
    }

    @Test
    public void updateContractTest2() {
        ContractDto contractDto = new ContractDto();
        contractDto.setContract(contract1);
        Mockito.when(contractDao.get(contractDto.getContract().getId())).thenReturn(contractDto.getContract());
        String[] stringsTariff = new String[1];
        stringsTariff[0] = String.valueOf(1);
        contractDto.setStringsTariff(stringsTariff);
        Tariff tariff = new Tariff();
        tariff.setId(1);
        tariff.setPrice(100);
        Mockito.when(tariffService.get(1)).thenReturn(tariff);

        List<Options> optionsList = new ArrayList<>();
        OptionType optionType = new OptionType();
        optionType.setId(1);
        OptionType optionType2 = new OptionType();
        optionType.setId(2);
        Options options1 = new Options();
        options1.setOptionType(optionType);
        Options options2 = new Options();
        options2.setOptionType(optionType2);
        optionsList.add(options1);
        optionsList.add(options2);
        contractDto.getContract().setConnectedOptions(optionsList);
        List<Integer> optionsIds = new ArrayList<>();
        optionsIds.add(options1.getId());
        optionsIds.add(options2.getId());
        String[] optionStrings = new String[2];
        optionStrings[0] = String.valueOf(options1.getId());
        optionStrings[1] = String.valueOf(options2.getId());
        contractDto.setStringsOptions(optionStrings);
        Mockito.when(optionsService.getOptionsFromChosenList(optionsIds)).thenReturn(optionsList);

        contractService.update(contractDto);
        Mockito.verify(contractDao).save(contractDto.getContract());
    }

    @Test
    public void updateContractTest3() {
        ContractDto contractDto = new ContractDto();
        contractDto.setContract(contract1);
        Mockito.when(contractDao.get(contractDto.getContract().getId())).thenReturn(contractDto.getContract());
        String[] stringsTariff = new String[1];
        stringsTariff[0] = String.valueOf(1);
        contractDto.setStringsTariff(stringsTariff);
        Tariff tariff = new Tariff();
        tariff.setId(1);
        tariff.setPrice(100);
        Mockito.when(tariffService.get(1)).thenReturn(tariff);

        List<Options> optionsList = new ArrayList<>();
        OptionType optionType = new OptionType();
        optionType.setId(1);
        OptionType optionType2 = new OptionType();
        optionType.setId(2);
        Options options1 = new Options();
        options1.setOptionType(optionType);
        Options options2 = new Options();
        options2.setOptionType(optionType2);
        optionsList.add(options1);
        optionsList.add(options2);
        contractDto.getContract().setConnectedOptions(optionsList);

        contractService.update(contractDto);
        Mockito.verify(contractDao).save(contractDto.getContract());
    }

    @Test
    public void lockContractTest1() {
        ContractDto contractDto = new ContractDto();
        contractDto.setId(contract1.getId());
        contractDto.setBlockedRole("client");
        Mockito.when(contractDao.get(contract1.getId())).thenReturn(contract1);

        contractService.lock(contractDto);
        Mockito.verify(contractDao).save(contract1);
    }

    @Test
    public void unlockContractTest1() {
        ContractDto contractDto = new ContractDto();
        contractDto.setId(contract1.getId());
        Mockito.when(contractDao.get(contract1.getId())).thenReturn(contract1);

        contractService.unlock(contractDto);
        Mockito.verify(contractDao).save(contract1);
    }

    @Test
    public void countPricePerMonthTest1() {
        Client client = new Client();
        client.setMoneyBalance(100);
        contract1.setContractClient(client);
        contract1.getContractTariff().setPrice(10);
        List<Options> optionsList = new ArrayList<>();
        OptionType optionType = new OptionType();
        optionType.setId(1);
        OptionType optionType2 = new OptionType();
        optionType.setId(2);
        Options options1 = new Options();
        options1.setOptionType(optionType);
        options1.setPrice(10);
        options1.setCostToAdd(10);
        Options options2 = new Options();
        options2.setOptionType(optionType2);
        options2.setPrice(10);
        options2.setCostToAdd(10);
        optionsList.add(options1);
        optionsList.add(options2);
        contract1.setConnectedOptions(optionsList);
        Mockito.when(contractDao.get(contract1.getId())).thenReturn(contract1);
        double result = contractService.countPricePerMonth(contract1);

        Assert.assertEquals(String.valueOf(30.0), String.valueOf(result));
    }

    @Test(expected = ServiceLayerException.class)
    public void countPricePerMonthTest2() {
        Client client = new Client();
        client.setMoneyBalance(0);
        contract1.setContractClient(client);
        contract1.getContractTariff().setPrice(10);
        List<Options> optionsList = new ArrayList<>();
        OptionType optionType = new OptionType();
        optionType.setId(1);
        OptionType optionType2 = new OptionType();
        optionType.setId(2);
        Options options1 = new Options();
        options1.setOptionType(optionType);
        options1.setPrice(10);
        options1.setCostToAdd(10);
        Options options2 = new Options();
        options2.setOptionType(optionType2);
        options2.setPrice(10);
        options2.setCostToAdd(10);
        optionsList.add(options1);
        optionsList.add(options2);
        contract1.setConnectedOptions(optionsList);
        Mockito.when(contractDao.get(contract1.getId())).thenReturn(contract2);
        double result = contractService.countPricePerMonth(contract1);

        Assert.assertEquals(String.valueOf(30.0), String.valueOf(result));
    }

    @Test
    public void optionsAlreadyConnectedToContractTest1() {
        int id = 1;
        List<Options> optionsList = new ArrayList<>();
        Options options1 = new Options();
        options1.setId(1);
        Options options2 = new Options();
        options2.setId(2);
        optionsList.add(options1);
        optionsList.add(options2);
        boolean result = contractService.optionsAlreadyConnectedToContract(id, optionsList);
        Assert.assertTrue(result);
    }

    @Test
    public void optionsAlreadyConnectedToContractTest2() {
        int id = 3;
        List<Options> optionsList = new ArrayList<>();
        Options options1 = new Options();
        options1.setId(1);
        Options options2 = new Options();
        options2.setId(2);
        optionsList.add(options1);
        optionsList.add(options2);
        boolean result = contractService.optionsAlreadyConnectedToContract(id, optionsList);
        Assert.assertFalse(result);
    }

    @Test
    public void getContractTest1() {
        Mockito.when(contractDao.get(contract1.getId())).thenReturn(contract1);
        Contract result = contractService.get(contract1.getId());

        Assert.assertNotNull(result);
        Assert.assertEquals(contract1.getId(), result.getId());
        Assert.assertEquals(contract1.getPhoneNumber(), result.getPhoneNumber());
        Assert.assertEquals(contract1.isContractBlockStatus(), result.isContractBlockStatus());
        Assert.assertEquals(contract1.getRoleOfUserWhoBlockedContract(), result.getRoleOfUserWhoBlockedContract());
        Assert.assertEquals(String.valueOf(contract1.getPriceForContractPerMonth()), String.valueOf(result.getPriceForContractPerMonth()));
        Assert.assertEquals(contract1.getContractTariff(), result.getContractTariff());
        Assert.assertEquals(contract1.getContractClient(), result.getContractClient());
        Assert.assertEquals(contract1.getConnectedOptions(), result.getConnectedOptions());

    }

    @Test
    public void getByPhoneNumberTest1() {
        Mockito.when(contractDao.getByPhoneNumber(contract1.getPhoneNumber())).thenReturn(contract1);
        Contract result = contractService.getByPhoneNumber(contract1.getPhoneNumber());

        Assert.assertNotNull(result);
        Assert.assertEquals(contract1.getId(), result.getId());
        Assert.assertEquals(contract1.getPhoneNumber(), result.getPhoneNumber());
        Assert.assertEquals(contract1.isContractBlockStatus(), result.isContractBlockStatus());
        Assert.assertEquals(contract1.getRoleOfUserWhoBlockedContract(), result.getRoleOfUserWhoBlockedContract());
        Assert.assertEquals(String.valueOf(contract1.getPriceForContractPerMonth()), String.valueOf(result.getPriceForContractPerMonth()));
        Assert.assertEquals(contract1.getContractTariff(), result.getContractTariff());
        Assert.assertEquals(contract1.getContractClient(), result.getContractClient());
        Assert.assertEquals(contract1.getConnectedOptions(), result.getConnectedOptions());
    }

    @Test
    public void deleteContractTest1() {
        Mockito.when(contractDao.get(contract1.getId())).thenReturn(contract1);
        Mockito.when(numberService.getByPhoneNumber(contract1.getPhoneNumber())).thenReturn(new Number());
        contractService.delete(contract1.getId());

        Mockito.verify(contractDao).delete(contract1.getId());
    }


}
