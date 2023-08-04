package com.safetynet.alerts.service;

import com.safetynet.alerts.model.Firestation;

import java.util.List;

public interface FirestationService {
    List<Firestation> getAllFirestations();

    Firestation save(Firestation firestation);

    Boolean update(Firestation firestation);

    Boolean delete(Firestation firestation);

    List<Firestation> getFirestationsByStation(Integer station);

    List<Firestation> getFirestationsByAddress(String address);

}
