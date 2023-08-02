package com.safetynet.alerts.service.jsonfile;

import com.safetynet.alerts.model.MedicalRecord;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MedicalRecordServiceTest {

    MedicalRecordService classToTest = new MedicalRecordService();

    @Test
    void getAllMedicalrecords() {
        List<MedicalRecord> medicalRecordList = classToTest.getAllMedicalrecords();
        assertNotNull(medicalRecordList);
    }

    @Test
    void save() {
        int initRecord = classToTest.getAllMedicalrecords().size();
        ArrayList<String> vide = new ArrayList<>();
        MedicalRecord medicalRecord = new MedicalRecord(
                "Laurent",
                "Touret",
                "03/01/1973",
                vide,
                vide
        );
        classToTest.save(medicalRecord);
        assertEquals(initRecord + 1, classToTest.getAllMedicalrecords().size());
    }

    @Test
    void update() {
        ArrayList<String> vide = new ArrayList<>();
        MedicalRecord medicalRecord = new MedicalRecord(
                "John",
                "Boyd",
                "03/01/1973",
                vide,
                vide
        );
        boolean retour = classToTest.update(medicalRecord);
        assertTrue(retour);
    }

    @Test
    void updateNotFound() {
        ArrayList<String> vide = new ArrayList<>();
        MedicalRecord medicalRecord = new MedicalRecord(
                "xxx",
                "xxx",
                "03/01/1973",
                vide,
                vide
        );
        boolean retour = classToTest.update(medicalRecord);
        assertFalse(retour);
    }

    @Test
    void delete() {
        boolean retour = classToTest.delete("John", "Boyd");
        assertTrue(retour);
    }

    @Test
    void deleteNotFound() {
        boolean retour = classToTest.delete("xxx", "xxx");
        assertFalse(retour);
    }
}