package com.safetynet.alerts.domain;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.jsonfile.JsonFileFirestationService;
import com.safetynet.alerts.service.jsonfile.JsonFileMedicalRecordService;
import com.safetynet.alerts.service.jsonfile.JsonFilePersonService;
import com.safetynet.alerts.utils.DateUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FireEndPointServiceTest {

    JsonFilePersonService jsonFilePersonService = mock(JsonFilePersonService.class);

    JsonFileFirestationService jsonFileFirestationService = mock(JsonFileFirestationService.class);

    JsonFileMedicalRecordService jsonFileMedicalRecordService = mock(JsonFileMedicalRecordService.class);

    FireEndPointService classToTest = new FireEndPointService(jsonFilePersonService, jsonFileFirestationService, jsonFileMedicalRecordService);

    @Test
    void getPersonListByAddress() {
        Firestation firestation = new Firestation("1509 Culver St", 1);
        Person person = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "email");
        MedicalRecord medicalRecord = new MedicalRecord("John", "Boyd", DateUtils.getAdultBirthdate(), null, null);

        List<Firestation> firestations = new ArrayList<>();
        firestations.add(firestation);

        List<Person> persons = new ArrayList<>();
        persons.add(person);

        List<MedicalRecord> medicalRecords = new ArrayList<>();
        medicalRecords.add(medicalRecord);

        when(jsonFileFirestationService.getFirestationsByAddress(anyString())).thenReturn(firestations);
        when(jsonFilePersonService.getPersonsByAddress(anyString())).thenReturn(persons);
        when(jsonFileMedicalRecordService.getMedicalrecordsByFirstNameAndLastName(anyString(), anyString())).thenReturn(medicalRecords);

        assertNotNull(classToTest.getPersonListByAddress("1509 Culver St"));

    }

}

