package com.safetynet.alerts.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class Firestation {
    private String address;
    private Integer station;
}
