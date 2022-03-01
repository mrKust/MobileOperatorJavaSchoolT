package com.school.controller;

import com.school.database.entity.Tariff;
import com.school.service.ServiceMVC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TariffController {

    @Autowired
    private ServiceMVC<Tariff> tariffServiceMVC;

    @GetMapping("/allTariffs")
    public String showAllTariff(Model model) {

        List<Tariff> tariffs = tariffServiceMVC.getAll();
        model.addAttribute("allTariffs", tariffs);

        return "all-tariffs";
    }

    @GetMapping("/addNewTariff")
    public String addNewTariff(Model model) {

        Tariff tariff = new Tariff();
        model.addAttribute("tariffs", tariff);
        return "tariff-info-form";
    }

    @GetMapping("/saveTariff")
    public String saveTariff(@ModelAttribute("tariffs") Tariff tariff) {

        tariffServiceMVC.save(tariff);

        return "redirect:/allTariffs";
    }

    @GetMapping("/updateTariff")
    public String updateTariff(@RequestParam("tariffId") int id, Model model) {

        Tariff tariff = tariffServiceMVC.get(id);
        model.addAttribute("tariffs", tariff);

        return "tariff-info-form";
    }

    @GetMapping("/deleteTariff")
    public String deleteTariff(@RequestParam("tariffId") int id ) {

        tariffServiceMVC.delete(id);

        return "redirect:/allTariffs";
    }
}
