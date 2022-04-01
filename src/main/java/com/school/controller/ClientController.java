package com.school.controller;

import com.school.database.entity.Client;
import com.school.dto.ClientDto;
import com.school.service.contracts.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class ClientController {

    private final ClientService clientServiceMVC;

    ClientController(ClientService clientServiceMVC) {
        this.clientServiceMVC = clientServiceMVC;
    }

    @RequestMapping("/control/allClients")
    public String showAllClients(@ModelAttribute("model") ClientDto clientDto,
                                 @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                 Model model) {

        model.addAttribute("model", clientDto);
        model.addAttribute("allClients", clientServiceMVC.getPageOfClients(clientDto, pageNumber));
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("numberOfPages", clientServiceMVC.getNumberOfPages(clientDto.getPageSize()));

        return "control/all-clients";
    }

    @RequestMapping("/control/addNewClient")
    public String addNewClient(Model model) {

        ClientDto tmp = new ClientDto();
        tmp.setClient(new Client());
        model.addAttribute("model", tmp);
        return "control/add-client-info-control-form";
    }

    @RequestMapping("/common/saveClient")
    public RedirectView saveClient(@ModelAttribute("model") ClientDto clientDto,
                                   HttpServletRequest request, RedirectAttributes redir) {

        clientServiceMVC.save(clientDto);
        RedirectView redirectView = new RedirectView(request.getHeader("Referer"), true);
        redir.addFlashAttribute("successMessage", "Client added successfully");

        return redirectView;
    }

    @RequestMapping("/common/patchClient")
    public RedirectView patchClient(@ModelAttribute("model") ClientDto clientDto,
                                   HttpServletRequest request, RedirectAttributes redir) {

        clientServiceMVC.update(clientDto);
        RedirectView redirectView = new RedirectView(request.getHeader("Referer"), true);
        redir.addFlashAttribute("successMessage", "Client updated successfully");

        return redirectView;
    }

    @RequestMapping("/control/updateClient")
    public String controlUpdateClient(@RequestParam("clientId") int id, Model model) {

        ClientDto clientDto = new ClientDto();
        clientDto.setClient(clientServiceMVC.get(id));
        clientDto.setOperationType("update");
        model.addAttribute("model", clientDto);
        model.addAttribute("clientContracts", clientDto.getClient().getContractClient());

        return "control/update-client-info-control-form";
    }

    @RequestMapping("/control/deleteClient")
    public RedirectView deleteClient(@RequestParam("clientId") int id,
                                     HttpServletRequest request, RedirectAttributes redir) {

        clientServiceMVC.delete(id);
        RedirectView redirectView = new RedirectView(request.getHeader("Referer"), true);
        redir.addFlashAttribute("successMessage", "Client deleted successfully");

        return redirectView;
    }

    @RequestMapping("/client/updateClient")
    public String clientUpdateClient(Principal principal, Model model) {

        ClientDto clientDto = new ClientDto();
        clientDto.setClient(clientServiceMVC.getByEmail(principal.getName()));
        clientDto.setOperationType("update");
        model.addAttribute("model", clientDto);
        model.addAttribute("clientContracts", clientDto.getClient().getContractClient());

        return "client/update-client-info-client-form";

    }

    @RequestMapping("/client/changePasswordClient")
    public String changePassword(@RequestParam("clientId") int clientId, Model model) {

        ClientDto clientDto = new ClientDto();
        clientDto.setClient(clientServiceMVC.get(clientId));
        model.addAttribute("model", clientDto);

        return "client/change-password-client";

    }

    @RequestMapping("/client/addMoneyToAccaunt")
    public String addMoney(@RequestParam("clientId") int clientId, Model model) {

        ClientDto clientDto = new ClientDto();
        clientDto.setClient(clientServiceMVC.get(clientId));
        clientDto.setOperationType("money");

        model.addAttribute("model", clientDto);

        return "client/add-money-to-account-client-form";
    }

    @RequestMapping("/client/moneyProcess")
    public RedirectView addMoneyCheck(@ModelAttribute("model") ClientDto clientDto,
                                      HttpServletRequest request, RedirectAttributes redir) {

        clientServiceMVC.addMoney(clientDto);
        RedirectView redirectView = new RedirectView(request.getHeader("Referer"), true);
        redir.addFlashAttribute("successMessage", "Money added successfully");

        return redirectView;
    }
}
