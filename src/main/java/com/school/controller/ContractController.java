package com.school.controller;

import com.school.customException.ServiceLayerException;
import com.school.database.entity.Client;
import com.school.database.entity.Contract;
import com.school.database.entity.Options;
import com.school.dto.ContractDto;
import com.school.service.contracts.ClientService;
import com.school.service.contracts.ContractService;
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
    public String showAllContracts(Model model) {

        model.addAttribute("allContracts", contractServiceMVC.getAll());

        return "control/all-contracts";
    }

    @RequestMapping("/control/addNewContract")
    public String addNewContract(Model model) {

        ContractDto tmp = new ContractDto();
        tmp.setContract(new Contract());
        model.addAttribute("tariffsList", tariffServiceMVC.getAll());
        model.addAttribute("clientsList", clientServiceMVC.getAll());
        model.addAttribute("model", tmp);
        return "control/add-contract-info-control-form";
    }

    @RequestMapping("/common/saveContract")
    public String saveContract(@ModelAttribute("model") ContractDto contractDto,
                               HttpServletRequest request) {

        contractServiceMVC.save(contractDto);

        if (request.isUserInRole("ROLE_control"))
            return "redirect:/control/allContracts";

        return "redirect:/";
    }

    @RequestMapping("/control/updateContract")
    public String controlUpdateContract(@RequestParam("contractId") int id, Model model) {

        ContractDto tmp = new ContractDto();
        tmp.setContract(contractServiceMVC.get(id));
        List<Options> contractAvailableOptions = tmp.getContract().getConnectedOptions();
        tmp.setConnectedOptions(tmp.castConnectedOptionsInStrings(contractAvailableOptions));
        model.addAttribute("connectedOptionsList", contractAvailableOptions);
        model.addAttribute("availableForTariffOptionsList", tmp.getContract().getContractTariff().getOptions());
        model.addAttribute("connectedTariff", tmp.getContract().getContractTariff());
        model.addAttribute("tariffsList", tariffServiceMVC.getAll());
        model.addAttribute("model", tmp);

        return "control/update-contract-info-control-form";
    }

    @RequestMapping("/control/deleteContract")
    public String deleteContract(@RequestParam("contractId") int id) {

        contractServiceMVC.delete(id);

        return "redirect:/control/allContracts";
    }

    @RequestMapping("/client/updateContract")
    public String clientUpdateContract(Principal principal, Model model) {

        Client client = clientServiceMVC.getByEmail(principal.getName());
        Contract contract = client.getContract();

        if (contract == null) {
            throw new ServiceLayerException("Client " + principal.getName() + " try to get his contract. " +
                    "But contract doesn't created yet");
        }

        ContractDto tmp = new ContractDto();
        tmp.setContract(contract);
        tmp.setConnectedOptions(tmp.castConnectedOptionsInStrings(tmp.getContract().getConnectedOptions()));
        model.addAttribute("connectedOptionsList", tmp.getContract().getConnectedOptions());
        model.addAttribute("availableForTariffOptionsList", tmp.getContract().getContractTariff().getOptions());
        model.addAttribute("connectedTariff", tmp.getContract().getContractTariff());
        model.addAttribute("tariffsList", tariffServiceMVC.getAll());
        model.addAttribute("model", tmp);


        return "client/update-contract-info-client-form";

    }
}
