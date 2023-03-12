package com.exercise;

import com.exercise.util.Util;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UtilTest {

    @Test
    void testTrue() {

        assertTrue(Util.isNullOrBlank(""));
        assertTrue(Util.isNullOrBlank("  "));
        assertTrue(Util.isNullOrBlank("    "));
        assertTrue(Util.isNullOrBlank(null));
    }

    @Test
    void testFalse() {
        assertFalse(Util.isNullOrBlank("S"));
        assertFalse(Util.isNullOrBlank("SS"));
    }
}
