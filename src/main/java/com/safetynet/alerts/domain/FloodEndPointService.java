package com.safetynet.alerts.domain;

import com.safetynet.alerts.dto.FirePersonDto;
import com.safetynet.alerts.dto.FloodDto;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.jsonfile.JsonFileFirestationService;
import com.safetynet.alerts.service.jsonfile.JsonFileMedicalRecordService;
import com.safetynet.alerts.service.jsonfile.JsonFilePersonService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FloodEndPointService {

    private final JsonFilePersonService jsonFilePersonService;

    private final JsonFileFirestationService jsonFileFirestationService;

    private final JsonFileMedicalRecordService jsonFileMedicalRecordService;

    public FloodEndPointService(JsonFilePersonService jsonFilePersonService, JsonFileFirestationService jsonFileFirestationService, JsonFileMedicalRecordService jsonFileMedicalRecordService) {
        this.jsonFilePersonService = jsonFilePersonService;
        this.jsonFileFirestationService = jsonFileFirestationService;
        this.jsonFileMedicalRecordService = jsonFileMedicalRecordService;
    }

    public List<FloodDto> getPersonListByStationNumberList(String stationNumbers) {
        List<FloodDto> result = new ArrayList<>();
        for (Integer stationNumber : stationNumberList(stationNumbers)) {
            List<Firestation> firestations = jsonFileFirestationService.getFirestationsByStation(stationNumber);
            if (firestations != null && !firestations.isEmpty()) {
                for (Firestation firestation : firestations) {
                    FloodDto floodDto = new FloodDto();

                    String address = firestation.getAddress();
                    Integer station = firestation.getStation();

                    floodDto.setAdress(address);
                    floodDto.setStationNumber(station);

                    List<Person> persons = jsonFilePersonService.getPersonsByAddress(address);
                    if (persons != null) {
                        for (Person person : persons) {

                            MedicalRecord medicalRecord = jsonFileMedicalRecordService.getMedicalrecordsByFirstNameAndLastName(
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
