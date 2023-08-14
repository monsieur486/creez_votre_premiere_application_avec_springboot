package com.safetynet.alerts.dto;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * The type Fire person dto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FirePersonDto {
    private String firstName;
    private String lastName;
    private String phone;
    private Integer age;
    private List<String> medications;
    private List<String> allergies;

    /**
     * Instantiates a new Fire person dto.
     *
     * @param person        the person
     * @param medicalRecord the medical record
     */
    public FirePersonDto(Person person, MedicalRecord medicalRecord) {
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.phone = person.getPhone();
        this.age = DateUtils.getAge(medicalRecord.getBirthdate());
        this.medications = medicalRecord.getMedications();
        this.allergies = medicalRecord.getAllergies();
    }
}
