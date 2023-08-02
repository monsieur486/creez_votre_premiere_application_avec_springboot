package com.safetynet.alerts.model;

import java.util.ArrayList;

public interface IApplicationElements {
    ArrayList<Person> getPersons();

    ArrayList<Firestation> getFirestations();

    ArrayList<Medicalrecord> getMedicalrecords();
}
