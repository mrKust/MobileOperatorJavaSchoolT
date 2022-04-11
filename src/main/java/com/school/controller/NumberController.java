package com.school.controller;

import com.school.database.entity.Number;
import com.school.dto.NumberDto;
import com.school.service.contracts.NumberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NumberController {

    private final NumberService numberServiceMVC;

    NumberController(NumberService numberServiceMVC) {
        this.numberServiceMVC = numberServiceMVC;
    }

    /**
     * This method prepare date for add new phone number
     * @param model model
     * @return view with add new phone number form
     */
    @RequestMapping("/control/addNewPhoneNumber")
    public String addNewPhoneNumber(Model model) {

        Number number = new Number();
        number.setAvailableToConnectStatus(true);
        model.addAttribute("allNumbers", numberServiceMVC.getAll());
        model.addAttribute("phoneNumber", number);

        return "control/add-phonenumber-control-form";
    }

    /**
     * This method save new phone number
     * @param phoneNumber phone number which needed to be saved
     * @return view where all client stored, to add new client with new phone number
     */
    @RequestMapping("/control/savePhoneNumber")
    public String savePhoneNumber(@ModelAttribute("phoneNumber") Number phoneNumber) {

        NumberDto numberDto = new NumberDto();
        numberDto.setPhoneNumber(phoneNumber.getPhoneNumber());

        numberServiceMVC.save(phoneNumber);

        return "redirect:/control/allClients";
    }
}
