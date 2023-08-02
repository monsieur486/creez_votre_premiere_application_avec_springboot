package com.safetynet.alerts.controller;

import com.safetynet.alerts.controller.crud.PersonController;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.jsonfile.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PersonControllerTest {

    PersonService service = mock(PersonService.class);

    PersonController classToTest = new PersonController(service);

    @Test
    void findAllPersons() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(
                "John",
                "Boyd",
                "1 place de Paris",
                "Strasbourg",
                "67100",
                "123-456-789",
                "boyd@gmail.com"));

        when(service.getAllPersons()).thenReturn(personList);
        ResponseEntity<Object> response = classToTest.findAllPersons();
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void addPerson() {
        Person person = new Person(
                "John",
                "Boyd",
                "1 place de Paris",
                "Strasbourg",
                "67100",
                "123-456-789",
                "boyd@gmail.com");

        when(service.save(person)).thenReturn(person);
        ResponseEntity<Object> response = classToTest.addPerson(person);
        assertEquals(201, response.getStatusCodeValue());
    }

    @Test
    void updatePerson() {
        Person person = new Person(
                "John",
                "Boyd",
                "1 place de Paris",
                "Strasbourg",
                "67100",
                "123-456-789",
                "boyd@gmail.com");

        when(service.update(person)).thenReturn(true);
        ResponseEntity<Object> response = classToTest.updatePerson(person);
        assertEquals(201, response.getStatusCodeValue());
    }

    @Test
    void updatePersonNotFound() {
        Person person = new Person(
                "John",
                "Boyd",
                "1 place de Paris",
                "Strasbourg",
                "67100",
                "123-456-789",
                "boyd@gmail.com");

        when(service.update(person)).thenReturn(false);
        ResponseEntity<Object> response = classToTest.updatePerson(person);
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void deletePerson() {
        when(service.delete("xxx", "xxx")).thenReturn(true);
        ResponseEntity<Object> response = classToTest.deletePerson("xxx", "xxx");
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void deletePersonNotFound() {
        when(service.delete("xxx", "xxx")).thenReturn(false);
        ResponseEntity<Object> response = classToTest.deletePerson("xxx", "xxx");
        assertEquals(404, response.getStatusCodeValue());
    }
}