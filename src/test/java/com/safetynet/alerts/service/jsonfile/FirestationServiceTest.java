package com.safetynet.alerts.service.jsonfile;

import com.safetynet.alerts.model.Firestation;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FirestationServiceTest {

    FirestationService classToTest = new FirestationService();

    @Test
    void getAllFirestations() {
        List<Firestation> firestationServiceList = classToTest.getAllFirestations();
        assertNotNull(firestationServiceList);
    }

    @Test
    void save() {
        int initRecord = classToTest.getAllFirestations().size();
        Firestation firestation = new Firestation(
                "1 rue de Paris",
                1
        );
        classToTest.save(firestation);
        assertEquals(initRecord + 1, classToTest.getAllFirestations().size());
    }

    @Test
    void update() {
        Firestation firestation = new Firestation(
                "908 73rd St",
                2
        );
        boolean retour = classToTest.update(firestation);
        assertTrue(retour);
    }

    @Test
    void updateNotFound() {
        Firestation firestation = new Firestation(
                "xxx",
                1
        );
        boolean retour = classToTest.update(firestation);
        assertFalse(retour);
    }

    @Test
    void delete() {
        Firestation firestation = new Firestation(
                "112 Steppes Pl",
                4
        );
        boolean retour = classToTest.delete(firestation);
        assertTrue(retour);
    }

    @Test
    void deleteNotFound() {
        Firestation firestation = new Firestation(
                "xxx",
                1
        );
        boolean retour = classToTest.delete(firestation);
        assertFalse(retour);
    }

    @Test
    void getFirestationsByStation() {
        List<Firestation> result = classToTest.getFirestationsByStation(1);
        assertNotNull(result);
    }

    @Test
    void getFirestationsByAddress() {
        List<Firestation> result = classToTest.getFirestationsByAddress("1509 Culver St");
        assertNotNull(result);
    }
}