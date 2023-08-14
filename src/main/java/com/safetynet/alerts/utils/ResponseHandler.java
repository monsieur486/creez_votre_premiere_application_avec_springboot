package com.safetynet.alerts.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Response handler.
 */
public class ResponseHandler {

    private ResponseHandler() {
    }

    /**
     * Generate response response entity.
     *
     * @param message     the message
     * @param status      the status
     * @param objectName  the object name
     * @param responseObj the response obj
     * @return the response entity
     */
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
