package com.school.controller;

import com.school.service.contracts.TariffService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class AdvRestController {

    private final TariffService tariffServiceMVC;
    private final AmqpTemplate amqpTemplate;

    AdvRestController(TariffService tariffServiceMVC, AmqpTemplate amqpTemplate) {
        this.tariffServiceMVC = tariffServiceMVC;
        this.amqpTemplate = amqpTemplate;
    }

    @RequestMapping(value = "/tariffsInfo", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<String> getTariffJsonData() {

        return ResponseEntity.ok(tariffServiceMVC.getAllAvailableTariffsDataInJson());
    }

    @RequestMapping("/emit")
    @ResponseBody
    public String queue1() {
        amqpTemplate.convertAndSend("queue1","Update info");
        return "update";
    }
}
