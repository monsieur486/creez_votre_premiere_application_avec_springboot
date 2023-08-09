package com.safetynet.alerts.controller.endpoint;

import com.safetynet.alerts.domain.CommunityEmailEndPointService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CommunityEmailControllerTest {

    CommunityEmailEndPointService communityEmailEndPointService = mock(CommunityEmailEndPointService.class);

    CommunityEmailController classToTest = new CommunityEmailController(communityEmailEndPointService);


    @Test
    void getEndPoint() {
        when(communityEmailEndPointService.getCommunityEmailByCity("Culver")).thenReturn(null);
        assertNotNull(classToTest.getEndPoint("Culver"));
    }
}