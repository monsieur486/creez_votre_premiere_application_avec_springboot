package com.safetynet.alerts.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Data
@NoArgsConstructor
public class MedicalRecord {
    private String firstName;
    private String lastName;
    private String birthdate;
    private ArrayList<String> medications;
    private ArrayList<String> allergies;
}
