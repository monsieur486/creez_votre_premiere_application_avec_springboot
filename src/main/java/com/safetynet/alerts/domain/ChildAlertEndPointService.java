package com.safetynet.alerts.domain;

import com.safetynet.alerts.configuration.Constants;
import com.safetynet.alerts.dto.ChildAlertDto;
import com.safetynet.alerts.dto.ChildAlertPersonDto;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.jsonfile.MedicalRecordService;
import com.safetynet.alerts.service.jsonfile.PersonService;
import com.safetynet.alerts.utils.DateUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChildAlertEndPointService {

    private final PersonService personService;

    private final MedicalRecordService medicalRecordService;

    public ChildAlertEndPointService(PersonService personService, MedicalRecordService medicalRecordService) {
        this.personService = personService;
        this.medicalRecordService = medicalRecordService;
    }

    public ChildAlertDto getChildAlertByAddress(String address) {

        ChildAlertDto result = new ChildAlertDto();

        List<Person> persons = personService.getPersonsByAddress(address);

        if (persons != null) {
            for (Person person : persons) {
                MedicalRecord medicalRecord = medicalRecordService.getMedicalrecordsByFirstNameAndLastName(person.getFirstName(), person.getLastName()).get(0);

                if(medicalRecord != null) {
                    int age = DateUtils.getAge(medicalRecord.getBirthdate());
                    String firstName = person.getFirstName();
                    String lastName = person.getLastName();

                    ChildAlertPersonDto childAlertPersonDto = new ChildAlertPersonDto(firstName, lastName, age);

                    if (age <= Constants.MAX_AGE_CHILD) {
                        result.addChild(childAlertPersonDto);
                    } else {
                        result.addAdult(childAlertPersonDto);
                    }
                }

            }
        }

        return result;

    }

}
