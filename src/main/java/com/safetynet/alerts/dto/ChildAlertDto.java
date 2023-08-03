package com.safetynet.alerts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class ChildAlertDto {
    private List<ChildAlertPersonDto> children;
    private List<ChildAlertPersonDto> adults;

    public ChildAlertDto() {
        this.children = new ArrayList<>();
        this.adults = new ArrayList<>();
    }

    public void addChild(ChildAlertPersonDto child) {
        this.children.add(child);
    }

    public void addAdult(ChildAlertPersonDto adult) {
        this.adults.add(adult);
    }
}
