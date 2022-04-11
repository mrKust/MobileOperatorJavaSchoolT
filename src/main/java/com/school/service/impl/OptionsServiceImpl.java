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

    /**
     * Described at {@link OptionsService}
     * @return list of all options in system
     */
    @Override
    public List<Options> getAll() {
        return optionsDao.getAll();
    }

    /**
     * Described at {@link OptionsService}
     * @return list of available to connect options
     */
    @Override
    public List<Options> getAllAvailable() {
        return optionsDao.getAllAvailable();
    }

    /**
     * Described at {@link OptionsService}
     * @param list list with ids of options which we want to get
     * @return list of options
     */
    @Override
    public List<Options> getOptionsFromChosenList(List<Integer> list) {
        return optionsDao.getOptionsFromChosenList(list);
    }

    /**
     * Described at {@link OptionsService}
     * @param list list with ids of options which we want to get
     * @return list of options
     */
    @Override
    public List<Options> getAvailableOptionsNamesAndPricesForTariff(List<Integer> list) {
        return optionsDao.getAvailableOptionsNamesAndPricesForTariff(list);
    }

    /**
     * Described at {@link OptionsService}
     * @param optionsDto option data transfer object
     * @param numberOfPage number of page where options will be shown
     * @return list of options for show on page
     */
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

    /**
     * Described at {@link OptionsService}
     * @param sizeOfPage number of record on one page
     * @return number of pages
     */
    @Override
    public int getNumberOfPages(int sizeOfPage) {
        return optionsDao.getNumberOfPages(sizeOfPage);
    }

    /**
     * Described at {@link OptionsService}
     * @param optionsDto option data transfer object
     */
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

    /**
     * Described at {@link OptionsService}
     * @param optionsDto option data transfer object
     */
    @Override
    public void update(OptionsDto optionsDto) {
        Options option = get(optionsDto.getOptions().getId());
        option.setOptionsName(optionsDto.getOptions().getOptionsName());
        option.setPrice(optionsDto.getOptions().getPrice());
        option.setCostToAdd(optionsDto.getOptions().getCostToAdd());
        option.setAvailableOptionToConnectOrNot(optionsDto.getOptions().isAvailableOptionToConnectOrNot());

        optionsDao.save(option);
    }

    /**
     * Described at {@link OptionsService}
     * @param id id of option which need to be returned
     * @return option with this id
     */
    @Override
    public Options get(int id) {
        return optionsDao.get(id);
    }

    /**
     * Described at {@link OptionsService}
     * @param id option's id which need to be deleted
     */
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
