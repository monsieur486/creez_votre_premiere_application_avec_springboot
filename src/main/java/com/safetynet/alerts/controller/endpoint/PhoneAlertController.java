package com.safetynet.alerts.controller.endpoint;

import com.safetynet.alerts.domain.PhoneAlertEndPointService;
import com.safetynet.alerts.utils.ResponseHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Phone alert controller.
 */
@RestController
@RequestMapping("/phoneAlert")
@Slf4j
public class PhoneAlertController {

    private final PhoneAlertEndPointService phoneAlertEndPointService;

    /**
     * Instantiates a new Phone alert controller.
     *
     * @param phoneAlertEndPointService the phone alert end point service
     */
    public PhoneAlertController(PhoneAlertEndPointService phoneAlertEndPointService) {
        this.phoneAlertEndPointService = phoneAlertEndPointService;
    }

    /**
     * Gets end point.
     *
     * @param firestation the firestation
     * @return the end point
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getEndPoint(@RequestParam Integer firestation) {

        return ResponseHandler.generateResponse("Phone numbers for firestation " + firestation,
                HttpStatus.OK,
                "phoneNumbers",
                phoneAlertEndPointService.getPhoneAlertByStationNumber(firestation));
    }
}
