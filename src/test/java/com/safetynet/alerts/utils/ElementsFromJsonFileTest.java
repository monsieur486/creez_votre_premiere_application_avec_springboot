package com.safetynet.alerts.utils;

import com.safetynet.alerts.model.ApplicationElements;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.Medicalrecord;
import com.safetynet.alerts.model.Person;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ElementsFromJsonFileTest {

    ElementsFromJsonFile classToTest = new ElementsFromJsonFile();

    @Test
    void getJsonData() {
        ApplicationElements result = ElementsFromJsonFile.getJsonData();
        assertNotNull(result);
    }

    @Test
    void getPersons() {
        List<Person> list = classToTest.getPersons();
        assertNotNull(list);
    }

    @Test
    void getFirestations() {
        List<Firestation> list = classToTest.getFirestations();
        assertNotNull(list);
    }

    @Test
    void getMedicalrecords() {
        List<Medicalrecord> list = classToTest.getMedicalrecords();
        assertNotNull(list);
    }
}