package com.safetynet.alerts.domain;

import com.safetynet.alerts.dto.PersonInfoDto;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.jsonfile.FirestationService;
import com.safetynet.alerts.service.jsonfile.MedicalRecordService;
import com.safetynet.alerts.service.jsonfile.PersonService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PersonInfoEndPointServiceTest {

    PersonService personService = mock(PersonService.class);

    MedicalRecordService medicalRecordService = mock(MedicalRecordService.class);

    PersonInfoEndPointService classToTest = new PersonInfoEndPointService(personService, medicalRecordService);


    @Test
    void getPersonInfo() {
        Person person = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "");
        MedicalRecord medicalRecord = new MedicalRecord("John", "Boyd", "03/06/1984", null, null);

        List<Person> personList = List.of(person);
        List<MedicalRecord> medicalRecordList = List.of(medicalRecord);

        when(personService.getPeronsByFirstNameAndLastName("John", "Boyd")).thenReturn(personList);
        when(medicalRecordService.getMedicalrecordsByFirstNameAndLastName("John", "Boyd")).thenReturn(medicalRecordList);
        assertNotNull(classToTest.getPersonInfo("John", "Boyd"));
    }
}