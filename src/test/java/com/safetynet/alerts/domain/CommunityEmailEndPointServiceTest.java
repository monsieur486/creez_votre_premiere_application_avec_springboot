package com.safetynet.alerts.domain;

import com.safetynet.alerts.service.jsonfile.PersonService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CommunityEmailEndPointServiceTest {

    PersonService personService = mock(PersonService.class);

    CommunityEmailEndPointService communityEmailEndPointService = new CommunityEmailEndPointService(personService);
    @Test
    void getCommunityEmailByCity() {
        String city = "Culver";
        List<String> result = communityEmailEndPointService.getCommunityEmailByCity(city);
        assertNotNull(result);
    }
}