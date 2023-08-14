package com.safetynet.alerts.controller.endpoint;

import com.safetynet.alerts.domain.CommunityEmailEndPointService;
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
 * The type Community email controller.
 */
@RestController
@RequestMapping("/communityEmail")
@Slf4j
public class CommunityEmailController {

    private final CommunityEmailEndPointService communityEmailEndPointService;

    /**
     * Instantiates a new Community email controller.
     *
     * @param communityEmailEndPointService the community email end point service
     */
    public CommunityEmailController(CommunityEmailEndPointService communityEmailEndPointService) {
        this.communityEmailEndPointService = communityEmailEndPointService;
    }

    /**
     * Gets end point.
     *
     * @param city the city
     * @return the end point
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getEndPoint(@RequestParam String city) {

        return new ResponseEntity<>(communityEmailEndPointService.getCommunityEmailByCity(city), HttpStatus.OK);
    }
}
