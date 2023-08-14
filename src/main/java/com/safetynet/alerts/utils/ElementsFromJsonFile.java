package com.safetynet.alerts.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.dto.ApplicationElementsDto;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * The type Elements from json file.
 */
@Slf4j
public class ElementsFromJsonFile {

    private ElementsFromJsonFile() {
    }

    /**
     * Gets json data.
     *
     * @param fileName the file name
     * @return the json data
     */
    public static ApplicationElementsDto getJsonData(String fileName) {


        ObjectMapper objectMapper = new ObjectMapper();
        ApplicationElementsDto result = new ApplicationElementsDto();

        try {
            byte[] json = Files.readAllBytes(Paths.get(fileName));
            result = objectMapper.readValue(json, ApplicationElementsDto.class);
            log.trace("import of " + fileName + " file completed successfully");
            return result;

        } catch (Exception e) {
            log.error("error while importing " + fileName + " file");
        }

        return result;

    }
}
