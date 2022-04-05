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

    @Override
    public List<Tariff> getAll() {
        return tariffDao.getAll();
    }

    @Override
    public List<Tariff> getAllAvailable() {
        return tariffDao.getAllAvailable();
    }

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

    @Override
    public int getNumberOfPages(int sizeOfPage) {
        return tariffDao.getNumberOfPages(sizeOfPage);
    }


    @Override
    public void save(TariffDto tariffDto) {
        Tariff tariff = tariffDto.getTariff();
        tariff.setPrice(Double.parseDouble(decimalFormat.format(tariff.getPrice())));
        tariff.setOptions(optionsService.getOptionsFromChosenList(tariffDto.getChosenOptionsList()));
        tariffDao.save(tariff);
    }

    @Override
    public void update(TariffDto tariffDto) {
        Tariff tariff = tariffDao.get(tariffDto.getTariff().getId());
        tariff.setTariffName(tariffDto.getTariff().getTariffName());
        tariff.setPrice(tariffDto.getTariff().getPrice());
        tariff.setOptions(optionsService.getOptionsFromChosenList(tariffDto.getChosenOptionsList()));

        tariffDao.save(tariff);
    }

    @Override
    public void notificationAboutTariffUpdate() {
        amqpTemplate.convertAndSend("queue1","Update info");
    }

    @Override
    public Tariff get(int id) {
        return tariffDao.get(id);
    }

    @Override
    public Tariff getByName(String name) {
        return tariffDao.getByName(name);
    }

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
