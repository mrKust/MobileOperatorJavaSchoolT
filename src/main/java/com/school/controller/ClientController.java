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

/**
 * This controller works with methods which represent client information and activities
 */
@Controller
public class ClientController {

    private final ClientService clientServiceMVC;

    ClientController(ClientService clientServiceMVC) {
        this.clientServiceMVC = clientServiceMVC;
    }

    /**
     * This method shows all clients registered in system
     * @param clientDto client data transfer object with necessary data
     * @param pageNumber number of page
     * @param model model
     * @return view with clients table
     */
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

    /**
     * This method creates necessary data before register new user in system
     * @param model model
     * @return view with registration form
     */
    @RequestMapping("/control/addNewClient")
    public String addNewClient(Model model) {

        ClientDto tmp = new ClientDto();
        tmp.setClient(new Client());
        model.addAttribute("model", tmp);
        return "control/add-client-info-control-form";
    }

    /**
     * This method saves new client in system
     * @param clientDto client data transfer object with necessary data
     * @param request http request
     * @param redir container for redirected message
     * @return view after save
     */
    @RequestMapping("/common/saveClient")
    public RedirectView saveClient(@ModelAttribute("model") ClientDto clientDto,
                                   HttpServletRequest request, RedirectAttributes redir) {

        clientServiceMVC.save(clientDto);
        RedirectView redirectView = new RedirectView(request.getHeader("Referer"), true);
        redir.addFlashAttribute("successMessage", "Client added successfully");

        return redirectView;
    }

    /**
     * This method updates clients information
     * @param clientDto client data transfer object with necessary data
     * @param request http request
     * @param redir container for redirected message
     * @return view after save
     */
    @RequestMapping("/common/patchClient")
    public RedirectView patchClient(@ModelAttribute("model") ClientDto clientDto,
                                   HttpServletRequest request, RedirectAttributes redir) {

        clientServiceMVC.update(clientDto);
        RedirectView redirectView = new RedirectView(request.getHeader("Referer"), true);
        redir.addFlashAttribute("successMessage", "Client updated successfully");

        return redirectView;
    }

    /**
     * This method prepare necessary information for update client's form when it called from "control" user
     * @param id client's id
     * @param model model
     * @return view with form for update client's info
     */
    @RequestMapping("/control/updateClient")
    public String controlUpdateClient(@RequestParam("clientId") int id, Model model) {

        ClientDto clientDto = new ClientDto();
        clientDto.setClient(clientServiceMVC.get(id));
        clientDto.setOperationType("update");
        model.addAttribute("model", clientDto);
        model.addAttribute("clientContracts", clientDto.getClient().getContractClient());

        return "control/update-client-info-control-form";
    }

    /**
     * This methods delete client from system
     * @param id id of client
     * @param request http request
     * @param redir container for redirected attributes
     * @return view with message
     */
    @RequestMapping("/control/deleteClient")
    public RedirectView deleteClient(@RequestParam("clientId") int id,
                                     HttpServletRequest request, RedirectAttributes redir) {

        clientServiceMVC.delete(id);
        RedirectView redirectView = new RedirectView(request.getHeader("Referer"), true);
        redir.addFlashAttribute("successMessage", "Client deleted successfully");

        return redirectView;
    }

    /**
     * This method prepare necessary information for update client's form when it called from "client" user
     * @param principal user data
     * @param model model
     * @return view with form to update client's info
     */
    @RequestMapping("/client/updateClient")
    public String clientUpdateClient(Principal principal, Model model) {

        ClientDto clientDto = new ClientDto();
        clientDto.setClient(clientServiceMVC.getByEmail(principal.getName()));
        clientDto.setOperationType("update");
        model.addAttribute("model", clientDto);
        model.addAttribute("clientContracts", clientDto.getClient().getContractClient());

        return "client/update-client-info-client-form";

    }

    /**
     * This method prepares data to client's change password event
     * @param clientId id of client
     * @param model model
     * @return view with form to switch password
     */
    @RequestMapping("/client/changePasswordClient")
    public String changePassword(@RequestParam("clientId") int clientId, Model model) {

        ClientDto clientDto = new ClientDto();
        clientDto.setClient(clientServiceMVC.get(clientId));
        model.addAttribute("model", clientDto);

        return "client/change-password-client";

    }

    /**
     * This method prepares info to client's add money to balance event
     * @param clientId id of client
     * @param model model
     * @return view with form to add money on client's balance
     */
    @RequestMapping("/client/addMoneyToAccaunt")
    public String addMoney(@RequestParam("clientId") int clientId, Model model) {

        ClientDto clientDto = new ClientDto();
        clientDto.setClient(clientServiceMVC.get(clientId));
        clientDto.setOperationType("money");

        model.addAttribute("model", clientDto);

        return "client/add-money-to-account-client-form";
    }

    /**
     * This method sends money data to process for it correct
     * @param clientDto client data transfer object with necessary data
     * @param request http request
     * @param redir container for redirected attributes
     * @return view with message about money process
     */
    @RequestMapping("/client/moneyProcess")
    public RedirectView addMoneyCheck(@ModelAttribute("model") ClientDto clientDto,
                                      HttpServletRequest request, RedirectAttributes redir) {

        clientServiceMVC.addMoney(clientDto);
        RedirectView redirectView = new RedirectView(request.getHeader("Referer"), true);
        redir.addFlashAttribute("successMessage", "Money added successfully");

        return redirectView;
    }
}
