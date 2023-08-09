package com.safetynet.alerts.dto;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationElementsDto {
    private ArrayList<Person> persons = new ArrayList<>();
    private ArrayList<Firestation> firestations = new ArrayList<>();
    private ArrayList<MedicalRecord> medicalrecords = new ArrayList<>();
}
