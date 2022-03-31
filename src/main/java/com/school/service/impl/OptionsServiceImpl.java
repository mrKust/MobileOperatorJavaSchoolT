package com.school.service.impl;

import com.school.customException.ServiceLayerException;
import com.school.database.dao.contracts.OptionsDao;
import com.school.database.entity.OptionType;
import com.school.database.entity.Options;
import com.school.dto.OptionsDto;
import com.school.service.contracts.OptionTypeService;
import com.school.service.contracts.OptionsService;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

@Service
public class OptionsServiceImpl implements OptionsService {

    private final OptionsDao optionsDao;
    private final OptionTypeService optionTypeService;
    private final DecimalFormatSymbols priceFormat;
    private final DecimalFormat decimalFormat;
    String PRICE_PATTERN = "##0.00";

    OptionsServiceImpl(OptionsDao optionsDao, OptionTypeService optionTypeService) {
        this.optionsDao = optionsDao;
        this.optionTypeService = optionTypeService;
        this.priceFormat = new DecimalFormatSymbols(Locale.US);
        this.decimalFormat = new DecimalFormat(PRICE_PATTERN, priceFormat);
    }

    @Override
    public List<Options> getAll() {
        return optionsDao.getAll();
    }

    @Override
    public List<Options> getAllAvailable() {
        return optionsDao.getAllAvailable();
    }

    @Override
    public List<Options> getOptionsFromChosenList(List<Integer> list) {
        return optionsDao.getOptionsFromChosenList(list);
    }

    @Override
    public List<Options> getPageOfOptions(OptionsDto optionsDto, Integer numberOfPage) {
        if (optionsDto.getPageSize() == 0)
            optionsDto.setPageSize(5);
        if (optionsDto.getSortColumn() == null)
            optionsDto.setSortColumn("optionsName");
        if (numberOfPage == null)
            numberOfPage = 1;
        return optionsDao.getPageOfOptions(optionsDto.getPageSize(), optionsDto.getSortColumn(),
                numberOfPage);
    }

    @Override
    public int getNumberOfPages(int sizeOfPage) {
        return optionsDao.getNumberOfPages(sizeOfPage);
    }

    @Override
    public void save(OptionsDto optionsDto) {
        Options option = optionsDto.getOptions();
        option.setPrice(Double.parseDouble(decimalFormat.format(option.getPrice())));
        option.setCostToAdd(Double.parseDouble(decimalFormat.format(option.getCostToAdd())));
        String[] chosenOptionType = optionsDto.getStringOptionCategory();
        OptionType optionType = optionTypeService.get(Integer.parseInt(chosenOptionType[0]));
        option.setOptionType(optionType);
        optionsDao.save(option);
    }

    @Override
    public void update(OptionsDto optionsDto) {
        Options option = get(optionsDto.getOptions().getId());
        option.setOptionsName(optionsDto.getOptions().getOptionsName());
        option.setPrice(optionsDto.getOptions().getPrice());
        option.setCostToAdd(optionsDto.getOptions().getCostToAdd());
        option.setAvailableOptionToConnectOrNot(optionsDto.getOptions().isAvailableOptionToConnectOrNot());

        optionsDao.save(option);
    }

    @Override
    public Options get(int id) {
        return optionsDao.get(id);
    }

    @Override
    public void delete(int id) {
        Options options = get(id);
        if (options.getConnectedToContracts().size() > 0) {
            throw new ServiceLayerException("Couldn't delete option " + options.getOptionsName() +
                    "\nOption couldn't be deleted if it connected to although one contract");
        }
        optionsDao.delete(id);
    }
}
