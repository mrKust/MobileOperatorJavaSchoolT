package com.school.services.tests;

import com.school.customException.ServiceLayerException;
import com.school.database.dao.impl.NumberDaoImpl;
import com.school.database.entity.Number;
import com.school.service.impl.NumberServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class NumberServiceMockTests {

    private Number number1 = new Number();
    private Number number2 = new Number();
    private List<Number> allNumbers = new ArrayList<>();
    private String[] allUnusedNumbers = new String[1];
    private String[] allUsedNumbers = new String[1];

    @Mock
    NumberDaoImpl numberDao;

    @InjectMocks
    NumberServiceImpl numberService;

    @Before
    public void init() {

        number1.setId(1);
        number1.setPhoneNumber("89217354597");
        number1.setAvailableToConnectStatus(true);

        number2.setId(2);
        number2.setPhoneNumber("89115394615");
        number2.setAvailableToConnectStatus(false);

        allNumbers.add(number1);
        allNumbers.add(number2);

        allUsedNumbers[0] = number2.getPhoneNumber();

        allUnusedNumbers[0] = number1.getPhoneNumber();
    }

    @Test
    public void getAllNumbersTest1() {
        Mockito.when(numberDao.getAll()).thenReturn(allNumbers);
        List<Number> result = numberService.getAll();

        Mockito.verify(numberDao).getAll();
        Assert.assertNotNull(result);
        Assert.assertEquals(allNumbers.size(), result.size());
        for (int i = 0; i < result.size(); i++) {
            Number expectedNumber = allNumbers.get(i);
            Number resultNumber = result.get(i);
            Assert.assertEquals(expectedNumber.getId(), resultNumber.getId());
            Assert.assertEquals(expectedNumber.getPhoneNumber(), resultNumber.getPhoneNumber());
            Assert.assertEquals(expectedNumber.isAvailableToConnectStatus(), resultNumber.isAvailableToConnectStatus());
        }
    }

    @Test
    public void getAllNumbersTest2() {
        Mockito.when(numberDao.getAll()).thenReturn(null);
        List<Number> result = numberService.getAll();

        Mockito.verify(numberDao).getAll();
        Assert.assertNull(result);
    }

    @Test
    public void getAllUnusedNumbersTest1() {
        Mockito.when(numberDao.getAllUnused()).thenReturn(Arrays.asList(allUnusedNumbers));
        String[] result = numberService.getAllUnused();

        Mockito.verify(numberDao).getAllUnused();
        Assert.assertNotNull(result);
        Assert.assertEquals(allUnusedNumbers.length, result.length);
        for (int i = 0; i < result.length; i++) {
            String expectedNumber = allUnusedNumbers[i];
            String resultNumber = result[i];
            Assert.assertEquals(expectedNumber, resultNumber);
        }
    }

    @Test
    public void getAllUsedNumbers1() {
        Mockito.when(numberDao.getAllUsed()).thenReturn(Arrays.asList(allUsedNumbers));
        String[] result = numberService.getAllUsed();

        Mockito.verify(numberDao).getAllUsed();
        Assert.assertNotNull(result);
        Assert.assertEquals(allUsedNumbers.length, result.length);
        for (int i = 0; i < result.length; i++) {
            String expectedNumber = allUsedNumbers[i];
            String resultNumber = result[i];
            Assert.assertEquals(expectedNumber, resultNumber);
        }
    }

    @Test
    public void checkNumberToUniqueTest1() {
        Mockito.when(numberDao.checkNumberToUnique(number1)).thenReturn(0);
        boolean result = numberService.checkNumberToUnique(number1);

        Mockito.verify(numberDao).checkNumberToUnique(number1);
        Assert.assertTrue(result);
    }

    @Test
    public void saveNumberTest1() {
        Number numberTmp = new Number();
        numberTmp.setPhoneNumber("89584148201");
        numberTmp.setAvailableToConnectStatus(true);
        Mockito.when(numberDao.checkNumberToUnique(numberTmp)).thenReturn(0);

        numberService.save(numberTmp);

        Mockito.verify(numberDao).save(numberTmp);
    }

    @Test(expected = ServiceLayerException.class)
    public void saveNumberTest2() {
        Number numberTmp = new Number();
        numberTmp.setPhoneNumber("89584148201");
        numberTmp.setAvailableToConnectStatus(true);
        Mockito.when(numberDao.checkNumberToUnique(numberTmp)).thenReturn(1);

        numberService.save(numberTmp);

        Mockito.verify(numberDao).save(numberTmp);
    }

    @Test
    public void deleteNumberTest1() {
        numberService.delete(number1.getId());

        Mockito.verify(numberDao).delete(number1.getId());
    }

    @Test
    public void updateNumberTest1() {
        numberService.update(number1);

        Mockito.verify(numberDao).save(number1);
    }

    @Test
    public void getNumberTest1() {
        Mockito.when(numberDao.get(number1.getId())).thenReturn(number1);
        Number result = numberService.get(number1.getId());

        Mockito.verify(numberDao).get(number1.getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(number1.getId(), result.getId());
        Assert.assertEquals(number1.getPhoneNumber(), result.getPhoneNumber());
        Assert.assertEquals(number1.isAvailableToConnectStatus(), result.isAvailableToConnectStatus());
    }

    @Test
    public void getByPhoneNumberTest1() {
        Mockito.when(numberDao.getByPhoneNumber(number1.getPhoneNumber())).thenReturn(number1);
        Number result = numberService.getByPhoneNumber(number1.getPhoneNumber());

        Mockito.verify(numberDao).getByPhoneNumber(number1.getPhoneNumber());
        Assert.assertNotNull(result);
        Assert.assertEquals(number1.getId(), result.getId());
        Assert.assertEquals(number1.getPhoneNumber(), result.getPhoneNumber());
        Assert.assertEquals(number1.isAvailableToConnectStatus(), result.isAvailableToConnectStatus());
    }
}
