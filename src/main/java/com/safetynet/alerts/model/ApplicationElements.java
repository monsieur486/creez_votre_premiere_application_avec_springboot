package com.safetynet.alerts.model;

import java.util.ArrayList;

public interface ApplicationElements {
    ArrayList<Person> getPersons();

    ArrayList<Firestation> getFirestations();

    ArrayList<Medicalrecord> getMedicalrecords();
}
