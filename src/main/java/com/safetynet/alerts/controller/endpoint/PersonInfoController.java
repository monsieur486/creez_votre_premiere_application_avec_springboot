package com.safetynet.alerts.controller.endpoint;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/personInfo")
@Slf4j
public class PersonInfoController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String getEndPoint(@RequestParam String firstName, @RequestParam String lastName) {
        return firstName + " " + lastName;
    }
}
