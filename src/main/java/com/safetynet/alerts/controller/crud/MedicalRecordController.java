package com.safetynet.alerts.controller.crud;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.jsonfile.MedicalRecordService;
import com.safetynet.alerts.utils.ResponseHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicalRecord")
@Slf4j
public class MedicalRecordController {
    private final MedicalRecordService service;

    public MedicalRecordController(MedicalRecordService service) {
        this.service = service;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findAllMedicalrecords() {
        String message = "Request all medicalrecords";
        log.info(message);

        return ResponseHandler.generateResponse(
                message,
                HttpStatus.OK,
                "medicalrecords",
                service.getAllMedicalrecords()
        );
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addMedicalrecord(@RequestBody MedicalRecord medicalrecord) {
        String message = "Add medicalrecord firstname: "
                + medicalrecord.getFirstName()
                + " lastname: "
                + medicalrecord.getLastName();
        log.info(message);
        return ResponseHandler.generateResponse(
                message,
                HttpStatus.CREATED,
                "medicalrecord",
                service.save(medicalrecord)
        );
    }

    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> update(@RequestBody MedicalRecord medicalrecord) {
        if (Boolean.TRUE.equals(service.update(medicalrecord))) {
            String message = "medical record from " + medicalrecord.getFirstName() + " " + medicalrecord.getLastName() + " updated succesfully";
            log.info(message);
            return ResponseHandler.generateResponse(
                    message,
                    HttpStatus.CREATED,
                    "medicalrecord",
                    medicalrecord
            );
        } else {
            String message = "medical record not found";
            log.warn(message);
            return ResponseHandler.generateResponse(
                    message,
                    HttpStatus.NOT_FOUND,
                    "medicalrecord",
                    medicalrecord
            );
        }
    }

    @DeleteMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> delete(@RequestParam String firstName, @RequestParam String lastName) {
        if (Boolean.TRUE.equals(service.delete(firstName, lastName))) {
            String message = "medical record from " + firstName + " " + lastName + " deleted";
            log.info(message);
            return ResponseHandler.generateResponse(
                    message,
                    HttpStatus.OK,
                    "medicalrecord",
                    null
            );
        } else {
            String message = "medical record not found";
            log.warn(message);
            return ResponseHandler.generateResponse(
                    message,
                    HttpStatus.NOT_FOUND,
                    "medicalrecord",
                    null
            );
        }
    }
}
