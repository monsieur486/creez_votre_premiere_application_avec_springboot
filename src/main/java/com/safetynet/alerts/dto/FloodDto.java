package com.safetynet.alerts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class FloodDto {
    private String adress;
    private Integer stationNumber;
    private List<FirePersonDto> persons;

    public FloodDto() {
        this.persons = new ArrayList<>();
    }

    public void addPerson(FirePersonDto person) {
        this.persons.add(person);
    }
}
