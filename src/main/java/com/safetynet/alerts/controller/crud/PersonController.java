package com.safetynet.alerts.controller.crud;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.jsonfile.JsonFilePersonService;
import com.safetynet.alerts.utils.ResponseHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
@Slf4j
public class PersonController {

    private final JsonFilePersonService service;

    public PersonController(JsonFilePersonService service) {
        this.service = service;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findAllPersons() {
        String message = "Request all persons";
        log.info(message);
        return ResponseHandler.generateResponse(
                message,
                HttpStatus.OK,
                "persons",
                service.getAllPersons()
        );
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addPerson(@RequestBody Person person) {
        String message = "Add person firstname: "
                + person.getFirstName()
                + " lastname: "
                + person.getLastName();
        log.info(message);
        return ResponseHandler.generateResponse(
                message,
                HttpStatus.CREATED,
                "person",
                service.save(person)
        );
    }

    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updatePerson(@RequestBody Person person) {
        if (Boolean.TRUE.equals(service.update(person))) {
            String message = person.getFirstName() + " " + person.getLastName() + " updated succesfully";
            log.info(message);
            return ResponseHandler.generateResponse(
                    message,
                    HttpStatus.CREATED,
                    "person",
                    person
            );
        } else {
            String message = "person not found";
            log.warn(message);
            return ResponseHandler.generateResponse(
                    message,
                    HttpStatus.NOT_FOUND,
                    "person",
                    person
            );
        }
    }

    @DeleteMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deletePerson(@RequestParam String firstName, @RequestParam String lastName) {
        if (Boolean.TRUE.equals(service.delete(firstName, lastName))) {
            String message = firstName + " " + lastName + " deleted";
            log.info(message);
            return ResponseHandler.generateResponse(
                    message,
                    HttpStatus.OK,
                    "person",
                    null
            );
        } else {
            String message = "person not found";
            log.warn(message);
            return ResponseHandler.generateResponse(
                    message,
                    HttpStatus.NOT_FOUND,
                    "person",
                    null
            );
        }
    }
}
