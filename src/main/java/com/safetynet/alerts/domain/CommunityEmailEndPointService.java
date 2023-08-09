package com.safetynet.alerts.domain;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.jsonfile.JsonFilePersonService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Service
public class CommunityEmailEndPointService {

    private final JsonFilePersonService jsonFilePersonService;

    public CommunityEmailEndPointService(
            JsonFilePersonService jsonFilePersonService
    ) {
        this.jsonFilePersonService = jsonFilePersonService;
    }

    public List<String> getCommunityEmailByCity(String city) {
        HashSet<String> communityEmail = new HashSet<>();
        List<Person> persons = jsonFilePersonService.getPersonsByCity(city);
        if (persons != null) {
            for (Person person : persons) {
                communityEmail.add(person.getEmail());
            }
        }

        List<String> result = new java.util.ArrayList<>(List.copyOf(communityEmail));
        Collections.sort(result);

        return result;
    }

}
