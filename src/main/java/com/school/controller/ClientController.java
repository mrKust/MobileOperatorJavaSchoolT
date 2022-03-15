package com.school.controller;

import com.school.customException.BusinessLogicException;
import com.school.database.entity.Client;
import com.school.database.entity.Number;
import com.school.dto.ClientDto;
import com.school.service.ServiceMVC;
import org.apache.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    private final ServiceMVC<Client> clientServiceMVC;

    private final ServiceMVC<Number> numberServiceMVC;

    private static final Logger LOG = Logger.getLogger(ClientController.class);

    ClientController(ServiceMVC<Client> clientServiceMVC, ServiceMVC<Number> numberServiceMVC) {
        this.clientServiceMVC = clientServiceMVC;
        this.numberServiceMVC = numberServiceMVC;
    }

    @RequestMapping("/control/allClients")
    public String showAllClients(Model model) {

        List<Client> allClients = clientServiceMVC.getAll();
        model.addAttribute("allClients", allClients);

        return "control/all-clients";
    }

    @RequestMapping("/control/addNewClient")
    public String addNewClient(Model model) {

        ClientDto tmp = new ClientDto();
        tmp.setClient(new Client());
        tmp.setOperationType("add");
        tmp.getClient().setClientNumberReadyToWorkStatus(true);
        List<Number> allNumbers = numberServiceMVC.getAll();
        tmp.setStringsNumbers(tmp.wrapAvailableNumbersInJsonString(allNumbers));
        model.addAttribute("model", tmp);
        return "control/add-client-info-control-form";
    }

    @RequestMapping("/common/saveClient")
    public String saveClient(@ModelAttribute("model") ClientDto clientDto, HttpServletRequest request) {

        if (clientDto.getOperationType().equals("add")) {

            if (!clientDto.checkIsUserEmailUniqueOrNot(clientServiceMVC.getAll())) {
                throw new BusinessLogicException("User try to add user with already defined " +
                        "email address", "redirect:/control/addNewClient");
            }

        }

        Number number = null;
        if ( (clientDto.getOperationType() != null) && (clientDto.getOperationType().equals("add")) ) {
            String roleCast = clientDto.getClient().getUserRole().replace(",", "");
            clientDto.getClient().setUserRole(roleCast);
            if (clientDto.getClient().getUserRole().equals("client")) {
                number = numberServiceMVC.getByName(clientDto.getClient().getPhoneNumber());
                number.setAvailableToConnectStatus(false);
                numberServiceMVC.save(number);
            }

            String encodedPassword = new BCryptPasswordEncoder().encode(clientDto.getClient().getPasswordLogIn());
            clientDto.getClient().setPasswordLogIn(encodedPassword);
        }

        if ((clientDto.getOperationType().equals("update")) && (clientDto.getPasswordString() != null)) {
            String encodedPassword = new BCryptPasswordEncoder().encode(clientDto.getPasswordString());
            if (!encodedPassword.equals(clientDto.getClient().getPasswordLogIn()))
                clientDto.getClient().setPasswordLogIn(encodedPassword);
        }


        clientServiceMVC.save(clientDto.getClient());
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
    public String controlLockClient(@RequestParam("clientId") int id, Model model, HttpServletRequest request) {

        ClientDto clientDto = new ClientDto();
        clientDto.setClient(clientServiceMVC.get(id));
        clientDto.setOperationType("lock");
        clientDto.getClient().setClientNumberReadyToWorkStatus(false);
        if (request.isUserInRole("ROLE_control")) {
            clientDto.getClient().setRoleOfUserWhoBlockedNumber("control");
        } else {
            clientDto.getClient().setRoleOfUserWhoBlockedNumber("client");
        }
        model.addAttribute("model", clientDto);

        return this.saveClient(clientDto, request);
    }

    @RequestMapping("/common/unlockClient")
    public String controlUnlockClient(@RequestParam("clientId") int id, Model model, HttpServletRequest request) {

        ClientDto clientDto = new ClientDto();
        clientDto.setClient(clientServiceMVC.get(id));
        clientDto.setOperationType("unlock");
        clientDto.getClient().setClientNumberReadyToWorkStatus(true);
        clientDto.getClient().setRoleOfUserWhoBlockedNumber(null);
        model.addAttribute("model", clientDto);
        return this.saveClient(clientDto, request);
    }

    @RequestMapping("/control/deleteClient")
    public String deleteClient(@RequestParam("clientId") int id ) {

        Client client = clientServiceMVC.get(id);
        if (client.getUserRole().equals("client")) {
            Number number = numberServiceMVC.getByName(client.getPhoneNumber());
            number.setAvailableToConnectStatus(true);
            numberServiceMVC.save(number);

        }
        clientServiceMVC.delete(id);

        return "redirect:/control/allClients";
    }

    @RequestMapping("/client/updateClient")
    public String clientUpdateClient(Principal principal, Model model) {

        ClientDto clientDto = new ClientDto();
        clientDto.setClient(clientServiceMVC.getByName(principal.getName()));
        clientDto.setOperationType("update");
        clientDto.setPasswordString(clientDto.getClient().getPasswordLogIn());
        model.addAttribute("model", clientDto);

        return "client/update-client-info-client-form";

    }

    @RequestMapping("/control/inputNumberToSearch")
    public String getSearchData(Model model) {

        ClientDto clientDto = new ClientDto();
        clientDto.setStringsNumbers(clientDto.wrapUsedNumbersInJsonString(numberServiceMVC.getAll()));
        model.addAttribute("model", clientDto);
        return "control/input-number-for-search";
    }

    @RequestMapping("/control/searchByPhoneNumber")
    public String searchClientByPhoneNumber(@RequestParam("userPhoneNumber") String phoneNumber, Model model) {

        List<Client> clients = clientServiceMVC.getAll();
        int resultId = -1;
        for(Client tmp : clients) {
            if (tmp.getPhoneNumber().equals(phoneNumber))
                resultId = tmp.getId();
        }
        if (resultId != -1) {
            return this.controlUpdateClient(resultId, model);
        } else return this.addNewClient(model);
    }
}
