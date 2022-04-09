package com.school.services.tests;

import com.school.customException.ServiceLayerException;
import com.school.database.dao.impl.OptionsDaoImpl;
import com.school.database.entity.Contract;
import com.school.database.entity.OptionType;
import com.school.database.entity.Options;
import com.school.database.entity.Tariff;
import com.school.dto.OptionsDto;
import com.school.service.impl.OptionTypeServiceImpl;
import com.school.service.impl.OptionsServiceImpl;
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
public class OptionsServiceMockTests {

    private Options options1 = new Options();
    private Options options2 = new Options();

    private List<Options> allOptions = new ArrayList<>();
    private List<Options> allAvailableToConnectOptions = new ArrayList<>();
    private List<Options> allUnavailableToConnectOptions = new ArrayList<>();

    @Mock
    OptionsDaoImpl optionsDao;

    @Mock
    OptionTypeServiceImpl optionTypeService;

    @InjectMocks
    OptionsServiceImpl optionsService;

    @Before
    public void init() {
        options1.setId(1);
        options1.setOptionsName("Option1");
        options1.setPrice(1.50);
        options1.setCostToAdd(0.50);
        options1.setAvailableForTariffs(new ArrayList<Tariff>());
        options1.setConnectedToContracts(new ArrayList<Contract>());
        options1.setAvailableOptionToConnectOrNot(true);
        options1.setOptionType(new OptionType());

        options2.setId(2);
        options2.setOptionsName("Option2");
        options2.setPrice(2.25);
        options2.setCostToAdd(1.15);
        options2.setAvailableForTariffs(new ArrayList<Tariff>());
        options2.setConnectedToContracts(new ArrayList<Contract>());
        options2.setAvailableOptionToConnectOrNot(false);
        options2.setOptionType(new OptionType());

        allOptions.add(options1);
        allOptions.add(options2);

        allAvailableToConnectOptions.add(options1);

        allUnavailableToConnectOptions.add(options2);
    }

    @Test
    public void getAllOptionsTest1() {
        Mockito.when(optionsDao.getAll()).thenReturn(allOptions);
        List<Options> result = optionsService.getAll();

        Mockito.verify(optionsDao).getAll();
        Assert.assertNotNull(result);
        Assert.assertEquals(allOptions.size(), result.size());
        for (int i = 0; i < result.size(); i++) {
            Options expectedOptions = allOptions.get(i);
            Options resultOptions = result.get(i);
            Assert.assertEquals(expectedOptions.getId(), resultOptions.getId());
            Assert.assertEquals(expectedOptions.getOptionsName(), resultOptions.getOptionsName());
            Assert.assertEquals(String.valueOf(expectedOptions.getPrice()), String.valueOf(resultOptions.getPrice()));
            Assert.assertEquals(String.valueOf(expectedOptions.getCostToAdd()), String.valueOf(resultOptions.getCostToAdd()));
            Assert.assertEquals(expectedOptions.isAvailableOptionToConnectOrNot(), resultOptions.isAvailableOptionToConnectOrNot());
            Assert.assertEquals(expectedOptions.getOptionType(), resultOptions.getOptionType());
            Assert.assertEquals(expectedOptions.getConnectedToContracts(), resultOptions.getConnectedToContracts());
            Assert.assertEquals(expectedOptions.getAvailableForTariffs(), resultOptions.getAvailableForTariffs());
        }
    }

    @Test
    public void getAllAvailableOptionsTest1() {
        Mockito.when(optionsDao.getAllAvailable()).thenReturn(allAvailableToConnectOptions);
        List<Options> result = optionsService.getAllAvailable();

        Mockito.verify(optionsDao).getAllAvailable();
        Assert.assertNotNull(result);
        Assert.assertEquals(allAvailableToConnectOptions.size(), result.size());
        for (int i = 0; i < result.size(); i++) {
            Options expectedOptions = allAvailableToConnectOptions.get(i);
            Options resultOptions = result.get(i);
            Assert.assertEquals(expectedOptions.getId(), resultOptions.getId());
            Assert.assertEquals(expectedOptions.getOptionsName(), resultOptions.getOptionsName());
            Assert.assertEquals(String.valueOf(expectedOptions.getPrice()), String.valueOf(resultOptions.getPrice()));
            Assert.assertEquals(String.valueOf(expectedOptions.getCostToAdd()), String.valueOf(resultOptions.getCostToAdd()));
            Assert.assertEquals(expectedOptions.isAvailableOptionToConnectOrNot(), resultOptions.isAvailableOptionToConnectOrNot());
            Assert.assertEquals(expectedOptions.getOptionType(), resultOptions.getOptionType());
            Assert.assertEquals(expectedOptions.getConnectedToContracts(), resultOptions.getConnectedToContracts());
            Assert.assertEquals(expectedOptions.getAvailableForTariffs(), resultOptions.getAvailableForTariffs());
        }
    }

