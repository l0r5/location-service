package com.example.locationservice;

import lombok.Data;


@Data
public class Location {
    private final long id;
    private final int uuid;
    private final String location;
}
