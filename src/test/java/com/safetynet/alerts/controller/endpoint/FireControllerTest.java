package com.safetynet.alerts.controller.endpoint;

import com.safetynet.alerts.domain.FireEndPointService;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.jsonfile.JsonFileFirestationService;
import com.safetynet.alerts.service.jsonfile.JsonFileMedicalRecordService;
import com.safetynet.alerts.service.jsonfile.JsonFilePersonService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FireControllerTest {

    FireEndPointService fireEndPointService = mock(FireEndPointService.class);
    JsonFileFirestationService jsonFileFirestationService = mock(JsonFileFirestationService.class);
    JsonFilePersonService jsonFilePersonService = mock(JsonFilePersonService.class);
    JsonFileMedicalRecordService jsonFileMedicalRecordService = mock(JsonFileMedicalRecordService.class);

    FireController classToTest = new FireController(fireEndPointService);

    @Test
    void getEndPoint() {
        Firestation firestation = new Firestation("1509 Culver St", 1);
        Person person = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "");
        MedicalRecord medicalRecord = new MedicalRecord("John", "Boyd", "03/06/1984", null, null);

        when(jsonFileFirestationService.getFirestationsByStation(anyInt())).thenReturn(List.of(firestation));
        when(jsonFilePersonService.getPersonsByAddress(anyString())).thenReturn(List.of(person));
        when(jsonFileMedicalRecordService.getMedicalrecordsByFirstNameAndLastName(anyString(), anyString())).thenReturn(List.of(medicalRecord));

        assertNotNull(classToTest.getEndPoint("1509 Culver St"));

    }
}