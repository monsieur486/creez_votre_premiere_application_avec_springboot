package com.safetynet.alerts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Flood dto.
 */
@Data
@AllArgsConstructor
public class FloodDto {
    private String adress;
    private Integer stationNumber;
    private List<FirePersonDto> persons;

    /**
     * Instantiates a new Flood dto.
     */
    public FloodDto() {
        this.persons = new ArrayList<>();
    }

    /**
     * Add person.
     *
     * @param person the person
     */
    public void addPerson(FirePersonDto person) {
        this.persons.add(person);
    }
}
