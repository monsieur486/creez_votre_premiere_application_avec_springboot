package com.safetynet.alerts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * The type Medical record.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRecord {
    private String firstName;
    private String lastName;
    private String birthdate;
    private ArrayList<String> medications;
    private ArrayList<String> allergies;
}
