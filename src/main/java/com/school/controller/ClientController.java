package com.school.controller;

import com.school.database.entity.Client;
import com.school.dto.ClientDto;
import com.school.service.contracts.ClientService;
import com.school.service.contracts.NumberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class ClientController {

    private final ClientService clientServiceMVC;

    private final NumberService numberServiceMVC;

    ClientController(ClientService clientServiceMVC, NumberService numberServiceMVC) {
        this.clientServiceMVC = clientServiceMVC;
        this.numberServiceMVC = numberServiceMVC;
    }

    @RequestMapping("/control/allClients")
    public String showAllClients(Model model) {

        model.addAttribute("allClients", clientServiceMVC.getAll());

        return "control/all-clients";
    }

    @RequestMapping("/control/addNewClient")
    public String addNewClient(Model model) {

        ClientDto tmp = new ClientDto();
        tmp.setClient(new Client());
        tmp.setOperationType("add");
        tmp.getClient().setClientNumberReadyToWorkStatus(true);
        tmp.setStringsNumbers(numberServiceMVC.getAllUnused());
        model.addAttribute("model", tmp);
        return "control/add-client-info-control-form";
    }

    @RequestMapping("/common/saveClient")
    public String saveClient(@ModelAttribute("model") ClientDto clientDto, HttpServletRequest request) {

        if (clientDto.getOperationType().equals("add")) {

            clientServiceMVC.save(clientDto);
        } else clientServiceMVC.update(clientDto);

        if (request.isUserInRole("ROLE_control"))
            return "redirect:/control/allClients";

        return "redirect:/";
    }

    @RequestMapping("/control/updateClient")
    public String controlUpdateClient(@RequestParam("clientId") int id, Model model) {

        ClientDto clientDto = new ClientDto();
        clientDto.setClient(clientServiceMVC.get(id));
        clientDto.setOperationType("update");
        model.addAttribute("model", clientDto);

        return "control/update-client-info-control-form";
    }

    @RequestMapping("/common/lockClient")
    public String controlLockClient(@RequestParam("clientId") int id, HttpServletRequest request) {

        ClientDto clientDto = new ClientDto();
        clientDto.setId(id);

        if (request.isUserInRole("ROLE_control")) {
            clientDto.setBlockedRole("control");
        } else {
            clientDto.setBlockedRole("client");
        }

        clientServiceMVC.lock(clientDto);

        if (request.isUserInRole("ROLE_control"))
            return "redirect:/control/allClients";

        return "redirect:/client/updateClient";
    }

    @RequestMapping("/common/unlockClient")
    public String controlUnlockClient(@RequestParam("clientId") int id, HttpServletRequest request) {

        ClientDto clientDto = new ClientDto();
        clientDto.setId(id);

        clientServiceMVC.unlock(clientDto);

        if (request.isUserInRole("ROLE_control"))
            return "redirect:/control/allClients";

        return "redirect:/client/updateClient";
    }

    @RequestMapping("/control/deleteClient")
    public String deleteClient(@RequestParam("clientId") int id) {

        clientServiceMVC.delete(id);

        return "redirect:/control/allClients";
    }

    @RequestMapping("/client/updateClient")
    public String clientUpdateClient(Principal principal, Model model) {

        ClientDto clientDto = new ClientDto();
        clientDto.setClient(clientServiceMVC.getByEmail(principal.getName()));
        clientDto.setOperationType("update");
        model.addAttribute("model", clientDto);

        return "client/update-client-info-client-form";

    }

    @RequestMapping("/control/inputNumberToSearch")
    public String getSearchData(Model model) {

        ClientDto clientDto = new ClientDto();
        clientDto.setStringsNumbers(numberServiceMVC.getAllUsed());
        model.addAttribute("model", clientDto);
        return "control/input-number-for-search";
    }

    @RequestMapping("/control/searchByPhoneNumber")
    public String searchClientByPhoneNumber(@RequestParam("userPhoneNumber") String phoneNumber, Model model) {

        Client client = clientServiceMVC.getByPhoneNumber(phoneNumber);

        return this.controlUpdateClient(client.getId(), model);
    }

    @RequestMapping("/client/changePasswordClient")
    public String changePassword(@RequestParam("clientId") int clientId, Model model) {

        ClientDto clientDto = new ClientDto();
        clientDto.setClient(clientServiceMVC.get(clientId));

        model.addAttribute("model", clientDto);

        return "client/change-password-client";

    }
}
