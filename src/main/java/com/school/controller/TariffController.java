package com.school.controller;

import com.school.database.entity.Tariff;
import com.school.service.ServiceMVC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TariffController {

    @Autowired
    private ServiceMVC<Tariff> tariffServiceMVC;

    @RequestMapping("/common/allTariffs")
    public String showAllTariff(Model model) {

        List<Tariff> tariffs = tariffServiceMVC.getAll();
        model.addAttribute("allTariffs", tariffs);

        return "common/all-tariffs";
    }

    @RequestMapping("/control/addNewTariff")
    public String addNewTariff(Model model) {

        Tariff tariff = new Tariff();
        model.addAttribute("tariffs", tariff);
        return "control/add-tariff-info-control-form";
    }

    @RequestMapping("/common/saveTariff")
    public String saveTariff(@ModelAttribute("tariffs") Tariff tariff) {

        tariffServiceMVC.save(tariff);

        return "redirect:/common/allTariffs";
    }

    @RequestMapping("/control/updateTariff")
    public String controlUpdateTariff(@RequestParam("tariffId") int id, Model model) {

        Tariff tariff = tariffServiceMVC.get(id);
        model.addAttribute("tariffs", tariff);

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
        model.addAttribute("tariffs", tariff);

        return "client/tariff-info-client-form";
    }
}
