package com.safetynet.alerts.service.jsonfile;

import com.safetynet.alerts.model.Person;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonFilePersonServiceTest {

    JsonFilePersonService classToTest = new JsonFilePersonService();

    @Test
    void getAllPersons() {
        List<Person> personList = classToTest.getAllPersons();
        assertNotNull(personList);
    }

    @Test
    void save() {
        int initRecord = classToTest.getAllPersons().size();
        Person person = new Person(
                "Laurent",
                "Touret",
                "1 place de Paris",
                "Strasbourg",
                "67100",
                "123-456-789",
                "monsieur486"
        );
        classToTest.save(person);
        assertEquals(initRecord + 1, classToTest.getAllPersons().size());
    }

    @Test
    void update() {
        Person person = new Person(
                "John",
                "Boyd",
                "1 place de Paris",
                "Strasbourg",
                "67100",
                "123-456-789",
                "monsieur486@gmail.com"
        );
        boolean retour = classToTest.update(person);
        assertTrue(retour);
    }

    @Test
    void updateNotFound() {
        Person person = new Person(
                "xxx",
                "xxx",
                "1 place de Paris",
                "Strasbourg",
                "67100",
                "123-456-789",
                "monsieur486@gmail.com"
        );
        boolean retour = classToTest.update(person);
        assertFalse(retour);
    }

    @Test
    void delete() {
        boolean retour = classToTest.delete("John", "Boyd");
        assertTrue(retour);
    }

    @Test
    void deleteNotFound() {
        boolean retour = classToTest.delete("xxx", "xxx");
        assertFalse(retour);
    }

    @Test
    void getPersonsByAddress() {
        List<Person> personList = classToTest.getPersonsByAddress("1 place de Paris");
        assertNotNull(personList);
    }

    @Test
    void getPersonsByCity() {
        List<Person> personList = classToTest.getPersonsByCity("Strasbourg");
        assertNotNull(personList);
    }

    @Test
    void getPeronsByFirstNameAndLastName() {
        List<Person> personList = classToTest.getPeronsByFirstNameAndLastName("John", "Boyd");
        assertNotNull(personList);
    }
}