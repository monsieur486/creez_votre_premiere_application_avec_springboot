package com.safetynet.alerts.service;

import com.safetynet.alerts.model.MedicalRecord;

import java.util.List;

public interface IMedicalRecordService {
    List<MedicalRecord> getAllMedicalrecords();

    MedicalRecord save(MedicalRecord medicalRecord);

    Boolean update(MedicalRecord medicalRecord);

    Boolean delete(String firstName, String lastName);

    List<MedicalRecord> getMedicalrecordsByFirstNameAndLastName(String firstName, String lastName);

}
