package com.school.controller;

import com.school.database.dao.OptionsDaoImpl;
import com.school.database.entity.Client;
import com.school.database.entity.Contract;
import com.school.database.entity.Options;
import com.school.database.entity.Tariff;
import com.school.service.ServiceMVC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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



    /*@RequestMapping("/")
    public String showView() {
        return "login-view";
    }*/

    @RequestMapping("/")
    public String showAllOptions(Model model) {

        List<Options> allOptions = optionsServiceMVC.getAll();
        List<Tariff> allTariffs = tariffServiceMVC.getAll();
        model.addAttribute("allOptions", allOptions);

        return "all-options";
    }
}
