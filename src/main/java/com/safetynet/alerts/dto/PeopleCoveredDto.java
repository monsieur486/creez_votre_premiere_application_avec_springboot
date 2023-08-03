package com.safetynet.alerts.dto;

import com.safetynet.alerts.model.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeopleCoveredDto {
    private List<PersonCoveredDto> persons;
    private int adults;
    private int children;
}