package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.MedicalRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicalRecord")
@Slf4j
public class MedicalRecordController {
    private final MedicalRecordService service;

    public MedicalRecordController(MedicalRecordService service){
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MedicalRecord> findAllMedicalrecords() {
        log.info("Request all medicalrecords");
        return service.getAllMedicalrecords();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MedicalRecord addMedicalrecord(@RequestBody MedicalRecord medicalrecord) {
        log.info("Save medicalrecord firstname: "
                + medicalrecord.getFirstName()
                + " lasname: "
                + medicalrecord.getLastName());
        return service.save(medicalrecord);
    }

    @PutMapping
    public ResponseEntity<String> updateProduct(@RequestBody MedicalRecord medicalrecord) {
        if (Boolean.TRUE.equals(service.update(medicalrecord))) {
            String message = "medical record from " + medicalrecord.getFirstName() + " " + medicalrecord.getLastName() + " updated succesfully";
            log.info(message);
            return ResponseEntity.status(HttpStatus.CREATED).body(message);
        } else {
            String message = "medical record not found";
            log.warn(message);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    @DeleteMapping("")
    public ResponseEntity<String> deleteProduct(@RequestParam String firstName, @RequestParam String lastName) {
        if (Boolean.TRUE.equals(service.delete(firstName, lastName))) {
            String message = "medical record from " +firstName + " " + lastName + " deleted";
            log.info(message);
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } else {
            String message = "medical record not found";
            log.warn(message);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }
}
