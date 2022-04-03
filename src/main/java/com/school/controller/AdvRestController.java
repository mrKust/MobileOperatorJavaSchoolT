package com.school.controller;

import com.school.service.contracts.TariffService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class AdvRestController {

    private final TariffService tariffServiceMVC;

    AdvRestController(TariffService tariffServiceMVC) {
        this.tariffServiceMVC = tariffServiceMVC;
    }

    @RequestMapping(value = "/tariffsInfo", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<String> getTariffJsonData() {

        return ResponseEntity.ok(tariffServiceMVC.getAllAvailableTariffsDataInJson());
    }
}
