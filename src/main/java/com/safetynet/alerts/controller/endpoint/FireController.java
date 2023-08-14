package com.safetynet.alerts.controller.endpoint;

import com.safetynet.alerts.domain.FireEndPointService;
import com.safetynet.alerts.dto.FireDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Fire controller.
 */
@RestController
@RequestMapping("/fire")
@Slf4j
public class FireController {

    private final FireEndPointService fireEndPointService;

    /**
     * Instantiates a new Fire controller.
     *
     * @param fireEndPointService the fire end point service
     */
    public FireController(FireEndPointService fireEndPointService) {
        this.fireEndPointService = fireEndPointService;
    }

    /**
     * Gets end point.
     *
     * @param address the address
     * @return the end point
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FireDto> getEndPoint(@RequestParam String address) {

        return new ResponseEntity<>(fireEndPointService.getPersonListByAddress(address), HttpStatus.OK);
    }
}
