package com.safetynet.alerts.service.jsonfile;

import com.safetynet.alerts.configuration.Constants;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.IMedicalRecordService;
import com.safetynet.alerts.utils.ElementsFromJsonFile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class MedicalRecordService implements IMedicalRecordService {

    private final List<MedicalRecord> medicalRecords;

    public MedicalRecordService() {
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
        for (int i = 0; i < medicalRecords.size(); i++) {
            if (Objects.equals(
                    medicalRecords.get(i).getFirstName(), medicalRecord.getFirstName())
                    && Objects.equals(medicalRecords.get(i).getLastName(),
                    medicalRecord.getLastName())) {
                firstName = medicalRecord.getFirstName();
                lastName = medicalRecord.getLastName();
                MedicalRecord medicalRecordToUpdate = new MedicalRecord();
                medicalRecordToUpdate.setFirstName(firstName);
                medicalRecordToUpdate.setLastName(lastName);
                medicalRecordToUpdate.setBirthdate(medicalRecord.getBirthdate());
                medicalRecordToUpdate.setAllergies(medicalRecord.getAllergies());
                medicalRecordToUpdate.setMedications(medicalRecord.getMedications());
                medicalRecords.set(i, medicalRecordToUpdate);
                updateTest = true;
                break;
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
}