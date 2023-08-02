package com.safetynet.alerts.controller.endpoint;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/phoneAlert")
@Slf4j
public class PhoneAlertController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String getEndPoint(@RequestParam Integer firestation) {
        return firestation.toString();
    }
}
