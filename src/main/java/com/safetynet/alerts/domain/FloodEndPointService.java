package com.safetynet.alerts.domain;

import com.safetynet.alerts.dto.FirePersonDto;
import com.safetynet.alerts.dto.FloodDto;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.jsonfile.FirestationService;
import com.safetynet.alerts.service.jsonfile.MedicalRecordService;
import com.safetynet.alerts.service.jsonfile.PersonService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FloodEndPointService {

    private final PersonService personService;

    private final FirestationService firestationService;

    private final MedicalRecordService medicalRecordService;

    public FloodEndPointService(PersonService personService, FirestationService firestationService, MedicalRecordService medicalRecordService) {
        this.personService = personService;
        this.firestationService = firestationService;
        this.medicalRecordService = medicalRecordService;
    }

    public List<FloodDto> getPersonListByStationNumberList(String stationNumbers) {
        List<FloodDto> result = new ArrayList<>();
        for (Integer stationNumber : stationNumberList(stationNumbers)) {
            List<Firestation> firestations = firestationService.getFirestationsByStation(stationNumber);
            if (firestations != null && !firestations.isEmpty()) {
                for (Firestation firestation : firestations) {
                    FloodDto floodDto = new FloodDto();

                    String address = firestation.getAddress();
                    Integer station = firestation.getStation();

                    floodDto.setAdress(address);
                    floodDto.setStationNumber(station);

                    List<Person> persons = personService.getPersonsByAddress(address);
                    if (persons != null) {
                        for (Person person : persons) {

                            MedicalRecord medicalRecord = medicalRecordService.getMedicalrecordsByFirstNameAndLastName(
                                    person.getFirstName(), person.getLastName()).get(0);

                            if (medicalRecord != null) {
                                FirePersonDto personDto = new FirePersonDto(person, medicalRecord);
                                floodDto.addPerson(personDto);
                            }
                        }
                    }

                    result.add(floodDto);
                }
            }

        }

        return result;

    }

    private List<Integer> stationNumberList(String stationNumbers) {
        List<Integer> stationNumberList = new ArrayList<>();
        if (stationNumbers != null) {
            String[] stationNumberArray = stationNumbers.split(",");
            for (String stationNumber : stationNumberArray) {
                stationNumberList.add(Integer.parseInt(stationNumber));
            }
        }
        return stationNumberList;
    }
}
