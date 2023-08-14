package com.safetynet.alerts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Child alert dto.
 */
@Data
@AllArgsConstructor
public class ChildAlertDto {
    private List<ChildAlertPersonDto> children;
    private List<ChildAlertPersonDto> adults;

    /**
     * Instantiates a new Child alert dto.
     */
    public ChildAlertDto() {
        this.children = new ArrayList<>();
        this.adults = new ArrayList<>();
    }

    /**
     * Add child.
     *
     * @param child the child
     */
    public void addChild(ChildAlertPersonDto child) {
        this.children.add(child);
    }

    /**
     * Add adult.
     *
     * @param adult the adult
     */
    public void addAdult(ChildAlertPersonDto adult) {
        this.adults.add(adult);
    }
}
