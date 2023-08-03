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

    public static String getAdultBirthdate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        int maxAge = Constants.MAX_AGE_CHILD + 1;
        LocalDate date1 = LocalDate.now().minusYears(maxAge);
        return date1.format(formatter);
    }

    public static String getChildBirthdate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        LocalDate date1 = LocalDate.now().minusYears( 1);
        return date1.format(formatter);
    }

    public static int getAge(String birthdate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        LocalDate date1 = LocalDate.parse(birthdate, formatter);
        LocalDate date2 = LocalDate.now();
        TemporalUnit unit = ChronoUnit.YEARS;
        long years = unit.between(date1, date2);
        return (int) years;
    }
}
