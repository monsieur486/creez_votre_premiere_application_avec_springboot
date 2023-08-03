package com.safetynet.alerts.controller.endpoint;

import com.safetynet.alerts.domain.PhoneAlertEndPointService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PhoneAlertControllerTest {

    PhoneAlertEndPointService phoneAlertEndPointService = mock(PhoneAlertEndPointService.class);

    PhoneAlertController classToTest = new PhoneAlertController(phoneAlertEndPointService);

    @Test
    void getEndPoint() {
        when(phoneAlertEndPointService.getPhoneAlertByStationNumber(1)).thenReturn(null);
        assertNotNull(classToTest.getEndPoint(1));
    }
}