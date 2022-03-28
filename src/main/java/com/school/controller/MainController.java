package com.school.controller;

import com.school.service.contracts.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;


@Controller
public class MainController {

    private final ClientService clientServiceMVC;

    MainController(ClientService clientServiceMVC) {
        this.clientServiceMVC = clientServiceMVC;

    }

    @RequestMapping("/")
    public String showView(Model model) {

        return "/common/start-view";
    }

    @RequestMapping("/common/errorPage")
    public String showErrorMessage(@RequestParam("errorMes") String errorMessage, Model model) {

        model.addAttribute("errorMessage", errorMessage);

        return "common/error-page";
    }

}
