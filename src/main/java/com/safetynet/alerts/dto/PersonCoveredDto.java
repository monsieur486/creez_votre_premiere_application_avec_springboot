package com.safetynet.alerts.dto;

import com.safetynet.alerts.model.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Person covered dto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonCoveredDto {
    private String firstName;
    private String lastName;
    private String address;
    private String phone;

    /**
     * Instantiates a new Person covered dto.
     *
     * @param person the person
     */
    public PersonCoveredDto(Person person) {
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.address = person.getAddress();
        this.phone = person.getPhone();
    }
}
