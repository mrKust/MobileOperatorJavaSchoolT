package com.school.services.tests;

import com.school.database.dao.contracts.OptionTypeDao;
import com.school.database.entity.OptionType;
import com.school.database.entity.Options;
import com.school.service.impl.OptionTypeServiceImpl;
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
public class OptionTypeServiceMockTests {

    private OptionType optionType1 = new OptionType();
    private OptionType optionType2 = new OptionType();
    private List<OptionType> allOptionsType = new ArrayList<>();

    @Mock
    OptionTypeDao optionTypeDao;

    @InjectMocks
    OptionTypeServiceImpl optionTypeService;

    @Before
    public void init() {
        optionType1.setId(1);
        optionType1.setOptionType("Internet");

        optionType2.setId(2);
        optionType2.setOptionType("Minuti");

        allOptionsType.add(optionType1);
        allOptionsType.add(optionType2);
    }

    @Test
    public void getOptionTypeTest1() {
        Mockito.when(optionTypeDao.get(optionType1.getId())).thenReturn(optionType1);
        OptionType result = optionTypeService.get(optionType1.getId());

        Mockito.verify(optionTypeDao).get(optionType1.getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(optionType1.getId(), result.getId());
        Assert.assertEquals(optionType1.getOptionType(), result.getOptionType());
        Assert.assertEquals(optionType1.getOptions(), result.getOptions());
    }

    @Test
    public void getAllOptionTypesTest1() {
        Mockito.when(optionTypeDao.getAll()).thenReturn(allOptionsType);
        List<OptionType> result = optionTypeService.getAll();

        Mockito.verify(optionTypeDao).getAll();
        Assert.assertNotNull(result);
        Assert.assertEquals(allOptionsType.size(), result.size());
        for (int i = 0; i < result.size(); i++) {
            OptionType expectedOptionType = allOptionsType.get(i);
            OptionType resultOptionType = result.get(i);
            Assert.assertEquals(expectedOptionType.getId(), resultOptionType.getId());
            Assert.assertEquals(expectedOptionType.getOptionType(), resultOptionType.getOptionType());
            Assert.assertEquals(expectedOptionType.getOptions(), resultOptionType.getOptions());
        }
    }

    @Test
    public void getAllOptionTypesTest2() {
        Mockito.when(optionTypeDao.getAll()).thenReturn(null);
        List<OptionType> result = optionTypeService.getAll();

        Mockito.verify(optionTypeDao).getAll();
        Assert.assertNull(result);
    }

    @Test
    public void saveOptionTypeTest1() {
        OptionType optionTypeTmp = new OptionType();
        optionTypeTmp.setOptionType("MMS");
        Mockito.when(optionTypeDao.checkOptionTypeToUnique(optionTypeTmp)).thenReturn(true);

        optionTypeService.save(optionTypeTmp);

        Mockito.verify(optionTypeDao).save(optionTypeTmp);
    }

    @Test
    public void checkOptionTypeToUniqueTest1() {
        Mockito.when(optionTypeDao.checkOptionTypeToUnique(optionType1)).thenReturn(true);
        boolean result = optionTypeService.checkOptionTypeToUnique(optionType1);

        Mockito.verify(optionTypeDao).checkOptionTypeToUnique(optionType1);
        Assert.assertTrue(result);
    }

    //Nullpointer exceptions because don't mock options list with current option type
    @Test(expected = NullPointerException.class)
    public void deleteOptionTypeTest1() {
        optionTypeService.delete(optionType1.getId());

        Mockito.verify(optionTypeDao).delete(optionType1.getId());
    }

    @Test
    public void deleteOptionTypeTest2() {
        Mockito.when(optionTypeDao.get(optionType1.getId())).thenReturn(optionType1);
        optionType1.setOptions(new ArrayList<Options>());
        optionTypeService.delete(optionType1.getId());

        Mockito.verify(optionTypeDao).delete(optionType1.getId());
    }
}
