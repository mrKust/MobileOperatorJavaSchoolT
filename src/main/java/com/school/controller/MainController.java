package com.school.controller;

import com.school.database.entity.Client;
import com.school.service.contracts.ClientService;
import com.school.service.contracts.ServiceMVC;
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
    public String showView(Principal principal, Model model) {

        model.addAttribute("user", clientServiceMVC.getByEmail(principal.getName()));
        return "/common/start-view";
    }

    @RequestMapping("/common/errorPage")
    public String showErrorMessage(@RequestParam("errorMes") String errorMessage, Model model) {

        model.addAttribute("errorMessage", errorMessage);

        return "common/error-page";
    }

}
