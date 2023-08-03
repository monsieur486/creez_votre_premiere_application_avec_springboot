package com.safetynet.alerts.controller.endpoint;

import com.safetynet.alerts.utils.ResponseHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/childAlert")
@Slf4j
public class ChildAlertController {

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getEndPoint(@RequestParam String address) {

        return ResponseHandler.generateResponse("childAlert", HttpStatus.OK, "childalert", address);
    }
}
