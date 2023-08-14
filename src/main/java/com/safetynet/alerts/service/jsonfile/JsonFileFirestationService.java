package com.safetynet.alerts.service.jsonfile;

import com.safetynet.alerts.configuration.Constants;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.service.FirestationService;
import com.safetynet.alerts.utils.ElementsFromJsonFile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * The type Json file firestation service.
 */
@Service
public class JsonFileFirestationService implements FirestationService {
    private final List<Firestation> firestations;

    /**
     * Instantiates a new Json file firestation service.
     */
    public JsonFileFirestationService() {
        firestations = ElementsFromJsonFile.getJsonData(Constants.JSON_DATA_FILE_NAME).getFirestations();
    }

    public List<Firestation> getAllFirestations() {
        return firestations;
    }

    public Firestation save(Firestation firestation) {
        Firestation firestationToSave = new Firestation();
        firestationToSave.setAddress(firestation.getAddress());
        firestationToSave.setStation(firestation.getStation());
        firestations.add(firestationToSave);
        return firestationToSave;
    }

    public Boolean update(Firestation firestation) {
        String adress = "";
        Integer station = 0;
        boolean flag = false;
        for (int i = 0; i < firestations.size(); i++) {
            if (Objects.equals(
                    firestations.get(i).getAddress(), firestation.getAddress())
            ) {
                adress = firestation.getAddress();
                station = firestation.getStation();
                Firestation firestationToUpdate = new Firestation();
                firestationToUpdate.setAddress(adress);
                firestationToUpdate.setStation(station);
                firestations.set(i, firestationToUpdate);
                flag = true;
                break;
            }
        }

        return flag;
    }

    public Boolean delete(Firestation firestation) {
        boolean flag = false;
        for (int i = 0; i < firestations.size(); i++) {
            if (Objects.equals(
                    firestations.get(i).getAddress(), firestation.getAddress())
                    && Objects.equals(firestations.get(i).getStation(), firestation.getStation())) {
                firestations.remove(i);
                flag = true;
                break;

            }
        }

        return flag;
    }

    @Override
    public List<Firestation> getFirestationsByStation(Integer station) {
        return firestations.stream()
                .filter(firestation -> Objects.equals(firestation.getStation(), station))
                .collect(Collectors.toList());
    }

    @Override
    public List<Firestation> getFirestationsByAddress(String address) {
        return firestations.stream()
                .filter(firestation -> Objects.equals(firestation.getAddress().toLowerCase(), address.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public Boolean exists(Firestation firestation) {
        return firestations.stream()
                .anyMatch(firestation1 -> Objects.equals(firestation1.getAddress(), firestation.getAddress())
                        && Objects.equals(firestation1.getStation(), firestation.getStation()));
    }
}
