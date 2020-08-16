package com.example.locationservice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LocationTest {

    private final Location testee = new Location(1, 1, "123");

    @Test
    void getId() {
        assertEquals(1, testee.getId());
    }

    @Test
    void getUuid() {
        assertEquals(1, testee.getUuid());
    }

    @Test
    void getLocation() {
        assertEquals("123", testee.getLocation());
    }

    @Test
    void testEquals() {
        assertEquals(new Location(1, 1, "123"), testee);
    }

    @Test
    void canEqual() {
        assertTrue(new Location(1, 1, "123").canEqual(testee));
    }

    @Test
    void testHashCode() {
        assertEquals(new Location(1, 1, "123").hashCode(), testee.hashCode());
    }

    @Test
    void testToString() {
        assertEquals(new Location(1, 1, "123").toString(), testee.toString());
    }
}