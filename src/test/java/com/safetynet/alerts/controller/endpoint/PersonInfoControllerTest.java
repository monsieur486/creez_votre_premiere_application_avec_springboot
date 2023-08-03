package com.safetynet.alerts.controller.endpoint;

import com.safetynet.alerts.domain.PersonInfoEndPointService;
import com.safetynet.alerts.dto.PersonInfoDto;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PersonInfoControllerTest {

    PersonInfoEndPointService personInfoEndPointService = mock(PersonInfoEndPointService.class);

    PersonInfoController classToTest = new PersonInfoController(personInfoEndPointService);

    @Test
    void getEndPoint() {
        Person person = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "");
        MedicalRecord medicalRecord = new MedicalRecord("John", "Boyd", "03/06/1984", null, null);

        PersonInfoDto personInfoDto = new PersonInfoDto(person, medicalRecord);
        List<PersonInfoDto> personInfoDtoList = List.of(personInfoDto);

        when(personInfoEndPointService.getPersonInfo("John", "Boyd")).thenReturn(personInfoDtoList);
        assertNotNull(classToTest.getEndPoint("John", "Boyd"));
    }
}