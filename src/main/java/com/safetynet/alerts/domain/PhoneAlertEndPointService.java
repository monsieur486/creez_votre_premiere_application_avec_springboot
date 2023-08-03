package com.safetynet.alerts.domain;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.jsonfile.FirestationService;
import com.safetynet.alerts.service.jsonfile.PersonService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class PhoneAlertEndPointService {

    private final PersonService personService;

    private final FirestationService firestationService;

    public PhoneAlertEndPointService(
            FirestationService firestationService,
            PersonService personService
    ) {
        this.firestationService = firestationService;
        this.personService = personService;
    }

    public List<String> getPhoneAlertByStationNumber(Integer stationNumber) {
        HashSet<String> phoneAlert = new HashSet<>();
        List<Firestation> firestations = firestationService.getFirestationsByStation(stationNumber);
        if (firestations != null) {
            for (Firestation firestation : firestations) {
                List<Person> personsByAdress = personService.getPersonsByAddress(firestation.getAddress());
                if (personsByAdress != null) {
                    for (Person person : personsByAdress) {
                        phoneAlert.add(person.getPhone());
                    }
                }
            }
        }

        return new ArrayList<>(phoneAlert);
    }

}
