package com.safetynet.alerts.utils;

import com.safetynet.alerts.dto.ApplicationElementsDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ElementsFromJsonFileTest {


    @Test
    void getJsonData() {
        ApplicationElementsDto elementsDto = ElementsFromJsonFile.getJsonData("nullfile.txt");
        assertTrue(elementsDto.getPersons().isEmpty());
        assertTrue(elementsDto.getFirestations().isEmpty());
        assertTrue(elementsDto.getMedicalrecords().isEmpty());
    }
}