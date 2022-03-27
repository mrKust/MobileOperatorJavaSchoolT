package com.school.controller;

import com.school.database.entity.Tariff;
import com.school.service.contracts.OptionsService;
import com.school.dto.TariffDto;
import com.school.service.contracts.TariffService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TariffController {

    private final TariffService tariffServiceMVC;

    private final OptionsService optionsServiceMVC;

    TariffController(TariffService tariffServiceMVC, OptionsService optionsServiceMVC) {
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

        TariffDto tmp = new TariffDto();
        tmp.setTariff(new Tariff());
        tmp.setOperationType("add");
        model.addAttribute("optionsList", optionsServiceMVC.getAll());
        model.addAttribute("model", tmp);

        return "control/add-tariff-info-control-form";
    }

    @RequestMapping("/common/saveTariff")
    public String saveTariff(@ModelAttribute("model") TariffDto tariffDto) {

        tariffServiceMVC.save(tariffDto);

        return "redirect:/common/allTariffs";
    }

    @RequestMapping("/control/updateTariff")
    public String controlUpdateTariff(@RequestParam("tariffId") int id, Model model) {

        TariffDto tmp = new TariffDto();
        tmp.setTariff(tariffServiceMVC.get(id));
        tmp.setOperationType("update");
        model.addAttribute("connectedOptionsList", tmp.getTariff().getOptions());
        model.addAttribute("optionsList", optionsServiceMVC.getAll());
        model.addAttribute("model", tmp);

        return "control/update-tariff-info-control-form";
    }

    @RequestMapping("/control/deleteTariff")
    public String deleteTariff(@RequestParam("tariffId") int id) {

        tariffServiceMVC.delete(id);

        return "redirect:/common/allTariffs";
    }

    @RequestMapping("/client/updateTariff")
    public String clientUpdateTariff(@RequestParam("tariffId") int id, Model model) {

        TariffDto tariffDto = new TariffDto();
        tariffDto.setTariff(tariffServiceMVC.get(id));
        tariffDto.setOperationType("update");
        model.addAttribute("optionsList", tariffDto.getTariff().getOptions());
        model.addAttribute("model", tariffDto);

        return "client/tariff-info-client-form";
    }
}
