package com.safetynet.alerts.domain;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.jsonfile.JsonFileFirestationService;
import com.safetynet.alerts.service.jsonfile.JsonFilePersonService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Service
public class PhoneAlertEndPointService {

    private final JsonFilePersonService jsonFilePersonService;

    private final JsonFileFirestationService jsonFileFirestationService;

    public PhoneAlertEndPointService(
            JsonFileFirestationService jsonFileFirestationService,
            JsonFilePersonService jsonFilePersonService
    ) {
        this.jsonFileFirestationService = jsonFileFirestationService;
        this.jsonFilePersonService = jsonFilePersonService;
    }

    public List<String> getPhoneAlertByStationNumber(Integer stationNumber) {
        HashSet<String> phoneAlert = new HashSet<>();
        List<Firestation> firestations = jsonFileFirestationService.getFirestationsByStation(stationNumber);
        if (firestations != null) {
            for (Firestation firestation : firestations) {
                List<Person> personsByAdress = jsonFilePersonService.getPersonsByAddress(firestation.getAddress());
                if (personsByAdress != null) {
                    for (Person person : personsByAdress) {
                        phoneAlert.add(person.getPhone());
                    }
                }
            }
        }

        List<String> result = new java.util.ArrayList<>(List.copyOf(phoneAlert));
        Collections.sort(result);

        return result;
    }

}
