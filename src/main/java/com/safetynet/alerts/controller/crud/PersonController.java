package com.safetynet.alerts.controller.crud;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.jsonfile.JsonFilePersonService;
import com.safetynet.alerts.utils.ResponseHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The type Person controller.
 */
@RestController
@RequestMapping("/person")
@Slf4j
public class PersonController {

    private final JsonFilePersonService service;

    /**
     * Instantiates a new Person controller.
     *
     * @param service the service
     */
    public PersonController(JsonFilePersonService service) {
        this.service = service;
    }

    /**
     * Find all persons response entity.
     *
     * @return the response entity
     */
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

    /**
     * Add person response entity.
     *
     * @param person the person
     * @return the response entity
     */
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

    /**
     * Update person response entity.
     *
     * @param person the person
     * @return the response entity
     */
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

    /**
     * Delete person response entity.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @return the response entity
     */
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
