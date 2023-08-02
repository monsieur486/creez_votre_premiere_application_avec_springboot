package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/person")
@Slf4j
public class PersonController {

    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Person> findAllPersons() {
        log.info("Request all persons");
        return service.getAllPersons();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Person addPerson(@RequestBody Person person) {
        log.info("Save personn firstname: "
                + person.getFirstName()
                + " lasname: "
                + person.getLastName());
        return service.save(person);
    }

    @PutMapping
    public ResponseEntity<String> updateProduct(@RequestBody Person person) {
        if (Boolean.TRUE.equals(service.update(person))) {
            String message = person.getFirstName() + " " + person.getLastName() + " updated succesfully";
            log.info(message);
            return ResponseEntity.status(HttpStatus.CREATED).body(message);
        } else {
            String message = "person not found";
            log.warn(message);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    @DeleteMapping("")
    public ResponseEntity<String> deleteProduct(@RequestParam String firstName, @RequestParam String lastName) {
        if (Boolean.TRUE.equals(service.delete(firstName, lastName))) {
            String message = firstName + " " + lastName + " deleted";
            log.info(message);
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } else {
            String message = "person not found";
            log.warn(message);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }
}
