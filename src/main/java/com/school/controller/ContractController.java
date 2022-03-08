package com.school.controller;

import com.school.database.entity.Client;
import com.school.database.entity.Contract;
import com.school.database.entity.Options;
import com.school.database.entity.Tariff;
import com.school.dto.ContractDto;
import com.school.service.ServiceMVC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
public class ContractController {

    @Autowired
    private ServiceMVC<Contract> contractServiceMVC;

    @Autowired
    private ServiceMVC<Tariff> tariffServiceMVC;

    @Autowired
    private ServiceMVC<Client> clientServiceMVC;

    @Autowired
    private ServiceMVC<Options> optionsServiceMVC;

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
        tmp.setObject(new Contract());
        model.addAttribute("tariffsList", tariffList);
        model.addAttribute("clientsList", clientList);
        model.addAttribute("model", tmp);
        return "control/add-contract-info-control-form";
    }

    @RequestMapping("/common/saveContract")
    public String saveContract(@ModelAttribute("model") ContractDto contractDto, HttpServletRequest request) {

        Contract contract = contractDto.getObject();
        if (contractDto.getStringsTariff() != null) {
            contract.setContractTariff(contractDto.wrapStringsToTariff(tariffServiceMVC.getAll()));
        }

        if (contractDto.getStringsClients() != null) {
            contract.setContractClient(contractDto.wrapStringsToClient(clientServiceMVC.getAll()));
        }

        contractServiceMVC.save(contract);
        if (request.isUserInRole("ROLE_control"))
            return "redirect:/control/allContracts";

        return "redirect:/";
    }

    @RequestMapping("/control/updateContract")
    @Transactional
    public String controlUpdateContract(@RequestParam("contractId") int id, Model model) {

        List<Tariff> tariffList = tariffServiceMVC.getAll();
        ContractDto tmp = new ContractDto();
        tmp.setObject(contractServiceMVC.get(id));
        Tariff connectedTariff = tmp.getObject().getContractTariff();
        List<Options> optionsList = optionsServiceMVC.getAll();
        List<Options> defaultTariffOptions = connectedTariff.getOptions();
        /*try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        model.addAttribute("connectedOptionsList", defaultTariffOptions);
        model.addAttribute("optionsList", optionsList);
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

        Contract contract = contractServiceMVC.getByName(principal.getName());
        model.addAttribute("contracts", contract);


        return "client/update-contract-info-client-form";

    }
}
