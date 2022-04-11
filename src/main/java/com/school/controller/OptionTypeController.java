package com.school.controller;

import com.school.database.entity.OptionType;
import com.school.service.contracts.OptionTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * This controller show views which are needs to work with option types
 */
@Controller
public class OptionTypeController {

    private final OptionTypeService optionTypeServiceMVC;

    OptionTypeController(OptionTypeService optionTypeServiceMVC) {
        this.optionTypeServiceMVC = optionTypeServiceMVC;
    }

    /**
     * This method show all options category
     * @param model model
     * @return view with all option types
     */
    @RequestMapping("/control/allOptionCategories")
    public String showAllOptionsCategory(Model model) {

        model.addAttribute("allOptionsType", optionTypeServiceMVC.getAll());

        return "control/all-optionsTypes";
    }

    /**
     * This method prepare data to add new option type form
     * @param model model
     * @return view with form to add new option type
     */
    @RequestMapping("/control/addNewOptionCategory")
    public String controlAddNewOptionCategory(Model model) {

        model.addAttribute("optionsType", new OptionType());

        return "control/add-optiontype-info-control-form";
    }

    /**
     * This method save new option type
     * @param optionsType option type which need to be saved
     * @return view with all options where can add new option with new option type
     */
    @RequestMapping("/control/saveOptionType")
    public String saveOptionType(@ModelAttribute("optionsType") OptionType optionsType) {

        optionTypeServiceMVC.save(optionsType);

        return "redirect:/common/allOptions";
    }

    /**
     * This method delete option type
     * @param optionsTypeId id of option type
     * @return view with option types which still exist in system
     */
    @RequestMapping("/control/deleteOptionType")
    public String deleteOptionType(@RequestParam("optionsTypeId") int optionsTypeId) {

        optionTypeServiceMVC.delete(optionsTypeId);


        return "redirect:/control/allOptionCategories";
    }

}
