package com.safetynet.alerts.controller.endpoint;

import com.safetynet.alerts.domain.FireEndPointService;
import com.safetynet.alerts.utils.ResponseHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fire")
@Slf4j
public class FireController {

    private final FireEndPointService fireEndPointService;

    public FireController(FireEndPointService fireEndPointService) {
        this.fireEndPointService = fireEndPointService;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getEndPoint(@RequestParam String address) {

        return ResponseHandler.generateResponse(
                "Get person list by address",
                HttpStatus.OK,
                "fire",
                fireEndPointService.getPersonListByAddress(address)
        );
    }
}
