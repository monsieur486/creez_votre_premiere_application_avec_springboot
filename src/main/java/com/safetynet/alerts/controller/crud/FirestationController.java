package com.safetynet.alerts.controller.crud;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.domain.FirestationEndPointService;
import com.safetynet.alerts.service.jsonfile.FirestationService;
import com.safetynet.alerts.utils.ResponseHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/firestation")
@Slf4j
public class FirestationController {
    private final FirestationService service;

    private final FirestationEndPointService firestationEndPointService;

    public FirestationController(FirestationService service, FirestationEndPointService firestationEndPointService) {
        this.service = service;
        this.firestationEndPointService = firestationEndPointService;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findAllFirestations(
            @RequestParam(required = false) Integer stationNumber
    ) {

        if (stationNumber != null) {
            String message = "Request people covered by firestation " + stationNumber;
            log.info(message);
            return ResponseHandler.generateResponse(
                    message,
                    HttpStatus.OK,
                    "data",
                    firestationEndPointService.getPeopleCoveredByStationNumber(stationNumber)
            );
        } else {
            String message = "Request all firestations";
            log.info(message);
            return ResponseHandler.generateResponse(
                    message,
                    HttpStatus.OK,
                    "firestations",
                    service.getAllFirestations()
            );
        }
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addFirestation(@RequestBody Firestation firestation) {
        String message = "Add firestation adress: "
                + firestation.getAddress()
                + " station: "
                + firestation.getStation();
        log.info(message);
        return ResponseHandler.generateResponse(
                message,
                HttpStatus.CREATED,
                "firestation",
                service.save(firestation)
        );
    }

    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateProduct(@RequestBody Firestation firestation) {
        if (Boolean.TRUE.equals(service.update(firestation))) {
            String message = firestation.getAddress() + " with station: " + firestation.getStation() + " updated succesfully";
            log.info(message);
            return ResponseHandler.generateResponse(
                    message,
                    HttpStatus.CREATED,
                    "firestation",
                    firestation
            );
        } else {
            String message = "Mapping adress/station not found";
            log.warn(message);
            return ResponseHandler.generateResponse(
                    message,
                    HttpStatus.NOT_FOUND,
                    "firestation",
                    firestation
            );
        }
    }

    @DeleteMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteFireStation(@RequestBody Firestation firestation) {
        if (Boolean.TRUE.equals(service.delete(firestation))) {
            String message = "adress: "
                    + firestation.getAddress()
                    + " with station "
                    + firestation.getStation()
                    + " deleted";
            log.info(message);
            return ResponseHandler.generateResponse(
                    message,
                    HttpStatus.OK,
                    "firestation",
                    null
            );
        } else {
            String message = "Mapping adress/station not found";
            log.warn(message);
            return ResponseHandler.generateResponse(
                    message,
                    HttpStatus.NOT_FOUND,
                    "firestation",
                    null
            );
        }
    }
}
