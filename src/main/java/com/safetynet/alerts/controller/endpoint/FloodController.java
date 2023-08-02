package com.safetynet.alerts.controller.endpoint;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flood/stations")
@Slf4j
public class FloodController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String getEndPoint(@RequestParam String stations) {
        return stations;
    }
}
