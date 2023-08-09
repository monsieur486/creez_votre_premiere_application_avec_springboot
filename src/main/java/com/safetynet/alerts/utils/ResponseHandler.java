package com.safetynet.alerts.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {

    private ResponseHandler() {
    }

    public static ResponseEntity<Object> generateResponse(
            String message,
            HttpStatus status,
            String objectName,
            Object responseObj
    ) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", message);
        map.put("status", status.value());
        map.put(objectName, responseObj);

        return new ResponseEntity<>(map, status);
    }
}
