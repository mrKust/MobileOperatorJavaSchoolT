package com.school.controller;

import com.school.database.entity.OptionType;
import com.school.service.contracts.OptionTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class OptionTypeController {

    private final OptionTypeService optionTypeServiceMVC;

    OptionTypeController(OptionTypeService optionTypeServiceMVC) {
        this.optionTypeServiceMVC = optionTypeServiceMVC;
    }

    @RequestMapping("/control/allOptionCategories")
    public String showAllOptionsCategory(Model model) {

        model.addAttribute("allOptionsType", optionTypeServiceMVC.getAll());

        return "control/all-optionsTypes";
    }

    @RequestMapping("/control/addNewOptionCategory")
    public String controlAddNewOptionCategory(Model model) {

        model.addAttribute("optionsType", new OptionType());

        return "control/add-optiontype-info-control-form";
    }

    @RequestMapping("/control/saveOptionType")
    public String saveOptionType(@ModelAttribute("optionsType") OptionType optionsType) {

        optionTypeServiceMVC.save(optionsType);

        return "redirect:/common/allOptions";
    }

    @RequestMapping("/control/deleteOptionType")
    public String deleteOptionType(@RequestParam("optionsTypeId") int optionsTypeId) {

        optionTypeServiceMVC.delete(optionsTypeId);


        return "redirect:/control/allOptionCategories";
    }

}
