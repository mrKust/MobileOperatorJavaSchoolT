package com.school.controller;

import com.school.database.entity.Tariff;
import com.school.service.contracts.TariffService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This controller sends data by api
 */
@RestController
@RequestMapping(value = "/api")
public class AdvRestController {

    private final TariffService tariffServiceMVC;

    AdvRestController(TariffService tariffServiceMVC) {
        this.tariffServiceMVC = tariffServiceMVC;
    }

    /**
     * This method returns data about tariffs and available for it options
     * @return
     */
    @RequestMapping(value = "/tariffsInfo", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @CrossOrigin
    public List<Tariff> getTariffJsonData() {

        return tariffServiceMVC.getAllAvailable();
    }
}
