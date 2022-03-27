package com.school.controller;

import com.school.customException.BusinessLogicException;
import com.school.database.entity.Client;
import com.school.database.entity.Number;
import com.school.dto.ClientDto;
import com.school.service.contracts.ClientService;
import com.school.service.contracts.NumberService;
import com.school.service.contracts.ServiceMVC;
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

    private final ClientService clientServiceMVC;

    private final NumberService numberServiceMVC;

    ClientController(ClientService clientServiceMVC, NumberService numberServiceMVC) {
        this.clientServiceMVC = clientServiceMVC;
        this.numberServiceMVC = numberServiceMVC;
    }

    @RequestMapping("/control/allClients")
    public String showAllClients(Model model, @ModelAttribute("errorMessage") String errorMessage) {

        List<Client> allClients = clientServiceMVC.getAll();
        model.addAttribute("allClients", allClients);

        return "control/all-clients";
    }

    @RequestMapping("/control/addNewClient")
    public String addNewClient(Model model,
                               @ModelAttribute("errorMessage") String errorMessage) {

        ClientDto tmp = new ClientDto();
        tmp.setClient(new Client());
        tmp.setOperationType("add");
        tmp.getClient().setClientNumberReadyToWorkStatus(true);
        List<Number> allAvailableNumbers = numberServiceMVC.getAllUnused();
        tmp.setStringsNumbers(tmp.wrapAvailableNumbersInString(allAvailableNumbers));
        model.addAttribute("model", tmp);
        return "control/add-client-info-control-form";
    }

    @RequestMapping("/common/saveClient")
    public String saveClient(@ModelAttribute("model") ClientDto clientDto, HttpServletRequest request,
                             @ModelAttribute("errorMessage") String errorMessage) {

        if (clientDto.getClient().getPasswordLogIn() == null)
            if ( ( (clientDto.getPasswordString() != null) && (clientDto.getPasswordString2() != null) ) &&
                ((!clientDto.getPasswordString().equals("")) && (!clientDto.getPasswordString2().equals(""))) ){
                if (clientDto.getPasswordString().equals(clientDto.getPasswordString2())) {
                    String encodedPassword = new BCryptPasswordEncoder().encode(clientDto.getPasswordString());
                    clientDto.getClient().setPasswordLogIn(encodedPassword);
                } else {
                    throw new BusinessLogicException("User's new password doesn't match",
                            "redirect:/client/changePasswordClient?clientId=" + clientDto.getClient().getId(),
                            "Input passwords doesn't match");
                }
            } else {

                clientDto.getClient().setPasswordLogIn(clientServiceMVC.get(clientDto.getClient().getId()).getPasswordLogIn());
            }

        if (clientDto.getOperationType().equals("add")) {

            if (!clientDto.checkIsUserEmailUniqueOrNot(clientServiceMVC.getAll())) {
                throw new BusinessLogicException("User try to add user with already defined " +
                        "email address", "redirect:/control/addNewClient",
                        "User with this email already in system");
            }

        }

        Number number = null;
        if ( (clientDto.getOperationType() != null) && (clientDto.getOperationType().equals("add")) ) {
            String roleCast = clientDto.getClient().getUserRole().replace(",", "");
            clientDto.getClient().setUserRole(roleCast);
            if (clientDto.getClient().getUserRole().equals("client")) {
                number = numberServiceMVC.getByPhoneNumber(clientDto.getClient().getPhoneNumber());
                number.setAvailableToConnectStatus(false);
                numberServiceMVC.update(number);
            }

            String encodedPassword = new BCryptPasswordEncoder().encode(clientDto.getClient().getPasswordLogIn());
            clientDto.getClient().setPasswordLogIn(encodedPassword);
        }

        if ((clientDto.getOperationType().equals("update")) && (clientDto.getPasswordString() != null)) {
            String encodedPassword = new BCryptPasswordEncoder().encode(clientDto.getPasswordString());
            if (!encodedPassword.equals(clientDto.getClient().getPasswordLogIn()))
                clientDto.getClient().setPasswordLogIn(encodedPassword);
        }


        clientServiceMVC.save(clientDto);
        if (request.isUserInRole("ROLE_control"))
            return "redirect:/control/allClients";

        return "redirect:/";
    }

    @RequestMapping("/control/updateClient")
    public String controlUpdateClient(@RequestParam("clientId") int id, Model model,
                                      @ModelAttribute("errorMessage") String errorMessage) {

        ClientDto clientDto = new ClientDto();
        clientDto.setClient(clientServiceMVC.get(id));
        clientDto.setOperationType("update");
        model.addAttribute("model", clientDto);

        return "control/update-client-info-control-form";
    }

    @RequestMapping("/common/lockClient")
    public String controlLockClient(@RequestParam("clientId") int id, Model model,
                                    HttpServletRequest request, @ModelAttribute("errorMessage") String errorMessage) {

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

        return this.saveClient(clientDto, request, errorMessage);
    }

    @RequestMapping("/common/unlockClient")
    public String controlUnlockClient(@RequestParam("clientId") int id, Model model,
                                      HttpServletRequest request, @ModelAttribute("errorMessage") String errorMessage) {

        ClientDto clientDto = new ClientDto();
        clientDto.setClient(clientServiceMVC.get(id));
        clientDto.setOperationType("unlock");
        clientDto.getClient().setClientNumberReadyToWorkStatus(true);
        clientDto.getClient().setRoleOfUserWhoBlockedNumber(null);
        model.addAttribute("model", clientDto);
        return this.saveClient(clientDto, request, errorMessage);
    }

    @RequestMapping("/control/deleteClient")
    public String deleteClient(@RequestParam("clientId") int id,
                               @ModelAttribute("errorMessage") String errorMessage) {

        Client client = clientServiceMVC.get(id);
        if (client.getUserRole().equals("client")) {
            Number number = numberServiceMVC.getByPhoneNumber(client.getPhoneNumber());
            number.setAvailableToConnectStatus(true);
            numberServiceMVC.update(number);

        }
        clientServiceMVC.delete(id);

        return "redirect:/control/allClients";
    }

    @RequestMapping("/client/updateClient")
    public String clientUpdateClient(Principal principal, Model model,
                                     @ModelAttribute("errorMessage") String errorMessage) {

        ClientDto clientDto = new ClientDto();
        clientDto.setClient(clientServiceMVC.getByEmail(principal.getName()));
        clientDto.setOperationType("update");
        model.addAttribute("model", clientDto);

        return "client/update-client-info-client-form";

    }

    @RequestMapping("/control/inputNumberToSearch")
    public String getSearchData(Model model, @ModelAttribute("errorMessage") String errorMessage) {

        ClientDto clientDto = new ClientDto();
        clientDto.setStringsNumbers(clientDto.wrapUsedNumbersInString(numberServiceMVC.getAllUsed()));
        model.addAttribute("model", clientDto);
        return "control/input-number-for-search";
    }

    @RequestMapping("/control/searchByPhoneNumber")
    public String searchClientByPhoneNumber(@RequestParam("userPhoneNumber") String phoneNumber, Model model,
                                            @ModelAttribute("errorMessage") String errorMessage) {

        List<Client> clients = clientServiceMVC.getAll();
        int resultId = -1;
        for(Client tmp : clients) {
            if (tmp.getPhoneNumber().equals(phoneNumber))
                resultId = tmp.getId();
        }
        if (resultId != -1) {
            return this.controlUpdateClient(resultId, model, errorMessage);
        } else return this.addNewClient(model, errorMessage);
    }

    @RequestMapping("/client/changePasswordClient")
    public String changePassword(@RequestParam("clientId") int clientId, Model model, @ModelAttribute("errorMessage") String errorMessage) {

        ClientDto clientDto = new ClientDto();
        clientDto.setClient(clientServiceMVC.get(clientId));

        model.addAttribute("model", clientDto);

        return "client/change-password-client";

    }
}
