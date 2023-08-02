package com.safetynet.alerts.controller;

import com.safetynet.alerts.controller.crud.MedicalRecordController;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.jsonfile.MedicalRecordService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class MedicalRecordControllerTest {

    MedicalRecordService medicalRecordService = mock(MedicalRecordService.class);

    MedicalRecordController classToTest = new MedicalRecordController(medicalRecordService);

    @Test
    void findAllMedicalrecords() {
        List<MedicalRecord> medicalRecordList = new ArrayList<>();
        medicalRecordList.add(new MedicalRecord(
                "John",
                "Boyd",
                "03/06/1984",
                new ArrayList<>(),
                new ArrayList<>()));

        when(medicalRecordService.getAllMedicalrecords()).thenReturn(medicalRecordList);
        List<MedicalRecord> medicalRecordListTest = classToTest.findAllMedicalrecords();
        assertEquals(medicalRecordListTest, medicalRecordList);
    }

    @Test
    void addMedicalrecord() {
        MedicalRecord medicalRecord = new MedicalRecord(
                "John",
                "Boyd",
                "03/06/1984",
                new ArrayList<>(),
                new ArrayList<>());

        when(medicalRecordService.save(medicalRecord)).thenReturn(medicalRecord);
        MedicalRecord medicalRecordTest = classToTest.addMedicalrecord(medicalRecord);
        assertEquals(medicalRecordTest, medicalRecord);
    }

    @Test
    void update() {
        MedicalRecord medicalRecord = new MedicalRecord(
                "John",
                "Boyd",
                "03/06/1984",
                new ArrayList<>(),
                new ArrayList<>());

        when(medicalRecordService.update(medicalRecord)).thenReturn(true);
        ResponseEntity<String> medicalRecordResponseEntity = classToTest.update(medicalRecord);
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

        when(medicalRecordService.update(medicalRecord)).thenReturn(false);
        ResponseEntity<String> medicalRecordResponseEntity = classToTest.update(medicalRecord);
        assertEquals(404, medicalRecordResponseEntity.getStatusCodeValue());

    }

    @Test
    void delete() {
        when(medicalRecordService.delete("John", "Boyd")).thenReturn(true);
        ResponseEntity<String> medicalRecordResponseEntity = classToTest.delete("John", "Boyd");
        assertEquals(200, medicalRecordResponseEntity.getStatusCodeValue());
    }

    @Test
    void deleteNotFound() {
        when(medicalRecordService.delete("John", "Boyd")).thenReturn(false);
        ResponseEntity<String> medicalRecordResponseEntity = classToTest.delete("John", "Boyd");
        assertEquals(404, medicalRecordResponseEntity.getStatusCodeValue());
    }
}