    @Test
    public void getOptionsFromChosenListTest1() {
        List<Integer> optionsIdsList = new ArrayList<>();
        for (int i = 0; i < allUnavailableToConnectOptions.size(); i++) {
            optionsIdsList.add(allUnavailableToConnectOptions.get(i).getId());
        }
        Mockito.when(optionsDao.getOptionsFromChosenList(optionsIdsList)).thenReturn(allUnavailableToConnectOptions);
        List<Options> result = optionsService.getOptionsFromChosenList(optionsIdsList);

        Mockito.verify(optionsDao).getOptionsFromChosenList(optionsIdsList);
        Assert.assertNotNull(result);
        Assert.assertEquals(allUnavailableToConnectOptions.size(), result.size());
        for (int i = 0; i < result.size(); i++) {
            Options expectedOptions = allUnavailableToConnectOptions.get(i);
            Options resultOptions = result.get(i);
            Assert.assertEquals(expectedOptions.getId(), resultOptions.getId());
            Assert.assertEquals(expectedOptions.getOptionsName(), resultOptions.getOptionsName());
            Assert.assertEquals(String.valueOf(expectedOptions.getPrice()), String.valueOf(resultOptions.getPrice()));
            Assert.assertEquals(String.valueOf(expectedOptions.getCostToAdd()), String.valueOf(resultOptions.getCostToAdd()));
            Assert.assertEquals(expectedOptions.isAvailableOptionToConnectOrNot(), resultOptions.isAvailableOptionToConnectOrNot());
            Assert.assertEquals(expectedOptions.getOptionType(), resultOptions.getOptionType());
            Assert.assertEquals(expectedOptions.getConnectedToContracts(), resultOptions.getConnectedToContracts());
            Assert.assertEquals(expectedOptions.getAvailableForTariffs(), resultOptions.getAvailableForTariffs());
        }
    }

    @Test
    public void getAvailableOptionsNamesAndPricesTest1() {
        List<Integer> optionsIdsList = new ArrayList<>();
        for (int i = 0; i < allUnavailableToConnectOptions.size(); i++) {
            optionsIdsList.add(allUnavailableToConnectOptions.get(i).getId());
        }
        Mockito.when(optionsDao.getOptionsFromChosenList(optionsIdsList)).thenReturn(allUnavailableToConnectOptions);
        List<Options> result = optionsService.getOptionsFromChosenList(optionsIdsList);

        Mockito.verify(optionsDao).getOptionsFromChosenList(optionsIdsList);
        Assert.assertNotNull(result);
        Assert.assertEquals(allUnavailableToConnectOptions.size(), result.size());
        for (int i = 0; i < result.size(); i++) {
            Options expectedOptions = allUnavailableToConnectOptions.get(i);
            Options resultOptions = result.get(i);
            Assert.assertEquals(expectedOptions.getId(), resultOptions.getId());
            Assert.assertEquals(expectedOptions.getOptionsName(), resultOptions.getOptionsName());
        }
    }

    @Test
    public void getPageOfOptionsTest1() {
        Mockito.when(optionsDao.getPageOfOptions(5, "optionsName", 1)).thenReturn(allOptions);
        OptionsDto optionsDto = new OptionsDto();
        List<Options> result = optionsService.getPageOfOptions(optionsDto, 1);
        Assert.assertNotNull(result);
        Assert.assertEquals(allOptions.size(), result.size());
    }

    @Test
    public void getNumberOfPagesTest1() {
        Mockito.when(optionsDao.getNumberOfPages(5)).thenReturn(1);
        int result = optionsService.getNumberOfPages(5);

        Assert.assertNotEquals(0, result);
        Assert.assertEquals(1, result);
    }

    @Test
    public void saveOptionTest1() {
        OptionsDto optionsDto = new OptionsDto();
        Options optionsTmp = new Options();
        optionsTmp.setOptionsName("OptionsTmp");
        optionsDto.setOptions(optionsTmp);
        String[] chosenOptionType = new String[1];
        chosenOptionType[0] = "1";
        optionsDto.setStringOptionCategory(chosenOptionType);
        Mockito.when(optionTypeService.get(Integer.parseInt(chosenOptionType[0]))).thenReturn(new OptionType());

        optionsService.save(optionsDto);

        Mockito.verify(optionsDao).save(optionsTmp);
    }

    @Test
    public void updateOptionTest1() {
        OptionsDto optionsDto = new OptionsDto();
        Options optionsTmp = new Options();
        optionsTmp.setId(1);
        optionsTmp.setOptionsName("OptionsTmp");
        optionsDto.setOptions(optionsTmp);
        Mockito.when(optionsDao.get(optionsTmp.getId())).thenReturn(optionsTmp);

        optionsService.update(optionsDto);

        Mockito.verify(optionsDao).save(optionsTmp);
    }

    @Test
    public void getOptionTest1() {
        Mockito.when(optionsDao.get(options1.getId())).thenReturn(options1);

        Options result = optionsService.get(options1.getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(options1.getId(), result.getId());
        Assert.assertEquals(options1.getOptionsName(), result.getOptionsName());
        Assert.assertEquals(String.valueOf(options1.getPrice()), String.valueOf(result.getPrice()));
        Assert.assertEquals(String.valueOf(options1.getCostToAdd()), String.valueOf(result.getCostToAdd()));
        Assert.assertEquals(options1.isAvailableOptionToConnectOrNot(), result.isAvailableOptionToConnectOrNot());
        Assert.assertEquals(options1.getOptionType(), result.getOptionType());
        Assert.assertEquals(options1.getConnectedToContracts(), result.getConnectedToContracts());
        Assert.assertEquals(options1.getAvailableForTariffs(), result.getAvailableForTariffs());
    }

    @Test
    public void deleteOptionsTest1() {
        Mockito.when(optionsDao.get(options1.getId())).thenReturn(options1);
        options1.setConnectedToContracts(new ArrayList<>());
        optionsService.delete(options1.getId());

        Mockito.verify(optionsDao).delete(options1.getId());
    }

    @Test(expected = ServiceLayerException.class)
    public void deleteOptionsTest2() {
        Mockito.when(optionsDao.get(options1.getId())).thenReturn(options1);
        List<Contract> connectedContracts = new ArrayList<>();
        connectedContracts.add(new Contract());
        options1.setConnectedToContracts(connectedContracts);
        optionsService.delete(options1.getId());

        Mockito.verify(optionsDao).delete(options1.getId());
    }
}
