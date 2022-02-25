package com.school.controller;

import com.school.database.entity.Client;
import com.school.database.entity.Contract;
import com.school.database.entity.Options;
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
public class MainController {

    @Autowired
    private ServiceMVC<Options> optionsServiceMVC;

    @Autowired
    private ServiceMVC<Tariff> tariffServiceMVC;

    @Autowired
    private ServiceMVC<Contract> contractServiceMVC;

    @Autowired
    private ServiceMVC<Client> clientServiceMVC;



    @RequestMapping("/")
    public String showView() {
        return "redirect:/allOptions";
    }

    @RequestMapping("/allOptions")
    public String showAllOptions(Model model) {

        List<Options> allOptions = optionsServiceMVC.getAll();
        model.addAttribute("allOptions", allOptions);

        return "all-options";
    }

    @RequestMapping("/addNewOption")
    public String addNewOption(Model model) {

        Options options = new Options();
        model.addAttribute("options", options);
        return "option-info-form";
    }

    @RequestMapping("/saveOption")
    public String saveOption(@ModelAttribute("options") Options option) {

        optionsServiceMVC.save(option);

        return "redirect:/allOptions";
    }

    @RequestMapping("/updateOption")
    public String updateOption(@RequestParam("optionId") int id, Model model) {

        Options options = optionsServiceMVC.get(id);
        model.addAttribute("options", options);

        return "option-info-form";
    }

    @RequestMapping("/deleteOption")
    public String deleteOption(@RequestParam("optionId") int id ) {

        optionsServiceMVC.delete(id);

        return "redirect:/allOptions";
    }

    @RequestMapping("/allTariffs")
    public String showAllTariff(Model model) {

        List<Tariff> allTariffs = tariffServiceMVC.getAll();
        model.addAttribute("allTariffs", allTariffs);

        return "all-tariffs";
    }

    @RequestMapping("/tariffInfo")
    public String showTariffInfo(@RequestParam("tariffId") int id, Model model) {
        Tariff tariff = tariffServiceMVC.get(id);
        model.addAttribute("tariffs", id);

        return "tariff-info-form";
    }
}
