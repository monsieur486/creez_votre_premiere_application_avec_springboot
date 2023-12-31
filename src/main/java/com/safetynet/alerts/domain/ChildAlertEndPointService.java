package com.safetynet.alerts.domain;

import com.safetynet.alerts.configuration.Constants;
import com.safetynet.alerts.dto.ChildAlertDto;
import com.safetynet.alerts.dto.ChildAlertPersonDto;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.jsonfile.JsonFileMedicalRecordService;
import com.safetynet.alerts.service.jsonfile.JsonFilePersonService;
import com.safetynet.alerts.utils.DateUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Child alert end point service.
 */
@Service
public class ChildAlertEndPointService {

    private final JsonFilePersonService jsonFilePersonService;

    private final JsonFileMedicalRecordService jsonFileMedicalRecordService;

    /**
     * Instantiates a new Child alert end point service.
     *
     * @param jsonFilePersonService        the json file person service
     * @param jsonFileMedicalRecordService the json file medical record service
     */
    public ChildAlertEndPointService(JsonFilePersonService jsonFilePersonService, JsonFileMedicalRecordService jsonFileMedicalRecordService) {
        this.jsonFilePersonService = jsonFilePersonService;
        this.jsonFileMedicalRecordService = jsonFileMedicalRecordService;
    }

    /**
     * Gets child alert by address.
     *
     * @param address the address
     * @return the child alert by address
     */
    public ChildAlertDto getChildAlertByAddress(String address) {

        ChildAlertDto result = new ChildAlertDto();

        List<Person> persons = jsonFilePersonService.getPersonsByAddress(address);

        if (persons != null) {
            for (Person person : persons) {
                MedicalRecord medicalRecord = jsonFileMedicalRecordService.getMedicalrecordsByFirstNameAndLastName(person.getFirstName(), person.getLastName()).get(0);

                if (medicalRecord != null) {
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
