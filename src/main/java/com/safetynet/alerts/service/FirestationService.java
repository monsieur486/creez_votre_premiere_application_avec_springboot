package com.safetynet.alerts.service;

import com.safetynet.alerts.model.Firestation;

import java.util.List;

/**
 * The interface Firestation service.
 */
public interface FirestationService {
    /**
     * Gets all firestations.
     *
     * @return the all firestations
     */
    List<Firestation> getAllFirestations();

    /**
     * Save firestation.
     *
     * @param firestation the firestation
     * @return the firestation
     */
    Firestation save(Firestation firestation);

    /**
     * Update boolean.
     *
     * @param firestation the firestation
     * @return the boolean
     */
    Boolean update(Firestation firestation);

    /**
     * Delete boolean.
     *
     * @param firestation the firestation
     * @return the boolean
     */
    Boolean delete(Firestation firestation);

    /**
     * Gets firestations by station.
     *
     * @param station the station
     * @return the firestations by station
     */
    List<Firestation> getFirestationsByStation(Integer station);

    /**
     * Gets firestations by address.
     *
     * @param address the address
     * @return the firestations by address
     */
    List<Firestation> getFirestationsByAddress(String address);


    /**
     * Exists boolean.
     *
     * @param firestation the firestation
     * return the boolean
     */
    Boolean exists(Firestation firestation);

}
