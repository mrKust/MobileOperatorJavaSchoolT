package com.school.controller;

import com.school.customException.BusinessLogicException;
import com.school.database.entity.Client;
import com.school.database.entity.Contract;
import com.school.database.entity.Options;
import com.school.database.entity.Tariff;
import com.school.dto.ContractDto;
import com.school.service.contracts.ClientService;
import com.school.service.contracts.ContractService;
import com.school.service.contracts.ServiceMVC;
import com.school.service.contracts.TariffService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
public class ContractController {

    private final ContractService contractServiceMVC;

    private final TariffService tariffServiceMVC;

    private final ClientService clientServiceMVC;

    ContractController(ContractService contractServiceMVC, ClientService clientServiceMVC, TariffService tariffServiceMVC) {
        this.contractServiceMVC = contractServiceMVC;
        this.tariffServiceMVC = tariffServiceMVC;
        this.clientServiceMVC = clientServiceMVC;

    }

    @RequestMapping("/control/allContracts")
    public String showAllContracts(Model model, @ModelAttribute("errorMessage") String errorMessage) {

        List<Contract> allContracts = contractServiceMVC.getAll();
        model.addAttribute("allContracts", allContracts);

        return "control/all-contracts";
    }

    @RequestMapping("/control/addNewContract")
    public String addNewContract(Model model, @ModelAttribute("errorMessage") String errorMessage) {

        List<Tariff> tariffList = tariffServiceMVC.getAll();
        List<Client> clientList = clientServiceMVC.getAll();
        ContractDto tmp = new ContractDto();
        tmp.setContract(new Contract());
        tmp.setOperationType("add");
        model.addAttribute("tariffsList", tariffList);
        model.addAttribute("clientsList", clientList);
        model.addAttribute("model", tmp);
        return "control/add-contract-info-control-form";
    }

    @RequestMapping("/common/saveContract")
    public String saveContract(@ModelAttribute("model") ContractDto contractDto,
                               HttpServletRequest request, Model model, Principal principal,
                               @ModelAttribute("errorMessage") String errorMessage) {

        contractServiceMVC.save(contractDto);

        if (request.isUserInRole("ROLE_control"))
            return "redirect:/control/allContracts";

        return "redirect:/";
    }

    @RequestMapping("/control/updateContract")
    public String controlUpdateContract(@RequestParam("contractId") int id, Model model,
                                        @ModelAttribute("errorMessage") String errorMessage) {

        List<Tariff> tariffList = tariffServiceMVC.getAll();
        ContractDto tmp = new ContractDto();
        tmp.setContract(contractServiceMVC.get(id));
        Tariff connectedTariff = tmp.getContract().getContractTariff();
        List<Options> defaultTariffOptions = connectedTariff.getOptions();
        List<Options> contractAvailableOptions = tmp.getContract().getConnectedOptions();
        tmp.setConnectedOptions(tmp.castConnectedOptionsInStrings(contractAvailableOptions));
        tmp.setOperationType("update");
        model.addAttribute("connectedOptionsList", contractAvailableOptions);
        model.addAttribute("availableForTariffOptionsList", defaultTariffOptions);
        model.addAttribute("connectedTariff", connectedTariff);
        model.addAttribute("tariffsList", tariffList);
        model.addAttribute("model", tmp);

        return "control/update-contract-info-control-form";
    }

    @RequestMapping("/control/deleteContract")
    public String deleteContract(@RequestParam("contractId") int id,
                                 @ModelAttribute("errorMessage") String errorMessage) {

        contractServiceMVC.delete(id);

        return "redirect:/control/allContracts";
    }

    @RequestMapping("/client/updateContract")
    public String clientUpdateContract(Principal principal, Model model,
                                       @ModelAttribute("errorMessage") String errorMessage) {

        Client client = clientServiceMVC.getByEmail(principal.getName());
        Contract contract = client.getContract();

        if (contract == null) {
            throw new NullPointerException("User " + principal.getName() +
                    " try to get his contract, but contract " +
                    "wasn't created / You haven't contract yet. Contact to administration");
        }


        List<Tariff> tariffList = tariffServiceMVC.getAll();
        ContractDto tmp = new ContractDto();
        tmp.setContract(contract);
        Tariff connectedTariff = tmp.getContract().getContractTariff();
        List<Options> defaultTariffOptions = connectedTariff.getOptions();
        List<Options> contractAvailableOptions = tmp.getContract().getConnectedOptions();
        tmp.setConnectedOptions(tmp.castConnectedOptionsInStrings(contractAvailableOptions));
        tmp.setOperationType("update");
        model.addAttribute("connectedOptionsList", contractAvailableOptions);
        model.addAttribute("availableForTariffOptionsList", defaultTariffOptions);
        model.addAttribute("connectedTariff", connectedTariff);
        model.addAttribute("tariffsList", tariffList);
        model.addAttribute("model", tmp);


        return "client/update-contract-info-client-form";

    }
}
