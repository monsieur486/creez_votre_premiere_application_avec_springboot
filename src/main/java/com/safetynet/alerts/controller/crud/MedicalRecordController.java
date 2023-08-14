package com.safetynet.alerts.controller.crud;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.jsonfile.JsonFileMedicalRecordService;
import com.safetynet.alerts.utils.ResponseHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Medical record controller.
 */
@RestController
@RequestMapping("/medicalRecord")
@Slf4j
public class MedicalRecordController {
    private final JsonFileMedicalRecordService service;

    /**
     * Instantiates a new Medical record controller.
     *
     * @param service the service
     */
    public MedicalRecordController(JsonFileMedicalRecordService service) {
        this.service = service;
    }

    /**
     * Find all medicalrecords response entity.
     *
     * @return the response entity
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MedicalRecord>> findAllMedicalrecords() {
        String message = "Request all medicalrecords";
        log.info(message);

        return new ResponseEntity<>(service.getAllMedicalrecords(), HttpStatus.OK);
    }

    /**
     * Add medicalrecord response entity.
     *
     * @param medicalrecord the medicalrecord
     * @return the response entity
     */
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MedicalRecord> addMedicalrecord(@RequestBody MedicalRecord medicalrecord) {
        if(Boolean.TRUE.equals(service.exists(medicalrecord))){
            String message = "Medical record already exists";
            log.warn(message);

            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else {
            MedicalRecord medicalRecord1 = service.save(medicalrecord);
            String message = "Add medicalrecord firstname: "
                    + medicalrecord.getFirstName()
                    + " lastname: "
                    + medicalrecord.getLastName();
            log.info(message);

            return new ResponseEntity<>(medicalRecord1, HttpStatus.CREATED);
        }
    }

    /**
     * Update response entity.
     *
     * @param medicalrecord the medicalrecord
     * @return the response entity
     */
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MedicalRecord> update(@RequestBody MedicalRecord medicalrecord) {
        if (Boolean.TRUE.equals(service.update(medicalrecord))) {
            String message = "Update medicalrecord firstname: "
                    + medicalrecord.getFirstName()
                    + " lastname: "
                    + medicalrecord.getLastName();
            log.info(message);

            return new ResponseEntity<>(medicalrecord, HttpStatus.CREATED);
        } else {
            String message = "Medical record not found";
            log.warn(message);

            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete response entity.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @return the response entity
     */
    @DeleteMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> delete(@RequestParam String firstName, @RequestParam String lastName) {
        if(Boolean.TRUE.equals(service.delete(firstName, lastName))){
            String message = "medical record from " + firstName + " " + lastName + " deleted";
            log.info(message);

            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            String message = "medical record not found";
            log.warn(message);

            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
}
