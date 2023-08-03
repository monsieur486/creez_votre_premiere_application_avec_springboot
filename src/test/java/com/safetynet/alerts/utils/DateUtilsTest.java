package com.safetynet.alerts.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateUtilsTest {


    @Test
    void isChild() {
        assertTrue(DateUtils.isChild("01/01/2010"));
        assertFalse(DateUtils.isChild("01/01/2000"));
    }
}