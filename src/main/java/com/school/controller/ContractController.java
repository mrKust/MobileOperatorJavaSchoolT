package com.school.controller;

import com.school.database.entity.Contract;
import com.school.service.ServiceMVC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
public class ContractController {

    @Autowired
    private ServiceMVC<Contract> contractServiceMVC;

    @RequestMapping("/control/allContracts")
    public String showAllContracts(Model model) {

        List<Contract> allContracts = contractServiceMVC.getAll();
        model.addAttribute("allContracts", allContracts);

        return "control/all-contracts";
    }

    @RequestMapping("/control/addNewContract")
    public String addNewContract(Model model) {

        Contract contract = new Contract();
        model.addAttribute("contracts", contract);
        return "control/add-contract-info-control-form";
    }

    @RequestMapping("/common/saveContract")
    public String saveContract(@ModelAttribute("contracts") Contract contract, HttpServletRequest request) {

        contractServiceMVC.save(contract);
        if (request.isUserInRole("ROLE_control"))
            return "redirect:/control/allContracts";

        return "redirect:/";
    }

    @RequestMapping("/control/updateContract")
    public String controlUpdateContract(@RequestParam("contractId") int id, Model model) {

        Contract contract = contractServiceMVC.get(id);
        model.addAttribute("contracts", contract);

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
