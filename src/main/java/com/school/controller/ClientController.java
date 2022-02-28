package com.school.controller;

import com.school.database.entity.Client;
import com.school.database.entity.Options;
import com.school.service.ServiceMVC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ClientController {

    @Autowired
    private ServiceMVC<Client> clientServiceMVC;

    @RequestMapping("/allClients")
    public String showAllClients(Model model) {

        List<Client> allClients = clientServiceMVC.getAll();
        model.addAttribute("allClients", allClients);

        return "all-clients";
    }

    @RequestMapping("/addNewClient")
    public String addNewClient(Model model) {

        Client client = new Client();
        model.addAttribute("clients", client);
        return "client-info-form";
    }

    @RequestMapping("/saveClient")
    public String saveClient(@ModelAttribute("clients") Client client) {

        clientServiceMVC.save(client);

        return "redirect:/allClients";
    }

    @RequestMapping("/updateClient")
    public String updateClient(@RequestParam("clientId") int id, Model model) {

        Client client = clientServiceMVC.get(id);
        model.addAttribute("clients", client);

        return "client-info-form";
    }

    @RequestMapping("/deleteClient")
    public String deleteClient(@RequestParam("clientId") int id ) {

        clientServiceMVC.delete(id);

        return "redirect:/allClients";
    }
}
