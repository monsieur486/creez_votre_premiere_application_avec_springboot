package com.safetynet.alerts.controller.endpoint;

import com.safetynet.alerts.domain.ChildAlertEndPointService;
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
 * The type Child alert controller.
 */
@RestController
@RequestMapping("/childAlert")
@Slf4j
public class ChildAlertController {

    private final ChildAlertEndPointService childAlertEndPointService;

    /**
     * Instantiates a new Child alert controller.
     *
     * @param childAlertEndPointService the child alert end point service
     */
    public ChildAlertController(ChildAlertEndPointService childAlertEndPointService) {
        this.childAlertEndPointService = childAlertEndPointService;
    }

    /**
     * Gets end point.
     *
     * @param address the address
     * @return the end point
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getEndPoint(@RequestParam String address) {

        return ResponseHandler.generateResponse(
                "List of children and adults living at the given address",
                HttpStatus.OK,
                "childAlert",
                childAlertEndPointService.getChildAlertByAddress(address)
        );
    }
}
