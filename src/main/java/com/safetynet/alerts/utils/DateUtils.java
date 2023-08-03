package com.safetynet.alerts.utils;

import com.safetynet.alerts.configuration.Constants;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

public class DateUtils {

    private DateUtils() {
    }

    public static boolean isChild(String birthdate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        LocalDate date1 = LocalDate.parse(birthdate, formatter);
        LocalDate date2 = LocalDate.now();
        TemporalUnit unit = ChronoUnit.YEARS;
        long years = unit.between(date1, date2);
        if (years <= Constants.MAX_AGE_CHILD) {
            return true;
        } else {
            return false;
        }
    }
}
