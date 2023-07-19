package com.safetynet.alerts.dto;

import com.safetynet.alerts.model.ApplicationElements;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.Medicalrecord;
import lombok.Data;

import java.util.ArrayList;

@Data
public class ApplicationElementsDto implements ApplicationElements {
    private ArrayList<Person> persons = new ArrayList<>();
    private ArrayList<Firestation> firestations = new ArrayList<>();
    private ArrayList<Medicalrecord> medicalrecords = new ArrayList<>();
}
