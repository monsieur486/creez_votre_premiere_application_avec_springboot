package com.safetynet.alerts.domain;

import com.safetynet.alerts.dto.FireDto;
import com.safetynet.alerts.dto.FirePersonDto;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.jsonfile.JsonFileFirestationService;
import com.safetynet.alerts.service.jsonfile.JsonFileMedicalRecordService;
import com.safetynet.alerts.service.jsonfile.JsonFilePersonService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Fire end point service.
 */
@Service
public class FireEndPointService {

    private final JsonFilePersonService jsonFilePersonService;

    private final JsonFileFirestationService jsonFileFirestationService;

    private final JsonFileMedicalRecordService jsonFileMedicalRecordService;


    /**
     * Instantiates a new Fire end point service.
     *
     * @param jsonFilePersonService        the json file person service
     * @param jsonFileFirestationService   the json file firestation service
     * @param jsonFileMedicalRecordService the json file medical record service
     */
    public FireEndPointService(JsonFilePersonService jsonFilePersonService, JsonFileFirestationService jsonFileFirestationService, JsonFileMedicalRecordService jsonFileMedicalRecordService) {
        this.jsonFilePersonService = jsonFilePersonService;
        this.jsonFileFirestationService = jsonFileFirestationService;
        this.jsonFileMedicalRecordService = jsonFileMedicalRecordService;
    }

    /**
     * Gets person list by address.
     *
     * @param address the address
     * @return the person list by address
     */
    public FireDto getPersonListByAddress(String address) {
        FireDto result = new FireDto();
        Firestation firestation = jsonFileFirestationService.getFirestationsByAddress(address).get(0);
        if (firestation != null) {
            result.setStationNumber(firestation.getStation());
        }

        List<Person> persons = jsonFilePersonService.getPersonsByAddress(address);
        if (persons != null) {
            for (Person person : persons) {
                MedicalRecord medicalRecord = jsonFileMedicalRecordService.getMedicalrecordsByFirstNameAndLastName(
                        person.getFirstName(), person.getLastName()).get(0);

                if (medicalRecord != null) {
                    result.addPerson(new FirePersonDto(person, medicalRecord));
                }
            }
        }

        return result;

    }
}
