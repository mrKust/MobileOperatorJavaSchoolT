package com.school.controller;

import com.school.database.entity.Client;
import com.school.service.ServiceMVC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
public class ClientController {

    @Autowired
    private ServiceMVC<Client> clientServiceMVC;

    @RequestMapping("/control/allClients")
    public String showAllClients(Model model) {

        List<Client> allClients = clientServiceMVC.getAll();
        model.addAttribute("allClients", allClients);

        return "control/all-clients";
    }

    @RequestMapping("/control/addNewClient")
    public String addNewClient(Model model) {

        Client client = new Client();
        model.addAttribute("clients", client);
        return "control/add-client-info-control-form";
    }

    @RequestMapping("/common/saveClient")
    public String saveClient(@ModelAttribute("clients") Client client, HttpServletRequest request) {

        clientServiceMVC.save(client);
        if (request.isUserInRole("ROLE_control"))
            return "redirect:/control/allClients";

        return "redirect:/";
    }

    @RequestMapping("/control/updateClient")
    public String controlUpdateClient(@RequestParam("clientId") int id, Model model) {

        Client client = clientServiceMVC.get(id);
        model.addAttribute("clients", client);

        return "control/update-client-info-control-form";
    }

    @RequestMapping("/control/deleteClient")
    public String deleteClient(@RequestParam("clientId") int id ) {

        clientServiceMVC.delete(id);

        return "redirect:/control/allClients";
    }

    @RequestMapping("/client/updateClient")
    public String clientUpdateClient(Principal principal, Model model) {

        Client client = clientServiceMVC.getByName(principal.getName());
        model.addAttribute("clients", client);

        return "client/update-client-info-client-form";

    }
}
