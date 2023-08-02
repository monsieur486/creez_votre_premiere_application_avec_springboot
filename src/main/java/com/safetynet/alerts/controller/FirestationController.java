package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.service.jsonfile.FirestationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/firestation")
@Slf4j
public class FirestationController {
    private final FirestationService service;

    public FirestationController(FirestationService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Firestation> findAllFirestations() {
        log.info("Request all firestations");
        return service.getAllFirestations();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Firestation addFirestation(@RequestBody Firestation firestation) {
        log.info("Save firestationn adress: "
                + firestation.getAddress()
                + " station: "
                + firestation.getStation());
        return service.save(firestation);
    }

    @PutMapping
    public ResponseEntity<String> updateProduct(@RequestBody Firestation firestation) {
        if (Boolean.TRUE.equals(service.update(firestation))) {
            String message = firestation.getAddress() + " with station: " + firestation.getStation() + " updated succesfully";
            log.info(message);
            return ResponseEntity.status(HttpStatus.CREATED).body(message);
        } else {
            String message = "Mapping adress/station not found";
            log.warn(message);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    @DeleteMapping("")
    public ResponseEntity<String> deleteFireStation(@RequestBody Firestation firestation) {
        if (Boolean.TRUE.equals(service.delete(firestation))) {
            String message = "adress: "
                    + firestation.getAddress()
                    + " with station "
                    + firestation.getStation()
                    + " deleted";
            log.info(message);
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } else {
            String message = "Mapping adress/station not found";
            log.warn(message);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }
}
