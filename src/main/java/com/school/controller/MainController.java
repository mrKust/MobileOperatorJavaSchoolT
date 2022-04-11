package com.school.controller;

import com.school.service.contracts.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * This controller show common views which used for all registered users in system and for unregistered
 * users
 */
@Controller
public class MainController {

    private final ClientService clientServiceMVC;

    MainController(ClientService clientServiceMVC) {
        this.clientServiceMVC = clientServiceMVC;

    }

    /**
     * This method shows home page view
     * @param model model
     * @return view with home page content
     */
    @RequestMapping("/")
    public String showView(Model model) {

        return "/common/start-view";
    }

    /**
     * This method shows common error page
     * @param errorMessage text of error message
     * @param model model
     * @return view with error description
     */
    @RequestMapping("/errorPage")
    public String showErrorMessage(@RequestParam("errorMes") String errorMessage, Model model) {

        model.addAttribute("errorMessage", errorMessage);

        return "common/error-page";
    }

}
