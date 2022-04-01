package com.school.controller;

import com.school.database.entity.Client;
import com.school.database.entity.Contract;
import com.school.database.entity.Options;
import com.school.dto.ContractDto;
import com.school.dto.TariffDto;
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
import java.util.List;

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

    @RequestMapping("/common/saveContract")
    public RedirectView saveContract(@ModelAttribute("model") ContractDto contractDto,
                                     HttpServletRequest request, RedirectAttributes redir) {

        contractServiceMVC.save(contractDto);
        RedirectView redirectView = new RedirectView(request.getHeader("Referer"), true);
        redir.addFlashAttribute("successMessage", "Contract added successfully");

        return redirectView;
    }

    @RequestMapping("/common/patchContract")
    public RedirectView patchContract(@ModelAttribute("model") ContractDto contractDto,
                                     HttpServletRequest request, RedirectAttributes redir) {

        contractServiceMVC.update(contractDto);
        RedirectView redirectView = new RedirectView(request.getHeader("Referer"), true);
        redir.addFlashAttribute("successMessage", "Contract updated successfully");

        return redirectView;
    }

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

    @RequestMapping("/common/deleteContract")
    public RedirectView deleteContract(@RequestParam("contractId") int id,
                                       HttpServletRequest request, RedirectAttributes redir) {

        contractServiceMVC.delete(id);
        RedirectView redirectView = new RedirectView(request.getHeader("Referer"), true);
        redir.addFlashAttribute("successMessage", "Contract deleted successfully");

        return redirectView;
    }

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

    @RequestMapping("/common/unlockContract")
    public String controlUnlockClient(@RequestParam("contractId") int id, HttpServletRequest request) {

        ContractDto contractDto = new ContractDto();
        contractDto.setId(id);

        contractServiceMVC.unlock(contractDto);

        return "redirect:" + request.getHeader("Referer");
    }

    @RequestMapping("/control/inputNumberToSearch")
    public String getSearchData(Model model) {

        ContractDto contractDto = new ContractDto();
        contractDto.setStringsNumbers(numberServiceMVC.getAllUsed());
        model.addAttribute("model", contractDto);
        return "control/input-number-for-search";
    }

    @RequestMapping("/control/searchByPhoneNumber")
    public String searchContractByPhoneNumber(@RequestParam("userPhoneNumber") String phoneNumber, Model model) {

        Contract contract = contractServiceMVC.getByPhoneNumber(phoneNumber);
        model.addAttribute("clientId", contract.getContractClient().getId());

        return "redirect:/control/updateClient";
    }
}
