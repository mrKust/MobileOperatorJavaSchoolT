package com.school.controller;

import com.school.database.entity.OptionType;
import com.school.database.entity.Options;
import com.school.service.ServiceMVC;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class OptionTypeController {

    private final ServiceMVC<OptionType> optionTypeServiceMVC;
    private final ServiceMVC<Options> optionsServiceMVC;

    OptionTypeController(ServiceMVC<OptionType> optionTypeServiceMVC, ServiceMVC<Options> optionsServiceMVC) {
        this.optionTypeServiceMVC = optionTypeServiceMVC;
        this.optionsServiceMVC = optionsServiceMVC;
    }

    @RequestMapping("/control/allOptionCategories")
    public String showAllOptionsCategory(Model model) {

        List<OptionType> allOptionsType = optionTypeServiceMVC.getAll();
        model.addAttribute("allOptionsType", allOptionsType);

        return "control/all-optionsTypes";
    }

    @RequestMapping("/control/addNewOptionCategory")
    public String controlAddNewOptionCategory(Model model) {

        OptionType optionsType = new OptionType();
        model.addAttribute("optionsType", optionsType);

        return "control/add-optiontype-info-control-form";
    }

    @RequestMapping("/control/saveOptionType")
    public String saveOptionType(@ModelAttribute("optionsType") OptionType optionsType) {

        optionTypeServiceMVC.save(optionsType);

        return "redirect:/common/allOptions";
    }

    @RequestMapping("/control/deleteOptionType")
    public String deleteOptionType(@RequestParam("optionsTypeId") int optionsTypeId) {

        OptionType optionsType = optionTypeServiceMVC.get(optionsTypeId);
        List<Options> allOptions = optionsServiceMVC.getAll();
        for(Options tmp : allOptions) {
            if (tmp.getOptionType().getOptionType().equals(optionsType.getOptionType()))
                optionsServiceMVC.delete(tmp.getId());
        }
        optionTypeServiceMVC.delete(optionsTypeId);

        return "redirect:/control/allOptionCategories";
    }

}
