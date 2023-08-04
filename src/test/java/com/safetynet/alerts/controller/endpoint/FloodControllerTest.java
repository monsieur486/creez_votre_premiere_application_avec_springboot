package com.safetynet.alerts.controller.endpoint;

import com.safetynet.alerts.domain.FloodEndPointService;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.jsonfile.FirestationService;
import com.safetynet.alerts.service.jsonfile.MedicalRecordService;
import com.safetynet.alerts.service.jsonfile.PersonService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FloodControllerTest {

    FloodEndPointService floodEndPointService = mock(FloodEndPointService.class);

    FirestationService firestationService = mock(FirestationService.class);
    PersonService personService = mock(PersonService.class);
    MedicalRecordService medicalRecordService = mock(MedicalRecordService.class);

    FloodController classToTest = new FloodController(floodEndPointService);

    @Test
    void getEndPoint() {
        Firestation firestation = new Firestation("1509 Culver St", 1);
        Person person = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "");
        MedicalRecord medicalRecord = new MedicalRecord("John", "Boyd", "03/06/1984", null, null);

        when(firestationService.getFirestationsByStation(anyInt())).thenReturn(List.of(firestation));
        when(personService.getPersonsByAddress(anyString())).thenReturn(List.of(person));
        when(medicalRecordService.getMedicalrecordsByFirstNameAndLastName(anyString(), anyString())).thenReturn(List.of(medicalRecord));

        assertNotNull(classToTest.getEndPoint("1,2,3"));
    }
}