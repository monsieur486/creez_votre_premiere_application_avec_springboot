package com.safetynet.alerts.domain;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.jsonfile.FirestationService;
import com.safetynet.alerts.service.jsonfile.PersonService;
import com.safetynet.alerts.utils.DateUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PhoneAlertEndPointServiceTest {

    PersonService personService = mock(PersonService.class);


    FirestationService firestationService = mock(FirestationService.class);

    PhoneAlertEndPointService phoneAlertEndPointService = new PhoneAlertEndPointService(firestationService, personService);

    @Test
    void getPhoneAlertByStationNumber() {
        Firestation firestation = new Firestation("1509 Culver St", 1);
        Person person = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "email");

        List<Firestation> firestations = new ArrayList<>();
        firestations.add(firestation);

        List<Person> persons = new ArrayList<>();
        persons.add(person);

        when(firestationService.getFirestationsByStation(anyInt())).thenReturn(firestations);
        when(personService.getPersonsByAddress(anyString())).thenReturn(persons);

        List<String> result = phoneAlertEndPointService.getPhoneAlertByStationNumber(1);
        assertNotNull(result);
    }

    @Test
    void getPhoneAlertByStationNumberWithoutPerson() {
        Firestation firestation = new Firestation("1509 Culver St", 1);

        List<Firestation> firestations = new ArrayList<>();
        firestations.add(firestation);

        when(firestationService.getFirestationsByStation(anyInt())).thenReturn(firestations);
        when(personService.getPersonsByAddress(anyString())).thenReturn(null);

        List<String> result = phoneAlertEndPointService.getPhoneAlertByStationNumber(1);
        assertNotNull(result);
    }

    @Test
    void getPhoneAlertByStationNumberWithoutFirestation() {
        when(firestationService.getFirestationsByStation(anyInt())).thenReturn(null);

        List<String> result = phoneAlertEndPointService.getPhoneAlertByStationNumber(1);
        assertNotNull(result);
    }
}