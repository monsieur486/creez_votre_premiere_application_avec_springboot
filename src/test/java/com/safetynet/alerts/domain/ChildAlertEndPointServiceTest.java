package com.safetynet.alerts.domain;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.jsonfile.JsonFileMedicalRecordService;
import com.safetynet.alerts.service.jsonfile.JsonFilePersonService;
import com.safetynet.alerts.utils.DateUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ChildAlertEndPointServiceTest {

    JsonFilePersonService jsonFilePersonService = mock(JsonFilePersonService.class);

    JsonFileMedicalRecordService jsonFileMedicalRecordService = mock(JsonFileMedicalRecordService.class);

    ChildAlertEndPointService classToTest = new ChildAlertEndPointService(jsonFilePersonService, jsonFileMedicalRecordService);

    @Test
    void getChildAlertByAddressWithAdult() {
        Person person = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "");
        MedicalRecord medicalRecord = new MedicalRecord("John", "Boyd", DateUtils.getAdultBirthdate(), null, null);

        List<Person> personList = List.of(person);
        List<MedicalRecord> medicalRecordList = List.of(medicalRecord);

        when(jsonFilePersonService.getPersonsByAddress(anyString())).thenReturn(personList);
        when(jsonFileMedicalRecordService.getMedicalrecordsByFirstNameAndLastName(anyString(), anyString())).thenReturn(medicalRecordList);
        assertNotNull(classToTest.getChildAlertByAddress("1509 Culver St"));

    }

    @Test
    void getChildAlertByAddressWithChild() {
        Person person = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "");
        MedicalRecord medicalRecord = new MedicalRecord("John", "Boyd", DateUtils.getChildBirthdate(), null, null);

        List<Person> personList = List.of(person);
        List<MedicalRecord> medicalRecordList = List.of(medicalRecord);

        when(jsonFilePersonService.getPersonsByAddress(anyString())).thenReturn(personList);
        when(jsonFileMedicalRecordService.getMedicalrecordsByFirstNameAndLastName(anyString(), anyString())).thenReturn(medicalRecordList);
        assertNotNull(classToTest.getChildAlertByAddress("1509 Culver St"));

    }
}