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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

/**
 * This controller show all views which are connected with options
 */
@Controller
public class OptionsController {

    private final OptionsService optionsServiceMVC;
    private final OptionTypeService optionTypeServiceMVC;

    OptionsController(OptionsService optionsServiceMVC, OptionTypeService optionTypeServiceMVC) {
        this.optionsServiceMVC = optionsServiceMVC;
        this.optionTypeServiceMVC = optionTypeServiceMVC;
    }

    /**
     * This method show page of options
     * @param optionsDto options data transfer object with necessary data
     * @param pageNumber number of page of options
     * @param model model
     * @return view with page of options
     */
    @RequestMapping(value = "/common/allOptions")
    public String showAllOptions(@ModelAttribute("model") OptionsDto optionsDto,
                                 @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                 Model model) {

        model.addAttribute("model", optionsDto);
        model.addAttribute("allOptions", optionsServiceMVC.getPageOfOptions(optionsDto, pageNumber));
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("numberOfPages", optionsServiceMVC.getNumberOfPages(optionsDto.getPageSize()));

        return "common/all-options";
    }

    /**
     * This method show all available options for unregistered users
     * @param model model
     * @return view with all options which could be connected
     */
    @RequestMapping("/anonymous/allAvailableOptions")
    public String showAllAvailableOptions(Model model) {

        model.addAttribute("allOptions", optionsServiceMVC.getAllAvailable());

        return "common/all-options";
    }

    /**
     * This method prepare data which is needed to add new option
     * @param model model
     * @return view with form to add new option
     */
    @RequestMapping("/control/addNewOption")
    public String addNewOption(Model model) {

        OptionsDto optionsDto = new OptionsDto();
        optionsDto.setOptions(new Options());
        model.addAttribute("optionsCategory", optionTypeServiceMVC.getAll());
        model.addAttribute("model", optionsDto);

        return "control/add-option-info-control-form";
    }

    /**
     * This method saves new option
     * @param optionsDto options data transfer object with necessary data
     * @param request http request
     * @param redir container for redirected attributes
     * @return view where save method was called with message about save process
     */
    @RequestMapping(value = "/control/saveOption")
    public RedirectView saveOption(@ModelAttribute("model") OptionsDto optionsDto,
                                   HttpServletRequest request, RedirectAttributes redir) {

        optionsServiceMVC.save(optionsDto);
        RedirectView redirectView = new RedirectView(request.getHeader("Referer"), true);
        redir.addFlashAttribute("successMessage", "Option save successfully");

        return redirectView;
    }

    /**
     * This method updates existed option
     * @param optionsDto options data transfer object with necessary data
     * @param request http request
     * @param redir container for redirected attributes
     * @return view where update method was called with message about save process
     */
    @RequestMapping(value = "/control/patchOption")
    public RedirectView patchOption(@ModelAttribute("model") OptionsDto optionsDto,
                                   HttpServletRequest request, RedirectAttributes redir) {

        optionsServiceMVC.update(optionsDto);
        RedirectView redirectView = new RedirectView(request.getHeader("Referer"), true);
        redir.addFlashAttribute("successMessage", "Option update successfully");

        return redirectView;
    }

    /**
     * This method prepare data to update form when update method called by "control" user
     * @param id id of option
     * @param model model
     * @return view with data form for update option's data
     */
    @RequestMapping(value = "/control/updateOption")
    public String controlUpdateOption(@RequestParam("optionId") int id, Model model) {

        OptionsDto optionsDto = new OptionsDto();
        optionsDto.setOptions(optionsServiceMVC.get(id));
        model.addAttribute("model", optionsDto);

        return "control/update-option-info-control-form";
    }

    /**
     * This method delete option
     * @param id id of option
     * @param request http request
     * @param redir container for redirected attributes
     * @return view where delete method was called with message about delete process
     */
    @RequestMapping(value = "/control/deleteOption")
    public RedirectView deleteOption(@RequestParam("optionId") int id,
                                     HttpServletRequest request, RedirectAttributes redir) {

        optionsServiceMVC.delete(id);
        RedirectView redirectView = new RedirectView(request.getHeader("Referer"), true);
        redir.addFlashAttribute("successMessage", "Option deleted successfully");

        return redirectView;
    }

    /**
     * This method prepare data to update form when update method called by "client" user
     * @param id id of option
     * @param model model
     * @return view with form to update option data
     */
    @RequestMapping("/client/updateOption")
    public String clientUpdateOption(@RequestParam("optionId") int id, Model model) {

        OptionsDto optionsDto = new OptionsDto();
        optionsDto.setOptions(optionsServiceMVC.get(id));
        model.addAttribute("model", optionsDto);

        return "client/option-info-client-form";
    }
}
