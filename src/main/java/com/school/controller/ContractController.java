package com.school.controller;

import com.school.database.entity.Client;
import com.school.database.entity.Contract;
import com.school.service.ServiceMVC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class ContractController {

    @Autowired
    private ServiceMVC<Contract> contractServiceMVC;

    @RequestMapping("/allContracts")
    public String showAllContracts(Model model) {

        List<Contract> allContracts = contractServiceMVC.getAll();
        model.addAttribute("allContracts", allContracts);

        return "all-contracts";
    }

    @RequestMapping("/addNewContract")
    public String addNewContract(Model model) {

        Contract contract = new Contract();
        model.addAttribute("contracts", contract);
        return "contract-info-form";
    }

    @RequestMapping("/saveContract")
    public String saveContract(@ModelAttribute("contracts") Contract contract) {

        contractServiceMVC.save(contract);

        return "redirect:/allContracts";
    }

    @RequestMapping("/updateContract")
    public String updateContract(@RequestParam("contractId") int id, Model model) {

        Contract contract = contractServiceMVC.get(id);
        model.addAttribute("contracts", contract);

        return "contract-info-form";
    }

    @RequestMapping("/deleteContract")
    public String deleteContract(@RequestParam("contractId") int id ) {

        contractServiceMVC.delete(id);

        return "redirect:/allContracts";
    }

    @RequestMapping("/ownContractInfo")
    public String showOwnContract(Principal principal, Model model) {

        Contract contract = contractServiceMVC.getByName(principal.getName());
        model.addAttribute("contracts", contract);


        return "contract-info-form";

    }
}
