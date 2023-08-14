package com.safetynet.alerts.service.jsonfile;

import com.safetynet.alerts.configuration.Constants;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.MedicalRecordService;
import com.safetynet.alerts.utils.ElementsFromJsonFile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * The type Json file medical record service.
 */
@Service
public class JsonFileMedicalRecordService implements MedicalRecordService {

    private final List<MedicalRecord> medicalRecords;

    /**
     * Instantiates a new Json file medical record service.
     */
    public JsonFileMedicalRecordService() {
        medicalRecords = ElementsFromJsonFile.getJsonData(Constants.JSON_DATA_FILE_NAME).getMedicalrecords();
    }

    public List<MedicalRecord> getAllMedicalrecords() {
        return medicalRecords;
    }

    public MedicalRecord save(MedicalRecord medicalrecord) {
        MedicalRecord medicalrecordToSave = new MedicalRecord();
        medicalrecordToSave.setFirstName(medicalrecord.getFirstName());
        medicalrecordToSave.setLastName(medicalrecord.getLastName());
        medicalrecordToSave.setBirthdate(medicalrecord.getBirthdate());
        medicalrecordToSave.setMedications(medicalrecord.getMedications());
        medicalrecordToSave.setAllergies(medicalrecord.getAllergies());
        medicalRecords.add(medicalrecordToSave);
        return medicalrecordToSave;
    }

    public Boolean update(MedicalRecord medicalRecord) {
        String firstName = "";
        String lastName = "";
        boolean updateTest = false;
        if(Boolean.TRUE.equals(exists(medicalRecord))) {
            for (int i = 0; i < medicalRecords.size(); i++) {
                if (Objects.equals(
                        medicalRecords.get(i).getFirstName(), medicalRecord.getFirstName())
                        && Objects.equals(medicalRecords.get(i).getLastName(),
                        medicalRecord.getLastName())) {
                    firstName = medicalRecord.getFirstName();
                    lastName = medicalRecord.getLastName();
                    MedicalRecord medicalRecordToUpdate = new MedicalRecord(
                            firstName,
                            lastName,
                            medicalRecord.getBirthdate(),
                            medicalRecord.getMedications(),
                            medicalRecord.getAllergies()
                    );
                    medicalRecords.set(i, medicalRecordToUpdate);
                    updateTest = true;
                    break;
                }
            }
        }
        return updateTest;
    }

    public Boolean delete(String firstName, String lastName) {
        boolean deleteTest = false;
        for (int i = 0; i < medicalRecords.size(); i++) {
            if (Objects.equals(
                    medicalRecords.get(i).getFirstName(), firstName)
                    && Objects.equals(medicalRecords.get(i).getLastName(),
                    lastName)) {
                medicalRecords.remove(i);
                deleteTest = true;
            }
        }
        return deleteTest;
    }

    @Override
    public List<MedicalRecord> getMedicalrecordsByFirstNameAndLastName(String firstName, String lastName) {
        return medicalRecords.stream()
                .filter(medicalRecord -> medicalRecord.getFirstName().equalsIgnoreCase(firstName)
                        && medicalRecord.getLastName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());
    }

    @Override
    public Boolean exists(MedicalRecord medicalRecord) {
        return medicalRecords.stream()
                .anyMatch(medicalRecord1 -> medicalRecord1.getFirstName().equalsIgnoreCase(medicalRecord.getFirstName())
                        && medicalRecord1.getLastName().equalsIgnoreCase(medicalRecord.getLastName()));
    }
}
