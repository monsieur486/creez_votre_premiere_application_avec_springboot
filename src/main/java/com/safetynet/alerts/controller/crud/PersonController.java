package com.safetynet.alerts.controller.crud;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.jsonfile.JsonFilePersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<Person>> findAllPersons() {
        String message = "Request all persons";
        log.info(message);

        return new ResponseEntity<>(service.getAllPersons(), HttpStatus.OK);
    }

    /**
     * Add person response entity.
     *
     * @param person the person
     * @return the response entity
     */
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
        if(Boolean.TRUE.equals(service.exists(person))){
            String message = "Person already exists";
            log.warn(message);

            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else
        {
            Person person1 = service.save(person);
            String message = person1.getFirstName() + " " + person1.getLastName() + " added succesfully";
            log.info(message);

            return new ResponseEntity<>(person1, HttpStatus.CREATED);
        }
    }

    /**
     * Update person response entity.
     *
     * @param person the person
     * @return the response entity
     */
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> updatePerson(@RequestBody Person person) {
        if (Boolean.TRUE.equals(service.update(person))) {
            String message = person.getFirstName() + " " + person.getLastName() + " updated succesfully";
            log.info(message);

            return new ResponseEntity<>(person, HttpStatus.CREATED);
        } else {
            String message = "Person not found";
            log.warn(message);

            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
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
    public ResponseEntity<String> deletePerson(@RequestParam String firstName, @RequestParam String lastName) {
        if (Boolean.TRUE.equals(service.delete(firstName, lastName))) {
            String message = firstName + " " + lastName + " deleted";
            log.info(message);

            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            String message = "Person not found";
            log.warn(message);

            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
}
