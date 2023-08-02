package com.safetynet.alerts.controller;

import com.safetynet.alerts.controller.crud.FirestationController;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.service.jsonfile.FirestationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class FirestationControllerTest {

    FirestationService service = mock(FirestationService.class);

    FirestationController classToTest = new FirestationController(service);

    @Test
    void findAllFirestations() {
        List<Firestation> firestationList = new ArrayList<>();
        firestationList.add(new Firestation(
                "1 rue de Paris",
                1));

        when(service.getAllFirestations()).thenReturn(firestationList);
        List<Firestation> firestationListTest = classToTest.findAllFirestations();
        assertEquals(firestationListTest, firestationList);

        verify(service, times(1)).getAllFirestations();
    }

    @Test
    void addFirestation() {
        Firestation firestation = new Firestation(
                "1 rue de Paris",
                1);

        when(service.save(firestation)).thenReturn(firestation);
        Firestation firestationTest = classToTest.addFirestation(firestation);
        assertEquals(firestationTest, firestation);

        verify(service, times(1)).save(firestation);
    }

    @Test
    void updateProduct() {
        Firestation firestation = new Firestation(
                "1 rue de Paris",
                1);

        when(service.update(firestation)).thenReturn(true);
        ResponseEntity<String> response = classToTest.updateProduct(firestation);
        assertEquals(201, response.getStatusCodeValue());
    }

    @Test
    void updateProductNotFound() {
        Firestation firestation = new Firestation(
                "1 rue de Paris",
                1);

        when(service.update(firestation)).thenReturn(false);
        ResponseEntity<String> response = classToTest.updateProduct(firestation);
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void deleteFireStation() {
        Firestation firestation = new Firestation(
                "1 rue de Paris",
                1);

        when(service.delete(firestation)).thenReturn(true);
        ResponseEntity<String> response = classToTest.deleteFireStation(firestation);
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void deleteFireStationNotFound() {
        Firestation firestation = new Firestation(
                "1 rue de Paris",
                1);

        when(service.delete(firestation)).thenReturn(false);
        ResponseEntity<String> response = classToTest.deleteFireStation(firestation);
        assertEquals(404, response.getStatusCodeValue());
    }
}