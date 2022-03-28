package com.school.controller;

import com.school.database.entity.Options;
import com.school.dto.OptionsDto;
import com.school.service.contracts.OptionTypeService;
import com.school.service.contracts.OptionsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class OptionsController {

    private final OptionsService optionsServiceMVC;
    private final OptionTypeService optionTypeServiceMVC;

    OptionsController(OptionsService optionsServiceMVC, OptionTypeService optionTypeServiceMVC) {
        this.optionsServiceMVC = optionsServiceMVC;
        this.optionTypeServiceMVC = optionTypeServiceMVC;
    }

    @RequestMapping("/common/allOptions")
    public String showAllOptions(Model model) {

        model.addAttribute("allOptions", optionsServiceMVC.getAll());

        return "common/all-options";
    }

    @RequestMapping("/anonymous/allAvailableOptions")
    public String showAllAvailableOptions(Model model) {

        model.addAttribute("allOptions", optionsServiceMVC.getAllAvailable());

        return "common/all-options";
    }

    @RequestMapping("/control/addNewOption")
    public String addNewOption(Model model) {

        OptionsDto optionsDto = new OptionsDto();
        optionsDto.setOptions(new Options());
        model.addAttribute("optionsCategory", optionTypeServiceMVC.getAll());
        model.addAttribute("model", optionsDto);

        return "control/add-option-info-control-form";
    }

    @RequestMapping("/common/saveOption")
    public String saveOption(@ModelAttribute("model") OptionsDto optionsDto) {

        optionsServiceMVC.save(optionsDto);

        return "redirect:/common/allOptions";
    }

    @RequestMapping("/control/updateOption")
    public String controlUpdateOption(@RequestParam("optionId") int id, Model model) {

        OptionsDto optionsDto = new OptionsDto();
        optionsDto.setOptions(optionsServiceMVC.get(id));
        model.addAttribute("model", optionsDto);

        return "control/update-option-info-control-form";
    }

    @RequestMapping("/control/deleteOption")
    public String deleteOption(@RequestParam("optionId") int id) {

        optionsServiceMVC.delete(id);

        return "redirect:/common/allOptions";
    }

    @RequestMapping("/client/updateOption")
    public String clientUpdateOption(@RequestParam("optionId") int id, Model model) {

        OptionsDto optionsDto = new OptionsDto();
        optionsDto.setOptions(optionsServiceMVC.get(id));
        model.addAttribute("model", optionsDto);

        return "client/option-info-client-form";
    }
}
