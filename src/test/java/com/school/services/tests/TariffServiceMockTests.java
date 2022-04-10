package com.school.services.tests;

import com.school.customException.ServiceLayerException;
import com.school.database.dao.impl.TariffDaoImpl;
import com.school.database.entity.Contract;
import com.school.database.entity.OptionType;
import com.school.database.entity.Options;
import com.school.database.entity.Tariff;
import com.school.dto.OptionsDto;
import com.school.dto.TariffDto;
import com.school.service.impl.OptionsServiceImpl;
import com.school.service.impl.TariffServiceImpl;
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
public class TariffServiceMockTests {

    private Tariff tariff1 = new Tariff();
    private Tariff tariff2 = new Tariff();

    private List<Tariff> allTariffs = new ArrayList<>();
    private List<Tariff> allAvailableTariffs = new ArrayList<>();

    @Mock
    TariffDaoImpl tariffDao;

    @Mock
    OptionsServiceImpl optionsService;

    @InjectMocks
    TariffServiceImpl tariffService;

    @Before
    public void init() {
        tariff1.setId(1);
        tariff1.setTariffName("Tariff1");
        tariff1.setPrice(5.10);
        List<Options> connectedOptions = new ArrayList<>();
        Options options = new Options();
        options.setId(1);
        connectedOptions.add(options);
        tariff1.setOptions(connectedOptions);
        tariff1.setContract(new Contract());
        tariff1.setAvailableToConnectOrNotStatus(true);

        tariff2.setId(2);
        tariff2.setTariffName("Tariff2");
        tariff2.setPrice(3.25);
        List<Options> connectedOptions2 = new ArrayList<>();
        tariff2.setOptions(connectedOptions2);
        tariff2.setAvailableToConnectOrNotStatus(false);

        allTariffs.add(tariff1);
        allTariffs.add(tariff2);

        allAvailableTariffs.add(tariff1);
    }

    @Test
    public void getAllTariffsTest1() {
        Mockito.when(tariffDao.getAll()).thenReturn(allTariffs);
        List<Tariff> result = tariffService.getAll();

        Mockito.verify(tariffDao).getAll();
        Assert.assertNotNull(result);
        Assert.assertEquals(allTariffs.size(), result.size());
        for (int i = 0; i < result.size(); i++) {
            Tariff expectedTariff = allTariffs.get(i);
            Tariff resultTariff = result.get(i);
            Assert.assertEquals(expectedTariff.getId(), resultTariff.getId());
            Assert.assertEquals(expectedTariff.getTariffName(), resultTariff.getTariffName());
            Assert.assertEquals(String.valueOf(expectedTariff.getPrice()), String.valueOf(resultTariff.getPrice()));
            Assert.assertEquals(expectedTariff.getOptions(), resultTariff.getOptions());
            Assert.assertEquals(expectedTariff.getContract(), resultTariff.getContract());
            Assert.assertEquals(expectedTariff.isAvailableToConnectOrNotStatus(), resultTariff.isAvailableToConnectOrNotStatus());
        }
    }

    @Test
    public void getAllAvailableTest1() {
        Mockito.when(tariffDao.getAllAvailable()).thenReturn(allAvailableTariffs);
        List<Tariff> result = tariffService.getAllAvailable();

        Mockito.verify(tariffDao).getAllAvailable();
        Assert.assertNotNull(result);
        Assert.assertEquals(allAvailableTariffs.size(), result.size());
        for (int i = 0; i < result.size(); i++) {
            Tariff expectedTariff = allTariffs.get(i);
            Tariff resultTariff = result.get(i);
            Assert.assertEquals(expectedTariff.getId(), resultTariff.getId());
            Assert.assertEquals(expectedTariff.getTariffName(), resultTariff.getTariffName());
            Assert.assertEquals(String.valueOf(expectedTariff.getPrice()), String.valueOf(resultTariff.getPrice()));
            Assert.assertEquals(expectedTariff.getOptions(), resultTariff.getOptions());
            Assert.assertEquals(expectedTariff.getContract(), resultTariff.getContract());
            Assert.assertEquals(expectedTariff.isAvailableToConnectOrNotStatus(), resultTariff.isAvailableToConnectOrNotStatus());
        }
    }

