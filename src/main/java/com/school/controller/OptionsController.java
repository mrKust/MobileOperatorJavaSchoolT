package com.school.controller;

import com.school.database.entity.OptionType;
import com.school.database.entity.Options;
import com.school.dto.OptionsDto;
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
    private final ServiceMVC<OptionType> optionTypeServiceMVC;

    OptionsController(ServiceMVC<Options> optionsServiceMVC, ServiceMVC<OptionType> optionTypeServiceMVC) {
        this.optionsServiceMVC = optionsServiceMVC;
        this.optionTypeServiceMVC = optionTypeServiceMVC;
    }
    @RequestMapping("/common/allOptions")
    public String showAllOptions(Model model) {

        List<Options> allOptions = optionsServiceMVC.getAll();
        model.addAttribute("allOptions", allOptions);

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

        if (optionsDto.getStringOptionCategory() != null) {

            String[] choosenOptionType = optionsDto.getStringOptionCategory();
            OptionType optionType = optionTypeServiceMVC.get(
                    Integer.parseInt(choosenOptionType[0]));
            optionsDto.getOptions().setOptionType(optionType);
        }

        optionsServiceMVC.save(optionsDto.getOptions());

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
    public String deleteOption(@RequestParam("optionId") int id ) {

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
