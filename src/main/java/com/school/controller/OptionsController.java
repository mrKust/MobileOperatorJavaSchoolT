package com.school.controller;

import com.school.database.entity.Options;
import com.school.service.ServiceMVC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class OptionsController {

    private final ServiceMVC<Options> optionsServiceMVC;

    OptionsController(ServiceMVC<Options> optionsServiceMVC) {
        this.optionsServiceMVC = optionsServiceMVC;
    }

    @RequestMapping("/common/allOptions")
    public String showAllOptions(Model model) {

        List<Options> allOptions = optionsServiceMVC.getAll();
        model.addAttribute("allOptions", allOptions);

        return "common/all-options";
    }

    @RequestMapping("/control/addNewOption")
    public String addNewOption(Model model) {

        Options options = new Options();
        model.addAttribute("options", options);
        return "control/add-option-info-control-form";
    }

    @RequestMapping("/common/saveOption")
    public String saveOption(@ModelAttribute("options") Options option) {

        optionsServiceMVC.save(option);

        return "redirect:/common/allOptions";
    }

    @RequestMapping("/control/updateOption")
    public String controlUpdateOption(@RequestParam("optionId") int id, Model model) {

        Options options = optionsServiceMVC.get(id);
        model.addAttribute("options", options);

        return "control/update-option-info-control-form";
    }

    @RequestMapping("/control/deleteOption")
    public String deleteOption(@RequestParam("optionId") int id ) {

        optionsServiceMVC.delete(id);

        return "redirect:/common/allOptions";
    }

    @RequestMapping("/client/updateOption")
    public String clientUpdateOption(@RequestParam("optionId") int id, Model model) {

        Options options = optionsServiceMVC.get(id);
        model.addAttribute("options", options);

        return "client/option-info-client-form";
    }
}
