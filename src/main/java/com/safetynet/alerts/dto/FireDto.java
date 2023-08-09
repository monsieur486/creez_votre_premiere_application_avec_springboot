package com.safetynet.alerts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class FireDto {
    private List<FirePersonDto> persons;
    private int stationNumber;

    public FireDto() {
        this.persons = new ArrayList<>();
    }

    public void addPerson(FirePersonDto person) {
        this.persons.add(person);
    }

}
