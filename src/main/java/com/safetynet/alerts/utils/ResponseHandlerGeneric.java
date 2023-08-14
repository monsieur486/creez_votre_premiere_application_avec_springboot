package com.safetynet.alerts.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public abstract class ResponseHandlerGeneric {
    private HttpStatus status;
    private String message;
}
