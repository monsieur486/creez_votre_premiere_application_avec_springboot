package com.safetynet.alerts.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateUtilsTest {


    @Test
    void isChild() {
        assertTrue(DateUtils.isChild(DateUtils.getChildBirthdate()));
        assertFalse(DateUtils.isChild(DateUtils.getAdultBirthdate()));
    }

    @Test
    void getAdultBirthdate() {
        assertFalse(DateUtils.isChild(DateUtils.getAdultBirthdate()));
    }

    @Test
    void getChildBirthdate() {
        assertTrue(DateUtils.isChild(DateUtils.getChildBirthdate()));
    }

    @Test
    void getAge() {
        assertEquals(1, DateUtils.getAge(DateUtils.getChildBirthdate()));
    }
}