package com.safetynet.alerts.domain;

import com.safetynet.alerts.dto.PersonInfoDto;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.jsonfile.MedicalRecordService;
import com.safetynet.alerts.service.jsonfile.PersonService;
import com.safetynet.alerts.utils.DateUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonInfoEndPointService {

    private final PersonService personService;

    private final MedicalRecordService medicalRecordService;

    public PersonInfoEndPointService(PersonService personService, MedicalRecordService medicalRecordService) {
        this.personService = personService;
        this.medicalRecordService = medicalRecordService;
    }

    public List<PersonInfoDto> getPersonInfo(String firstName, String lastName) {
        List<PersonInfoDto> personInfoDtoList = new ArrayList<>();

        List<Person> personList = personService.getPeronsByFirstNameAndLastName(firstName, lastName);
        if(personList != null && !personList.isEmpty()) {
            for (Person person : personList) {
                PersonInfoDto personInfoDto = new PersonInfoDto();
                personInfoDto.setFirstName(person.getFirstName());
                personInfoDto.setLastName(person.getLastName());
                personInfoDto.setAddress(person.getAddress());
                personInfoDto.setEmail(person.getEmail());
                personInfoDtoList.add(personInfoDto);

                MedicalRecord medicalRecord = medicalRecordService.getMedicalrecordsByFirstNameAndLastName(firstName, lastName).get(0);
                if(medicalRecord != null) {
                    personInfoDto.setAge(DateUtils.getAge(medicalRecord.getBirthdate()));
                    personInfoDto.setMedications(medicalRecord.getMedications());
                    personInfoDto.setAllergies(medicalRecord.getAllergies());
                }
            }
        }

        return personInfoDtoList;
    }
}
