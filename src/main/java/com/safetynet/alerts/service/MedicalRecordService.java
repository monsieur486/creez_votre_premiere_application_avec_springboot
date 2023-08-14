package com.safetynet.alerts.service;

import com.safetynet.alerts.model.MedicalRecord;

import java.util.List;

/**
 * The interface Medical record service.
 */
public interface MedicalRecordService {
    /**
     * Gets all medicalrecords.
     *
     * @return the all medicalrecords
     */
    List<MedicalRecord> getAllMedicalrecords();

    /**
     * Save medical record.
     *
     * @param medicalRecord the medical record
     * @return the medical record
     */
    MedicalRecord save(MedicalRecord medicalRecord);

    /**
     * Update boolean.
     *
     * @param medicalRecord the medical record
     * @return the boolean
     */
    Boolean update(MedicalRecord medicalRecord);

    /**
     * Delete boolean.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @return the boolean
     */
    Boolean delete(String firstName, String lastName);

    /**
     * Gets medicalrecords by first name and last name.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @return the medicalrecords by first name and last name
     */
    List<MedicalRecord> getMedicalrecordsByFirstNameAndLastName(String firstName, String lastName);


    /**
     * Exists boolean.
     *
     * @param medicalRecord the medical record
     * @return the boolean
     */
    Boolean exists(MedicalRecord medicalRecord);

}
