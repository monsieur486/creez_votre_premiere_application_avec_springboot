package com.safetynet.alerts.domain;

import com.safetynet.alerts.dto.PeopleCoveredDto;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.jsonfile.FirestationService;
import com.safetynet.alerts.service.jsonfile.MedicalRecordService;
import com.safetynet.alerts.service.jsonfile.PersonService;
import com.safetynet.alerts.utils.DateUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class FirestationEndPointServiceTest {


    PersonService personService = mock(PersonService.class);


    FirestationService firestationService = mock(FirestationService.class);


    MedicalRecordService medicalRecordService = mock(MedicalRecordService.class);

    FirestationEndPointService classToTest = new FirestationEndPointService(firestationService, personService, medicalRecordService);

    DateUtils dateUtils = mock(DateUtils.class);


    @Test
    void getPeopleCoveredByStationNumberWithOneAdult() {
        Firestation firestation = new Firestation("1509 Culver St", 1);
        Person person = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "email");
        MedicalRecord medicalRecord = new MedicalRecord("John", "Boyd", DateUtils.getAdultBirthdate(), null, null);

        List<Firestation> firestations = new ArrayList<>();
        firestations.add(firestation);

        List<Person> persons = new ArrayList<>();
        persons.add(person);

        List<MedicalRecord> medicalRecords = new ArrayList<>();
        medicalRecords.add(medicalRecord);

        when(firestationService.getFirestationsByStation(anyInt())).thenReturn(firestations);
        when(personService.getPersonsByAddress(anyString())).thenReturn(persons);
        when(medicalRecordService.getMedicalrecordsByFirstNameAndLastName(anyString(), anyString())).thenReturn(medicalRecords);

        PeopleCoveredDto result = classToTest.getPeopleCoveredByStationNumber(1);
        assertNotNull(result);
    }

    @Test
    void getPeopleCoveredByStationNumberWithOneChild() {
        Firestation firestation = new Firestation("1509 Culver St", 1);
        Person person = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "email");
        MedicalRecord medicalRecord = new MedicalRecord("John", "Boyd", DateUtils.getChildBirthdate(), null, null);

        List<Firestation> firestations = new ArrayList<>();
        firestations.add(firestation);

        List<Person> persons = new ArrayList<>();
        persons.add(person);

        List<MedicalRecord> medicalRecords = new ArrayList<>();
        medicalRecords.add(medicalRecord);

        when(firestationService.getFirestationsByStation(anyInt())).thenReturn(firestations);
        when(personService.getPersonsByAddress(anyString())).thenReturn(persons);
        when(medicalRecordService.getMedicalrecordsByFirstNameAndLastName(anyString(), anyString())).thenReturn(medicalRecords);

        PeopleCoveredDto result = classToTest.getPeopleCoveredByStationNumber(1);
        assertNotNull(result);
    }
}