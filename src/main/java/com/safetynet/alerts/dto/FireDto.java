package com.safetynet.alerts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Fire dto.
 */
@Data
@AllArgsConstructor
public class FireDto {
    private List<FirePersonDto> persons;
    private int stationNumber;

    /**
     * Instantiates a new Fire dto.
     */
    public FireDto() {
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
