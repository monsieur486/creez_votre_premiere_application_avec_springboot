package com.safetynet.alerts.domain;

import com.safetynet.alerts.dto.PeopleCoveredDto;
import com.safetynet.alerts.dto.PersonCoveredDto;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.jsonfile.JsonFileFirestationService;
import com.safetynet.alerts.service.jsonfile.JsonFileMedicalRecordService;
import com.safetynet.alerts.service.jsonfile.JsonFilePersonService;
import com.safetynet.alerts.utils.DateUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FirestationEndPointService {

    private final JsonFilePersonService jsonFilePersonService;

    private final JsonFileFirestationService jsonFileFirestationService;

    private final JsonFileMedicalRecordService jsonFileMedicalRecordService;

    public FirestationEndPointService(
            JsonFileFirestationService jsonFileFirestationService,
            JsonFilePersonService jsonFilePersonService,
            JsonFileMedicalRecordService jsonFileMedicalRecordService
    ) {
        this.jsonFileFirestationService = jsonFileFirestationService;
        this.jsonFilePersonService = jsonFilePersonService;
        this.jsonFileMedicalRecordService = jsonFileMedicalRecordService;
    }

    public PeopleCoveredDto getPeopleCoveredByStationNumber(Integer stationNumber) {
        PeopleCoveredDto peopleCoveredDto = new PeopleCoveredDto();
        int adults = 0;
        int children = 0;
        List<PersonCoveredDto> people = new ArrayList<>();

        List<Firestation> firestations = jsonFileFirestationService.getFirestationsByStation(stationNumber);

        for (Firestation firestation : firestations) {
            List<Person> personsByAdress = jsonFilePersonService.getPersonsByAddress(firestation.getAddress());
            if (personsByAdress != null) {
                for (Person person : personsByAdress) {
                    people.add(new PersonCoveredDto(person));
                    MedicalRecord medicalRecord = jsonFileMedicalRecordService.getMedicalrecordsByFirstNameAndLastName(
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