    @Test
    public void getPageOfTariffsTest1() {
        Mockito.when(tariffDao.getPageOfTariffs(5, "tariffName", 1)).thenReturn(allTariffs);
        TariffDto tariffDto = new TariffDto();
        List<Tariff> result = tariffService.getPageOfTariffs(tariffDto, 1);
        Assert.assertNotNull(result);
        Assert.assertEquals(allTariffs.size(), result.size());
    }

    @Test
    public void getNumberOfPagesTest1() {
        Mockito.when(tariffDao.getNumberOfPages(5)).thenReturn(1);
        int result = tariffService.getNumberOfPages(5);

        Assert.assertNotEquals(0, result);
        Assert.assertEquals(1, result);
    }

    @Test
    public void saveTariffTest1() {
        TariffDto tariffDto = new TariffDto();
        Tariff tariffTmp = new Tariff();
        tariffTmp.setTariffName("TariffTmp");
        String[] stringsOptions = new String[tariff1.getOptions().size()];
        for (int i = 0; i < tariff1.getOptions().size(); i++) {
            stringsOptions[i] = String.valueOf(tariff1.getOptions().get(i).getId());
        }
        tariffDto.setStringsOptions(stringsOptions);
        tariffDto.setTariff(tariffTmp);
        List<Integer> tmpList = new ArrayList<>();
        for (Options tmp : tariff1.getOptions()) {
            tmpList.add(tmp.getId());
        }
        Mockito.when(optionsService.getOptionsFromChosenList(tmpList)).thenReturn(tariff1.getOptions());

        tariffService.save(tariffDto);

        Mockito.verify(tariffDao).save(tariffTmp);
    }

    @Test
    public void updateTariffTest1() {
        TariffDto tariffDto = new TariffDto();
        Tariff tariffTmp = new Tariff();
        tariffTmp.setTariffName("TariffTmp");
        String[] stringsOptions = new String[tariff1.getOptions().size()];
        for (int i = 0; i < tariff1.getOptions().size(); i++) {
            stringsOptions[i] = String.valueOf(tariff1.getOptions().get(i).getId());
        }
        tariffDto.setStringsOptions(stringsOptions);
        tariffDto.setTariff(tariffTmp);
        List<Integer> tmpList = new ArrayList<>();
        for (Options tmp : tariff1.getOptions()) {
            tmpList.add(tmp.getId());
        }
        Mockito.when(tariffDao.get(tariffDto.getTariff().getId())).thenReturn(tariff1);
        Mockito.when(optionsService.getOptionsFromChosenList(tmpList)).thenReturn(tariff1.getOptions());

        tariffService.save(tariffDto);

        Mockito.verify(tariffDao).save(tariffTmp);
    }

    @Test
    public void getTariffTest1() {
        Mockito.when(tariffDao.get(tariff1.getId())).thenReturn(tariff1);

        Tariff result = tariffService.get(tariff1.getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(tariff1.getId(), result.getId());
        Assert.assertEquals(tariff1.getTariffName(), result.getTariffName());
        Assert.assertEquals(String.valueOf(tariff1.getPrice()), String.valueOf(result.getPrice()));
        Assert.assertEquals(tariff1.getOptions(), result.getOptions());
        Assert.assertEquals(tariff1.getContract(), result.getContract());
        Assert.assertEquals(tariff1.isAvailableToConnectOrNotStatus(), result.isAvailableToConnectOrNotStatus());
    }

    @Test(expected = ServiceLayerException.class)
    public void deleteTariffTest1() {
        Mockito.when(tariffDao.get(tariff1.getId())).thenReturn(tariff1);
        tariffService.delete(tariff1.getId());

        Mockito.verify(tariffDao).delete(tariff1.getId());
    }

    @Test
    public void deleteTariffTest2() {
        Mockito.when(tariffDao.get(tariff2.getId())).thenReturn(tariff2);
        tariffService.delete(tariff2.getId());

        Mockito.verify(tariffDao).delete(tariff2.getId());
    }
}
