package com.safetynet.alerts.controller;

import com.safetynet.alerts.controller.crud.MedicalRecordController;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.jsonfile.JsonFileMedicalRecordService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class MedicalRecordControllerTest {

    JsonFileMedicalRecordService jsonFileMedicalRecordService = mock(JsonFileMedicalRecordService.class);

    MedicalRecordController classToTest = new MedicalRecordController(jsonFileMedicalRecordService);

    @Test
    void findAllMedicalrecords() {
        List<MedicalRecord> medicalRecordList = new ArrayList<>();
        medicalRecordList.add(new MedicalRecord(
                "John",
                "Boyd",
                "03/06/1984",
                new ArrayList<>(),
                new ArrayList<>()));

        when(jsonFileMedicalRecordService.getAllMedicalrecords()).thenReturn(medicalRecordList);
        ResponseEntity<List<MedicalRecord>> response = classToTest.findAllMedicalrecords();
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void addMedicalrecord() {
        MedicalRecord medicalRecord = new MedicalRecord(
                "John",
                "Boyd",
                "03/06/1984",
                new ArrayList<>(),
                new ArrayList<>());

        when(jsonFileMedicalRecordService.save(medicalRecord)).thenReturn(medicalRecord);
        ResponseEntity<MedicalRecord> medicalRecordResponseEntity = classToTest.addMedicalrecord(medicalRecord);
        assertEquals(201, medicalRecordResponseEntity.getStatusCodeValue());
    }

    @Test
    void update() {
        MedicalRecord medicalRecord = new MedicalRecord(
                "John",
                "Boyd",
                "03/06/1984",
                new ArrayList<>(),
                new ArrayList<>());

        when(jsonFileMedicalRecordService.update(medicalRecord)).thenReturn(true);
        ResponseEntity<MedicalRecord> medicalRecordResponseEntity = classToTest.update(medicalRecord);
        assertEquals(201, medicalRecordResponseEntity.getStatusCodeValue());
    }

    @Test
    void updateNotFound() {
        MedicalRecord medicalRecord = new MedicalRecord(
                "John",
                "Boyd",
                "03/06/1984",
                new ArrayList<>(),
                new ArrayList<>());

        when(jsonFileMedicalRecordService.update(medicalRecord)).thenReturn(false);
        ResponseEntity<MedicalRecord> medicalRecordResponseEntity = classToTest.update(medicalRecord);
        assertEquals(404, medicalRecordResponseEntity.getStatusCodeValue());
    }

    @Test
    void delete() {
        when(jsonFileMedicalRecordService.delete("John", "Boyd")).thenReturn(true);
        ResponseEntity<String> medicalRecordResponseEntity = classToTest.delete("John", "Boyd");
        assertEquals(200, medicalRecordResponseEntity.getStatusCodeValue());
    }

    @Test
    void deleteNotFound() {
        when(jsonFileMedicalRecordService.delete("John", "Boyd")).thenReturn(false);
        ResponseEntity<String> medicalRecordResponseEntity = classToTest.delete("John", "Boyd");
        assertEquals(404, medicalRecordResponseEntity.getStatusCodeValue());
    }
}