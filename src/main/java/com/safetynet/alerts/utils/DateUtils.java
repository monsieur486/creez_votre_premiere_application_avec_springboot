package com.safetynet.alerts.utils;

import com.safetynet.alerts.configuration.Constants;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

/**
 * The type Date utils.
 */
public class DateUtils {

    private DateUtils() {
    }

    /**
     * Is child boolean.
     *
     * @param birthdate the birthdate
     * @return the boolean
     */
    public static boolean isChild(String birthdate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        LocalDate date1 = LocalDate.parse(birthdate, formatter);
        LocalDate date2 = LocalDate.now();
        TemporalUnit unit = ChronoUnit.YEARS;
        long years = unit.between(date1, date2);
        return years <= Constants.MAX_AGE_CHILD;
    }

    /**
     * Gets adult birthdate.
     *
     * @return the adult birthdate
     */
    public static String getAdultBirthdate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        int maxAge = Constants.MAX_AGE_CHILD + 1;
        LocalDate date1 = LocalDate.now().minusYears(maxAge);
        return date1.format(formatter);
    }

    /**
     * Gets child birthdate.
     *
     * @return the child birthdate
     */
    public static String getChildBirthdate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        LocalDate date1 = LocalDate.now().minusYears(1);
        return date1.format(formatter);
    }

    /**
     * Gets age.
     *
     * @param birthdate the birthdate
     * @return the age
     */
    public static int getAge(String birthdate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        LocalDate date1 = LocalDate.parse(birthdate, formatter);
        LocalDate date2 = LocalDate.now();
        TemporalUnit unit = ChronoUnit.YEARS;
        long years = unit.between(date1, date2);
        return (int) years;
    }
}
