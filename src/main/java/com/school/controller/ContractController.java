package com.school.controller;

import com.school.database.entity.Client;
import com.school.database.entity.Contract;
import com.school.database.entity.Number;
import com.school.database.entity.Options;
import com.school.database.entity.Tariff;
import com.school.dto.ContractDto;
import com.school.service.ServiceMVC;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
public class ContractController {

    private final ServiceMVC<Contract> contractServiceMVC;

    private final ServiceMVC<Tariff> tariffServiceMVC;

    private final ServiceMVC<Client> clientServiceMVC;

    private static final Logger LOG = Logger.getLogger(ContractController.class);

    ContractController(ServiceMVC<Contract> contractServiceMVC, ServiceMVC<Client> clientServiceMVC, ServiceMVC<Tariff> tariffServiceMVC) {
        this.contractServiceMVC = contractServiceMVC;
        this.tariffServiceMVC = tariffServiceMVC;
        this.clientServiceMVC = clientServiceMVC;

    }

    @RequestMapping("/control/allContracts")
    public String showAllContracts(Model model) {

        List<Contract> allContracts = contractServiceMVC.getAll();
        model.addAttribute("allContracts", allContracts);

        return "control/all-contracts";
    }

    @RequestMapping("/control/addNewContract")
    public String addNewContract(Model model) {

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
    public String saveContract(@ModelAttribute("model") ContractDto contractDto, HttpServletRequest request, Model model, Principal principal) {


        Contract contract = contractDto.getContract();
        if (contractDto.getStringsTariff() != null) {
            contract.setContractTariff(contractDto.wrapStringsToTariff(tariffServiceMVC.getAll()));
        } else {
            contract.setContractTariff(tariffServiceMVC.get(contract.getContractTariff().getId()));
        }

        if (contractDto.getStringsClients() != null) {
            contract.setContractClient(contractDto.wrapStringsToClient(clientServiceMVC.getAll()));
        }

        List<Options> chosenOptions = contractDto.wrapStringsToConnectedOptions(contract.getContractTariff().getOptions());
        if (contractDto.checkChosenOptionForCorrect(chosenOptions)) {
            if (contractDto.getStringsOptions() != null) {
                contract.setConnectedOptions(chosenOptions);
            } else if (contractDto.getOperationType().equals("update")) {
                contract.setConnectedOptions(contractDto.wrapStringsToConnectedOptions(chosenOptions));
            }
        } else {
            LOG.debug("User " + principal.getName() + " chose forbidden combination of options");
            if (request.isUserInRole("ROLE_control")) {
                model.addAttribute("contractId", contractDto.getContract().getId());
                return "redirect:/control/updateContract";
            }
            else {
                model.addAttribute(principal);
                return "redirect:/client/updateContract";
            }
        }

        contractServiceMVC.save(contract);
        if (request.isUserInRole("ROLE_control"))
            return "redirect:/control/allContracts";

        return "redirect:/";
    }

    @RequestMapping("/control/updateContract")
    public String controlUpdateContract(@RequestParam("contractId") int id, Model model) {

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
    public String deleteContract(@RequestParam("contractId") int id ) {

        contractServiceMVC.delete(id);

        return "redirect:/control/allContracts";
    }

    @RequestMapping("/client/updateContract")
    public String clientUpdateContract(Principal principal, Model model) {

        Contract contract = new Contract();

        try {
            contract = contractServiceMVC.get(
                    clientServiceMVC.getByName(principal.getName()).getContract().getId()
            );

        } catch (NullPointerException exception) {
            LOG.error("User " + principal.getName() + " try to get his contract, but contract " +
                    "wasn't created");
            return "redirect:/";
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
