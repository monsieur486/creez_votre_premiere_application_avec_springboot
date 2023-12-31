package com.safetynet.alerts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Child alert person dto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChildAlertPersonDto {
    private String firstName;
    private String lastName;
    private int age;
}
