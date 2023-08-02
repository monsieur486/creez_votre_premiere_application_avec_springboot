package com.safetynet.alerts.controller.endpoint;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fire")
@Slf4j
public class FireController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String getEndPoint(@RequestParam String address) {
        return address;
    }
}
