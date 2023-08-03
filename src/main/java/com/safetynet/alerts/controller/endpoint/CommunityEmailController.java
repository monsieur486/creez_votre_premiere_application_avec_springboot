package com.safetynet.alerts.controller.endpoint;

import com.safetynet.alerts.domain.CommunityEmailEndPointService;
import com.safetynet.alerts.utils.ResponseHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/communityEmail")
@Slf4j
public class CommunityEmailController {

    private final CommunityEmailEndPointService communityEmailEndPointService;

    public CommunityEmailController(CommunityEmailEndPointService communityEmailEndPointService) {
        this.communityEmailEndPointService = communityEmailEndPointService;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getEndPoint(@RequestParam String city) {

        return ResponseHandler.generateResponse(
                "communityEmails",
                HttpStatus.OK,
                "emailsList",
                communityEmailEndPointService.getCommunityEmailByCity(city)
        );
    }
}
