package com.school.controller;

import com.school.database.entity.Tariff;
import com.school.service.contracts.OptionsService;
import com.school.dto.TariffDto;
import com.school.service.contracts.TariffService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

/**
 * This controller show all views which are connected with tariffs
 */
@Controller
public class TariffController {

    private final TariffService tariffServiceMVC;
    private final OptionsService optionsServiceMVC;

    TariffController(TariffService tariffServiceMVC, OptionsService optionsServiceMVC) {
        this.tariffServiceMVC = tariffServiceMVC;
        this.optionsServiceMVC = optionsServiceMVC;
    }

    /**
     * This method show page of tariffs
     * @param tariffDto tariff data transfer object with necessary data
     * @param pageNumber number of page of tariffs
     * @param model model
     * @return view with page of tariff
     */
    @RequestMapping("/common/allTariffs")
    public String showAllTariff(@ModelAttribute("model") TariffDto tariffDto,
                                @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                            Model model) {

        model.addAttribute("model", tariffDto);
        model.addAttribute("allTariffs", tariffServiceMVC.getPageOfTariffs(tariffDto, pageNumber));
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("numberOfPages", tariffServiceMVC.getNumberOfPages(tariffDto.getPageSize()));

        return "common/all-tariffs";
    }

    /**
     * This method show all available tariff for unregistered users
     * @param model model
     * @return view with all tariff which could be connected
     */
    @RequestMapping("/anonymous/allAvailableTariffs")
    public String showAllAvailableTariff(Model model) {

        model.addAttribute("allTariffs", tariffServiceMVC.getAllAvailable());

        return "common/all-tariffs";
    }

    /**
     * This method prepare data which is needed to add new tariff
     * @param model model
     * @return view with form to add new tariff
     */
    @RequestMapping("/control/addNewTariff")
    public String addNewTariff(Model model) {

        TariffDto tmp = new TariffDto();
        tmp.setTariff(new Tariff());
        model.addAttribute("optionsList", optionsServiceMVC.getAll());
        model.addAttribute("model", tmp);

        return "control/add-tariff-info-control-form";
    }

    /**
     * This method saves new tariff
     * @param tariffDto tariff data transfer object with necessary data
     * @param request http request
     * @param redir container for redirected attributes
     * @return view where save method was called with message about save process
     */
    @RequestMapping("/control/saveTariff")
    public RedirectView saveTariff(@ModelAttribute("model") TariffDto tariffDto,
                                   HttpServletRequest request, RedirectAttributes redir) {

        tariffServiceMVC.save(tariffDto);
        RedirectView redirectView = new RedirectView(request.getHeader("Referer"), true);
        redir.addFlashAttribute("successMessage", "Tariff added successfully");

        return redirectView;
    }

    /**
     * This method updates existed option
     * @param tariffDto tariff data transfer object with necessary data
     * @param request http request
     * @param redir container for redirected attributes
     * @return view where update method was called with message about save process
     */
    @RequestMapping("/control/patchTariff")
    public RedirectView patchTariff(@ModelAttribute("model") TariffDto tariffDto,
                                    HttpServletRequest request, RedirectAttributes redir) {

        tariffServiceMVC.update(tariffDto);
        RedirectView redirectView = new RedirectView(request.getHeader("Referer"), true);
        redir.addFlashAttribute("successMessage", "Tariff updated successfully");

        return redirectView;
    }

    /**
     * This method prepare data to update form when update method called by "control" user
     * @param id id of tariff
     * @param model model
     * @return view with data form for update tariff's data
     */
    @RequestMapping("/control/updateTariff")
    public String controlUpdateTariff(@RequestParam("tariffId") int id, Model model) {

        TariffDto tmp = new TariffDto();
        tmp.setTariff(tariffServiceMVC.get(id));
        model.addAttribute("connectedOptionsList", tmp.getTariff().getOptions());
        model.addAttribute("optionsList", optionsServiceMVC.getAll());
        model.addAttribute("model", tmp);

        return "control/update-tariff-info-control-form";
    }

    /**
     * This method delete tariff
     * @param id id of tariff
     * @param request http request
     * @param redir container for redirected attributes
     * @return view where delete method was called with message about delete process
     */
    @RequestMapping("/control/deleteTariff")
    public RedirectView deleteTariff(@RequestParam("tariffId") int id,
                                     HttpServletRequest request, RedirectAttributes redir) {

        tariffServiceMVC.delete(id);
        RedirectView redirectView = new RedirectView(request.getHeader("Referer"), true);
        redir.addFlashAttribute("successMessage", "Tariff deleted successfully");

        return redirectView;
    }

    /**
     * This method prepare data to update form when update method called by "client" user
     * @param id id of tariff
     * @param model model
     * @return view with form to update tariff data
     */
    @RequestMapping("/client/updateTariff")
    public String clientUpdateTariff(@RequestParam("tariffId") int id, Model model) {

        TariffDto tariffDto = new TariffDto();
        tariffDto.setTariff(tariffServiceMVC.get(id));
        model.addAttribute("optionsList", tariffDto.getTariff().getOptions());
        model.addAttribute("model", tariffDto);

        return "client/tariff-info-client-form";
    }
}
