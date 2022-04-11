package com.school.controller;

import com.school.database.entity.Contract;
import com.school.dto.ContractDto;
import com.school.service.contracts.ClientService;
import com.school.service.contracts.ContractService;
import com.school.service.contracts.NumberService;
import com.school.service.contracts.TariffService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * This controller redirect to different view which need to work with contracts
 */
@Controller
public class ContractController {

    private final ContractService contractServiceMVC;
    private final TariffService tariffServiceMVC;
    private final ClientService clientServiceMVC;
    private final NumberService numberServiceMVC;

    ContractController(ContractService contractServiceMVC, ClientService clientServiceMVC,
                       TariffService tariffServiceMVC, NumberService numberServiceMVC) {
        this.contractServiceMVC = contractServiceMVC;
        this.tariffServiceMVC = tariffServiceMVC;
        this.clientServiceMVC = clientServiceMVC;
        this.numberServiceMVC = numberServiceMVC;
    }

    /**
     * This method show all contracts which contains in system
     * @param contractDto contract data transfer object with necessary data
     * @param pageNumber number of page with contracts
     * @param model model
     * @return view with page of contracts
     */
    @RequestMapping("/control/allContracts")
    public String showAllContracts(@ModelAttribute("model") ContractDto contractDto,
                                   @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                   Model model) {

        model.addAttribute("model", contractDto);
        model.addAttribute("allContracts", contractServiceMVC.getPageOfContracts(contractDto, pageNumber));
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("numberOfPages", contractServiceMVC.getNumberOfPages(contractDto.getPageSize()));

        return "control/all-contracts";
    }

    /**
     * This method prepares data to form for creating new contract when it created by "control" user
     * @param model model
     * @return view with form to add new contract for user
     */
    @RequestMapping("/control/addNewContract")
    public String addNewContract(Model model) {

        ContractDto tmp = new ContractDto();
        tmp.setContract(new Contract());
        tmp.setStringsNumbers(numberServiceMVC.getAllUnused());
        model.addAttribute("tariffsList", tariffServiceMVC.getAll());
        model.addAttribute("clientsList", clientServiceMVC.getAll());
        model.addAttribute("model", tmp);
        return "control/add-contract-info-control-form";
    }

    /**
     * This method prepares data to form for creating new contract when it created by "client" user
     * @param model model
     * @param principal user's data
     * @return view with form to add new contract for client which call this method
     */
    @RequestMapping("/client/addNewContract")
    public String addNewContractClient(Model model, Principal principal) {

        ContractDto tmp = new ContractDto();
        tmp.setContract(new Contract());
        tmp.setId(clientServiceMVC.getByEmail(principal.getName()).getId());
        tmp.setStringsNumbers(numberServiceMVC.getAllUnused());
        model.addAttribute("tariffsList", tariffServiceMVC.getAll());
        model.addAttribute("model", tmp);
        return "client/add-contract-info-client-form";
    }

    /**
     * This method saves new contract
     * @param contractDto contract data transfer object with necessary data
     * @param request http request
     * @param redir container for redirected attributes
     * @return view with message about save procedure result
     */
    @RequestMapping("/common/saveContract")
    public RedirectView saveContract(@ModelAttribute("model") ContractDto contractDto,
                                     HttpServletRequest request, RedirectAttributes redir) {

        contractServiceMVC.save(contractDto);
        RedirectView redirectView = new RedirectView(request.getHeader("Referer"), true);
        redir.addFlashAttribute("successMessage", "Contract added successfully");

        return redirectView;
    }

    /**
     * This method updates contract data
     * @param contractDto contract data transfer object with necessary data
     * @param request http request
     * @param redir container for redirected attributes
     * @return view with message about update procedure result
     */
    @RequestMapping("/common/patchContract")
    public RedirectView patchContract(@ModelAttribute("model") ContractDto contractDto,
                                     HttpServletRequest request, RedirectAttributes redir) {

        contractServiceMVC.update(contractDto);
        RedirectView redirectView = new RedirectView(request.getHeader("Referer"), true);
        redir.addFlashAttribute("successMessage", "Contract updated successfully");

        return redirectView;
    }

    /**
     * This method prepares information to update contract form when it called from "control" user
     * @param id contract id
     * @param model model
     * @return view with form for update contracts data
     */
    @RequestMapping("/control/updateContract")
    public String controlUpdateContract(@RequestParam("contractId") int id, Model model) {

        ContractDto tmp = new ContractDto();
        tmp.setContract(contractServiceMVC.get(id));
        model.addAttribute("connectedOptions", tmp.castConnectedOptionsInStrings(tmp.getContract().getConnectedOptions()));
        model.addAttribute("availableForTariffOptionsList", tmp.getContract().getContractTariff().getOptions());
        model.addAttribute("connectedTariff", tmp.getContract().getContractTariff());
        model.addAttribute("tariffsList", tariffServiceMVC.getAllAvailable());
        model.addAttribute("model", tmp);

        return "control/update-contract-info-control-form";
    }

