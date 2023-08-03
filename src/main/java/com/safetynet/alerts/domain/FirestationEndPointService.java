package com.safetynet.alerts.domain;

import com.safetynet.alerts.dto.PeopleCoveredDto;
import com.safetynet.alerts.dto.PersonCoveredDto;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.jsonfile.FirestationService;
import com.safetynet.alerts.service.jsonfile.MedicalRecordService;
import com.safetynet.alerts.service.jsonfile.PersonService;
import com.safetynet.alerts.utils.DateUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FirestationEndPointService {

    private final PersonService personService;

    private final FirestationService firestationService;

    private final MedicalRecordService medicalRecordService;

    public FirestationEndPointService(
            FirestationService firestationService,
            PersonService personService,
            MedicalRecordService medicalRecordService
    ) {
        this.firestationService = firestationService;
        this.personService = personService;
        this.medicalRecordService = medicalRecordService;
    }

    public PeopleCoveredDto getPeopleCoveredByStationNumber(Integer stationNumber) {
        PeopleCoveredDto peopleCoveredDto = new PeopleCoveredDto();
        int adults = 0;
        int children = 0;
        List<PersonCoveredDto> people = new ArrayList<>();

        List<Firestation> firestations = firestationService.getFirestationsByStation(stationNumber);

        for(Firestation firestation : firestations) {
            List<Person> personsByAdress = personService.getPersonsByAddress(firestation.getAddress());
            if (personsByAdress != null) {
                for (Person person : personsByAdress) {
                    people.add(new PersonCoveredDto(person));
                    MedicalRecord medicalRecord = medicalRecordService.getMedicalrecordsByFirstNameAndLastName(
                            person.getFirstName(), person.getLastName()).get(0);
                    if (medicalRecord != null) {
                        if (DateUtils.isChild(medicalRecord.getBirthdate())) {
                            children++;
                        } else {
                            adults++;
                        }
                    }
                }
            }
        }

        peopleCoveredDto.setPersons(people);
        peopleCoveredDto.setAdults(adults);
        peopleCoveredDto.setChildren(children);

        return peopleCoveredDto;
    }
}
