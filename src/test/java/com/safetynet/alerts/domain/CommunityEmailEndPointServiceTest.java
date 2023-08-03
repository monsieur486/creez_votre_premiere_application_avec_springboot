package com.safetynet.alerts.domain;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.jsonfile.PersonService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CommunityEmailEndPointServiceTest {

    PersonService personService = mock(PersonService.class);

    CommunityEmailEndPointService communityEmailEndPointService = new CommunityEmailEndPointService(personService);
    @Test
    void getCommunityEmailByCity() {
        Person person = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "");

        List<Person> personList = List.of(person);
        String city = "Culver";

        when(personService.getPersonsByCity(city)).thenReturn(personList);

        List<String> result = communityEmailEndPointService.getCommunityEmailByCity(city);
        assertNotNull(result);
    }
}