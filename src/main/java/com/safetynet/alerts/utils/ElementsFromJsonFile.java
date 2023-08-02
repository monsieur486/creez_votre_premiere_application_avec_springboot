package com.safetynet.alerts.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.configuration.Constants;
import com.safetynet.alerts.model.*;
import lombok.extern.slf4j.Slf4j;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;

@Slf4j
public class ElementsFromJsonFile implements IApplicationElements {
    public static ApplicationElements getJsonData() {

        ObjectMapper objectMapper = new ObjectMapper();
        ApplicationElements result = new ApplicationElements();

        try {
            byte[] json = Files.readAllBytes(Paths.get(Constants.JSON_DATA_FILE_NAME));
            result = objectMapper.readValue(json, ApplicationElements.class);
            log.trace("import of " + Constants.JSON_DATA_FILE_NAME + " file completed successfully");
            return result;

        } catch (Exception e) {
            log.error("error while importing " + Constants.JSON_DATA_FILE_NAME + " file");
        }

        return result;

    }

    @Override
    public ArrayList<Person> getPersons() {
        log.trace("Get persons list from json file: " + Constants.JSON_DATA_FILE_NAME);
        return getJsonData().getPersons();
    }

    @Override
    public ArrayList<Firestation> getFirestations() {
        log.trace("Get firestations list from json file: " + Constants.JSON_DATA_FILE_NAME);
        return getJsonData().getFirestations();
    }

    @Override
    public ArrayList<Medicalrecord> getMedicalrecords() {
        log.trace("Get medical records list from json file: " + Constants.JSON_DATA_FILE_NAME);
        return getJsonData().getMedicalrecords();
    }
}
