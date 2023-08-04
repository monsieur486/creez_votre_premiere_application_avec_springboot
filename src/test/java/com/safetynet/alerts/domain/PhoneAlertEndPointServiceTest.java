package com.safetynet.alerts.domain;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.jsonfile.JsonFileFirestationService;
import com.safetynet.alerts.service.jsonfile.JsonFilePersonService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PhoneAlertEndPointServiceTest {

    JsonFilePersonService jsonFilePersonService = mock(JsonFilePersonService.class);


    JsonFileFirestationService jsonFileFirestationService = mock(JsonFileFirestationService.class);

    PhoneAlertEndPointService phoneAlertEndPointService = new PhoneAlertEndPointService(jsonFileFirestationService, jsonFilePersonService);

    @Test
    void getPhoneAlertByStationNumber() {
        Firestation firestation = new Firestation("1509 Culver St", 1);
        Person person = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "email");

        List<Firestation> firestations = new ArrayList<>();
        firestations.add(firestation);

        List<Person> persons = new ArrayList<>();
        persons.add(person);

        when(jsonFileFirestationService.getFirestationsByStation(anyInt())).thenReturn(firestations);
        when(jsonFilePersonService.getPersonsByAddress(anyString())).thenReturn(persons);

        List<String> result = phoneAlertEndPointService.getPhoneAlertByStationNumber(1);
        assertNotNull(result);
    }

    @Test
    void getPhoneAlertByStationNumberWithoutPerson() {
        Firestation firestation = new Firestation("1509 Culver St", 1);

        List<Firestation> firestations = new ArrayList<>();
        firestations.add(firestation);

        when(jsonFileFirestationService.getFirestationsByStation(anyInt())).thenReturn(firestations);
        when(jsonFilePersonService.getPersonsByAddress(anyString())).thenReturn(null);

        List<String> result = phoneAlertEndPointService.getPhoneAlertByStationNumber(1);
        assertNotNull(result);
    }

    @Test
    void getPhoneAlertByStationNumberWithoutFirestation() {
        when(jsonFileFirestationService.getFirestationsByStation(anyInt())).thenReturn(null);

        List<String> result = phoneAlertEndPointService.getPhoneAlertByStationNumber(1);
        assertNotNull(result);
    }
}