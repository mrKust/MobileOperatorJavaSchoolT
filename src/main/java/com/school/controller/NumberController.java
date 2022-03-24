package com.school.controller;

import com.school.customException.BusinessLogicException;
import com.school.database.entity.Client;
import com.school.database.entity.Number;
import com.school.dto.NumberDto;
import com.school.service.ServiceMVC;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NumberController {

    private final ServiceMVC<Number> numberServiceMVC;

    NumberController(ServiceMVC<com.school.database.entity.Number> numberServiceMVC) {
        this.numberServiceMVC = numberServiceMVC;
    }

    @RequestMapping("/control/addNewPhoneNumber")
    public String addNewPhoneNumber(Model model, @ModelAttribute("errorMessage") String errorMessage) {

        Number number = new Number();
        number.setAvailableToConnectStatus(true);
        model.addAttribute("allNumbers", numberServiceMVC.getAll());
        model.addAttribute("phoneNumber", number);

        return "control/add-phonenumber-control-form";
    }

    @RequestMapping("/control/savePhoneNumber")
    public String savePhoneNumber(@ModelAttribute("phoneNumber") Number phoneNumber) {

        NumberDto numberDto = new NumberDto();
        numberDto.setPhoneNumber(phoneNumber.getPhoneNumber());

        if (!numberDto.checkNumberToUnique(numberServiceMVC.getAll())) {
            throw new BusinessLogicException("User try to add phone number" +
                    " which we already in base", "redirect:/control/addNewPhoneNumber",
                    "This phone number already in base");
        }

        numberServiceMVC.save(phoneNumber);

        return "redirect:/control/allClients";
    }
}