package com.safetynet.alerts.controller.endpoint;

import com.safetynet.alerts.domain.PersonInfoEndPointService;
import com.safetynet.alerts.dto.PersonInfoDto;
import com.safetynet.alerts.utils.ResponseHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The type Person info controller.
 */
@RestController
@RequestMapping("/personInfo")
@Slf4j
public class PersonInfoController {

    private final PersonInfoEndPointService personInfoEndPointService;

    /**
     * Instantiates a new Person info controller.
     *
     * @param personInfoEndPointService the person info end point service
     */
    public PersonInfoController(PersonInfoEndPointService personInfoEndPointService) {
        this.personInfoEndPointService = personInfoEndPointService;
    }

    /**
     * Gets end point.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @return the end point
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PersonInfoDto>> getEndPoint(@RequestParam String firstName, @RequestParam String lastName) {
        return new ResponseEntity<>(personInfoEndPointService.getPersonInfo(firstName, lastName), HttpStatus.OK);
    }
}
