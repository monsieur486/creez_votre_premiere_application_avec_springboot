package com.safetynet.alerts.controller.endpoint;

import com.safetynet.alerts.domain.PersonInfoEndPointService;
import com.safetynet.alerts.utils.ResponseHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/personInfo")
@Slf4j
public class PersonInfoController {

    private final PersonInfoEndPointService personInfoEndPointService;

    public PersonInfoController(PersonInfoEndPointService personInfoEndPointService) {
        this.personInfoEndPointService = personInfoEndPointService;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getEndPoint(@RequestParam String firstName, @RequestParam String lastName) {
        return ResponseHandler.generateResponse(
                "personInfo",
                HttpStatus.OK,
                "personinfo",
                personInfoEndPointService.getPersonInfo(firstName, lastName));
    }
}
