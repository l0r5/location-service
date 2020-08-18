package com.example.locationservice.dtos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserLocationDtoTest {

    private final UserLocationDto testee = UserLocationDto.builder()
            .uuid(1)
            .location("123")
            .build();

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
        assertEquals(new UserLocationDto(1, "123"), testee);
    }

    @Test
    void canEqual() {
        assertTrue(new UserLocationDto(1, "123").canEqual(testee));
    }

    @Test
    void testHashCode() {
        assertEquals(new UserLocationDto(1, "123").hashCode(), testee.hashCode());
    }

    @Test
    void testToString() {
        assertEquals(new UserLocationDto(1, "123").toString(), testee.toString());
    }
}