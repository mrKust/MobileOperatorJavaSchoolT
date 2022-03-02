package com.school.controller;

import com.school.database.entity.Options;
import com.school.service.ServiceMVC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class OptionsController {

    @Autowired
    private ServiceMVC<Options> optionsServiceMVC;

    @RequestMapping("/allOptions")
    public String showAllOptions(Model model) {

        List<Options> allOptions = optionsServiceMVC.getAll();
        model.addAttribute("allOptions", allOptions);

        return "all-options";
    }

    @RequestMapping("/addNewOption")
    public String addNewOption(Model model) {

        Options options = new Options();
        model.addAttribute("options", options);
        return "option-info-form";
    }

    @RequestMapping("/saveOption")
    public String saveOption(@ModelAttribute("options") Options option) {

        optionsServiceMVC.save(option);

        return "redirect:/allOptions";
    }

    @RequestMapping("/updateOption")
    public String updateOption(@RequestParam("optionId") int id, Model model) {

        Options options = optionsServiceMVC.get(id);
        model.addAttribute("options", options);

        return "option-info-form";
    }

    @RequestMapping("/deleteOption")
    public String deleteOption(@RequestParam("optionId") int id ) {

        optionsServiceMVC.delete(id);

        return "redirect:/allOptions";
    }
}
