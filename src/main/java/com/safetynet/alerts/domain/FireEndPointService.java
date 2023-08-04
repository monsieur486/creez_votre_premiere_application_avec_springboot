package com.safetynet.alerts.domain;

import com.safetynet.alerts.dto.FireDto;
import com.safetynet.alerts.dto.FirePersonDto;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.jsonfile.FirestationService;
import com.safetynet.alerts.service.jsonfile.MedicalRecordService;
import com.safetynet.alerts.service.jsonfile.PersonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FireEndPointService {

    private final PersonService personService;

    private final FirestationService firestationService;

    private final MedicalRecordService medicalRecordService;


    public FireEndPointService(PersonService personService, FirestationService firestationService, MedicalRecordService medicalRecordService) {
        this.personService = personService;
        this.firestationService = firestationService;
        this.medicalRecordService = medicalRecordService;
    }

    public FireDto getPersonListByAddress(String address) {
        FireDto result = new FireDto();
        Firestation firestation = firestationService.getFirestationsByAddress(address).get(0);
        if (firestation != null) {
            result.setStationNumber(firestation.getStation());
        }

        List<Person> persons = personService.getPersonsByAddress(address);
        if (persons != null) {
            for (Person person : persons) {
                MedicalRecord medicalRecord = medicalRecordService.getMedicalrecordsByFirstNameAndLastName(
                        person.getFirstName(), person.getLastName()).get(0);

                if (medicalRecord != null) {
                    result.addPerson(new FirePersonDto(person, medicalRecord));
                }
            }
        }

        return result;

    }
}
