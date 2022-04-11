package com.school.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.customException.ServiceLayerException;
import com.school.database.dao.contracts.TariffDao;
import com.school.database.entity.Tariff;
import com.school.dto.TariffDto;
import com.school.service.contracts.OptionsService;
import com.school.service.contracts.TariffService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

@Service
public class TariffServiceImpl  implements TariffService {

    private final TariffDao tariffDao;
    private final OptionsService optionsService;
    private final DecimalFormatSymbols priceFormat;
    private final DecimalFormat decimalFormat;
    String PRICE_PATTERN = "##0.00";
    private final AmqpTemplate amqpTemplate;

    TariffServiceImpl(TariffDao tariffDao, OptionsService optionsService, AmqpTemplate amqpTemplate) {
        this.tariffDao = tariffDao;
        this.optionsService = optionsService;
        this.priceFormat = new DecimalFormatSymbols(Locale.US);
        this.decimalFormat = new DecimalFormat(PRICE_PATTERN, priceFormat);
        this.amqpTemplate = amqpTemplate;
    }

    /**
     * Described at {@link TariffService}
     * @return list of tariffs
     */
    @Override
    public List<Tariff> getAll() {
        return tariffDao.getAll();
    }

    /**
     * Described at {@link TariffService}
     * @return list of available to connect tariffs
     */
    @Override
    public List<Tariff> getAllAvailable() {
        return tariffDao.getAllAvailable();
    }

    /**
     * Described at {@link TariffService}
     * @param tariffDto tariff data transfer object
     * @param numberOfPage number of page where tariffs will be shown
     * @return list of tariffs for show on page
     */
    @Override
    public List<Tariff> getPageOfTariffs(TariffDto tariffDto, Integer numberOfPage) {
        if (tariffDto.getPageSize() == 0)
            tariffDto.setPageSize(5);
        if (tariffDto.getSortColumn() == null)
            tariffDto.setSortColumn("tariffName");
        if (numberOfPage == null)
            numberOfPage = 1;
        return tariffDao.getPageOfTariffs(tariffDto.getPageSize(), tariffDto.getSortColumn(),
                numberOfPage);
    }

    /**
     * Described at {@link TariffService}
     * @param sizeOfPage number of record on one page
     * @return number of pages
     */
    @Override
    public int getNumberOfPages(int sizeOfPage) {
        return tariffDao.getNumberOfPages(sizeOfPage);
    }

    /**
     * Described at {@link TariffService}
     * @param tariffDto tariff data transfer object
     */
    @Override
    public void save(TariffDto tariffDto) {
        Tariff tariff = tariffDto.getTariff();
        tariff.setPrice(Double.parseDouble(decimalFormat.format(tariff.getPrice())));
        tariff.setOptions(optionsService.getOptionsFromChosenList(tariffDto.getChosenOptionsList()));
        tariffDao.save(tariff);
    }

    /**
     * Described at {@link TariffService}
     * @param tariffDto tariff data transfer object
     */
    @Override
    public void update(TariffDto tariffDto) {
        Tariff tariff = tariffDao.get(tariffDto.getTariff().getId());
        tariff.setTariffName(tariffDto.getTariff().getTariffName());
        tariff.setPrice(tariffDto.getTariff().getPrice());
        tariff.setOptions(optionsService.getOptionsFromChosenList(tariffDto.getChosenOptionsList()));

        tariffDao.save(tariff);
    }

    /**
     * Described at {@link TariffService}
     */
    @Override
    public void notificationAboutTariffUpdate() {
        amqpTemplate.convertAndSend("queue1","Update info");
    }

    /**
     * Described at {@link TariffService}
     * @param id id of tariff which we are looking for
     * @return tariff with required id
     */
    @Override
    public Tariff get(int id) {
        return tariffDao.get(id);
    }

    /**
     * Described at {@link TariffService}
     * @return string in json format
     */
    @Override
    public String getAllAvailableTariffsDataInJson() {
        ObjectMapper mapper = new ObjectMapper();

        List<Tariff> list = tariffDao.getAllAvailable();
        String optionsJson = new String();
        try {
            optionsJson = mapper.writeValueAsString(list);

        } catch (JsonProcessingException e) {
            throw new ServiceLayerException("Convert to json problem " + e.getMessage());
        }
        return optionsJson;
    }

    /**
     * Described at {@link TariffService}
     * @param id id of tariff which should be deleted
     */
    @Override
    public void delete(int id) {
        Tariff tariff = get(id);
        if (tariff.getContract() != null) {
            throw new ServiceLayerException("Couldn't delete tariff " + tariff.getTariffName() +
                    "\nTariff couldn't be deleted if it connected to although one contract");
        }
        tariffDao.delete(id);
    }
}
