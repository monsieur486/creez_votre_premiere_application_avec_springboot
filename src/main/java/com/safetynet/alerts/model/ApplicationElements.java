package com.safetynet.alerts.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ApplicationElements implements IApplicationElements{
    private ArrayList<Person> persons = new ArrayList<>();
    private ArrayList<Firestation> firestations = new ArrayList<>();
    private ArrayList<Medicalrecord> medicalrecords = new ArrayList<>();
}
