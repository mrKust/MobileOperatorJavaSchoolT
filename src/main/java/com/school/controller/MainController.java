package com.school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MainController {

    @RequestMapping("/")
    public String showView() {
        return "/common/start-view";
    }

    @RequestMapping("/common/errorPage")
    public String showErrorMessage(@RequestParam("errorMes") String errorMessage, Model model) {

        model.addAttribute("errorMessage", errorMessage);

        return "common/error-page";
    }

}
