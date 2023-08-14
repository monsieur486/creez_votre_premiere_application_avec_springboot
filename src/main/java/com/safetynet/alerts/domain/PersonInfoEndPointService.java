package com.safetynet.alerts.domain;

import com.safetynet.alerts.dto.PersonInfoDto;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.jsonfile.JsonFileMedicalRecordService;
import com.safetynet.alerts.service.jsonfile.JsonFilePersonService;
import com.safetynet.alerts.utils.DateUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Person info end point service.
 */
@Service
public class PersonInfoEndPointService {

    private final JsonFilePersonService jsonFilePersonService;

    private final JsonFileMedicalRecordService jsonFileMedicalRecordService;

    /**
     * Instantiates a new Person info end point service.
     *
     * @param jsonFilePersonService        the json file person service
     * @param jsonFileMedicalRecordService the json file medical record service
     */
    public PersonInfoEndPointService(JsonFilePersonService jsonFilePersonService, JsonFileMedicalRecordService jsonFileMedicalRecordService) {
        this.jsonFilePersonService = jsonFilePersonService;
        this.jsonFileMedicalRecordService = jsonFileMedicalRecordService;
    }

    /**
     * Gets person info.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @return the person info
     */
    public List<PersonInfoDto> getPersonInfo(String firstName, String lastName) {
        List<PersonInfoDto> personInfoDtoList = new ArrayList<>();

        List<Person> personList = jsonFilePersonService.getPeronsByFirstNameAndLastName(firstName, lastName);
        if (personList != null && !personList.isEmpty()) {
            for (Person person : personList) {
                PersonInfoDto personInfoDto = new PersonInfoDto();
                personInfoDto.setFirstName(person.getFirstName());
                personInfoDto.setLastName(person.getLastName());
                personInfoDto.setAddress(person.getAddress());
                personInfoDto.setEmail(person.getEmail());
                personInfoDtoList.add(personInfoDto);

                MedicalRecord medicalRecord = jsonFileMedicalRecordService.getMedicalrecordsByFirstNameAndLastName(firstName, lastName).get(0);
                if (medicalRecord != null) {
                    personInfoDto.setAge(DateUtils.getAge(medicalRecord.getBirthdate()));
                    personInfoDto.setMedications(medicalRecord.getMedications());
                    personInfoDto.setAllergies(medicalRecord.getAllergies());
                }
            }
        }

        return personInfoDtoList;
    }
}
