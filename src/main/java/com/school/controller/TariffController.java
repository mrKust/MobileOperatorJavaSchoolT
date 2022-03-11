package com.school.controller;

import com.school.database.entity.Options;
import com.school.database.entity.Tariff;
import com.school.service.ServiceMVC;
import com.school.dto.TariffDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TariffController {

    private final ServiceMVC<Tariff> tariffServiceMVC;

    private final ServiceMVC<Options> optionsServiceMVC;

    TariffController(ServiceMVC<Tariff> tariffServiceMVC, ServiceMVC<Options> optionsServiceMVC) {
        this.tariffServiceMVC = tariffServiceMVC;
        this.optionsServiceMVC = optionsServiceMVC;
    }

    @RequestMapping("/common/allTariffs")
    public String showAllTariff(Model model) {

        List<Tariff> tariffs = tariffServiceMVC.getAll();
        model.addAttribute("allTariffs", tariffs);

        return "common/all-tariffs";
    }

    @RequestMapping("/control/addNewTariff")
    public String addNewTariff(Model model) {

        List<Options> optionsList = optionsServiceMVC.getAll();
        TariffDto tmp = new TariffDto();
        tmp.setTariff(new Tariff());
        model.addAttribute("optionsList", optionsList);
        model.addAttribute("model", tmp);

        return "control/add-tariff-info-control-form";
    }

    @RequestMapping("/common/saveTariff")
    public String saveTariff(@ModelAttribute("model") TariffDto tariffDto) {

        Tariff tariff = tariffDto.getTariff();
        tariff.setOptions(tariffDto.wrapStringsToList(optionsServiceMVC.getAll()));

        tariffServiceMVC.save(tariff);

        return "redirect:/common/allTariffs";
    }

    @RequestMapping("/control/updateTariff")
    public String controlUpdateTariff(@RequestParam("tariffId") int id, Model model) {

        List<Options> optionsList = optionsServiceMVC.getAll();
        TariffDto tmp = new TariffDto();
        tmp.setTariff(tariffServiceMVC.get(id));
        List<Options> connectedOptionsList = tmp.getTariff().getOptions();
        model.addAttribute("connectedOptionsList", connectedOptionsList);
        model.addAttribute("optionsList", optionsList);
        model.addAttribute("model", tmp);

        return "control/update-tariff-info-control-form";
    }

    @RequestMapping("/control/deleteTariff")
    public String deleteTariff(@RequestParam("tariffId") int id ) {

        tariffServiceMVC.delete(id);

        return "redirect:/common/allTariffs";
    }

    @RequestMapping("/client/updateTariff")
    public String clientUpdateTariff(@RequestParam("tariffId") int id, Model model) {

        Tariff tariff = tariffServiceMVC.get(id);
        List<Options> availableOptions = tariff.getOptions();
        model.addAttribute("optionsList", availableOptions);
        model.addAttribute("tariffs", tariff);

        return "client/tariff-info-client-form";
    }
}
