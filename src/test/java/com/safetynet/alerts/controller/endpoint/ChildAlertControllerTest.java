package com.safetynet.alerts.controller.endpoint;

import com.safetynet.alerts.domain.ChildAlertEndPointService;
import com.safetynet.alerts.dto.ChildAlertDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ChildAlertControllerTest {

    ChildAlertEndPointService childAlertEndPointService = mock(ChildAlertEndPointService.class);

    @Test
    void getEndPoint() {
        ChildAlertDto childAlertDto = new ChildAlertDto();

        when(childAlertEndPointService.getChildAlertByAddress("1509 Culver St")).thenReturn(childAlertDto);
        ChildAlertController classToTest = new ChildAlertController(childAlertEndPointService);
        assertNotNull(classToTest.getEndPoint("1509 Culver St"));
    }
}