    /**
     * This method deletes contract from system
     * @param id id of contract
     * @param request http request
     * @param redir container for redirected attributes
     * @return view with message about delete procedure result
     */
    @RequestMapping("/common/deleteContract")
    public RedirectView deleteContract(@RequestParam("contractId") int id,
                                       HttpServletRequest request, RedirectAttributes redir) {

        contractServiceMVC.delete(id);
        RedirectView redirectView = new RedirectView(request.getHeader("Referer"), true);
        redir.addFlashAttribute("successMessage", "Contract deleted successfully");

        return redirectView;
    }

    /**
     * This method show all contracts of one client
     * @param principal client data
     * @param contractDto contract data transfer object with necessary data
     * @param pageNumber page of contracts
     * @param model model
     * @return view with client's contracts
     */
    @RequestMapping("/client/allContracts")
    public String clientAllContracts(Principal principal,
                                     @ModelAttribute("model") ContractDto contractDto,
                                     @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                     Model model) {

        model.addAttribute("model", contractDto);
        model.addAttribute("allContracts", contractServiceMVC.getPageOfClientContracts(contractDto, pageNumber, principal.getName()));
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("numberOfPages", contractServiceMVC.getNumberOfClientContractPages(contractDto.getPageSize(), principal.getName()));

        return "client/all-contracts";
    }

    /**
     * This method prepares data for contract update when update contracts called from "client" user
     * @param contractId id of contract
     * @param model model
     * @return view with form for update contracts data
     */
    @RequestMapping("/client/updateContract")
    public String clientUpdateContract(@RequestParam("contractId") int contractId, Model model) {

        ContractDto tmp = new ContractDto();
        tmp.setContract(contractServiceMVC.get(contractId));
        model.addAttribute("connectedOptions", tmp.castConnectedOptionsInStrings(tmp.getContract().getConnectedOptions()));
        model.addAttribute("availableForTariffOptionsList", tmp.getContract().getContractTariff().getOptions());
        model.addAttribute("connectedTariff", tmp.getContract().getContractTariff());
        model.addAttribute("tariffsList", tariffServiceMVC.getAllAvailable());
        model.addAttribute("model", tmp);


        return "client/update-contract-info-client-form";

    }

    /**
     * This method set contact block status of contract
     * @param id id of contract
     * @param request http request
     * @return previous view, where lock option was called
     */
    @RequestMapping("/common/lockContract")
    public String controlLockClient(@RequestParam("contractId") int id, HttpServletRequest request) {

        ContractDto contractDto = new ContractDto();
        contractDto.setId(id);

        if (request.isUserInRole("ROLE_control")) {
            contractDto.setBlockedRole("control");
        } else {
            contractDto.setBlockedRole("client");
        }

        contractServiceMVC.lock(contractDto);

        return "redirect:" + request.getHeader("Referer");
    }

    /**
     * This method set contact unblock status of contract
     * @param id id of contract
     * @param request http request
     * @return previous view, where unlock option was called
     */
    @RequestMapping("/common/unlockContract")
    public String controlUnlockClient(@RequestParam("contractId") int id, HttpServletRequest request) {

        ContractDto contractDto = new ContractDto();
        contractDto.setId(id);

        contractServiceMVC.unlock(contractDto);

        return "redirect:" + request.getHeader("Referer");
    }

    /**
     * This method prepares used phone numbers to search by it
     * @param model model
     * @return view with search bar
     */
    @RequestMapping("/control/inputNumberToSearch")
    public String getSearchData(Model model) {

        ContractDto contractDto = new ContractDto();
        contractDto.setStringsNumbers(numberServiceMVC.getAllUsed());
        model.addAttribute("model", contractDto);
        return "control/input-number-for-search";
    }

    /**
     * This method returns client which has contract with this number
     * @param phoneNumber number which used to search
     * @param model model
     * @return view with client and his contracts
     */
    @RequestMapping("/control/searchByPhoneNumber")
    public String searchContractByPhoneNumber(@RequestParam("userPhoneNumber") String phoneNumber, Model model) {

        Contract contract = contractServiceMVC.getByPhoneNumber(phoneNumber);
        model.addAttribute("clientId", contract.getContractClient().getId());

        return "redirect:/control/updateClient";
    }
}
