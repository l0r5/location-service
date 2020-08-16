package com.example.locationservice;

public class Location {

    private final long id;
    private final int uuid;
    private final String location;


    public Location(long id, int uuid, String location) {
        this.id = id;
        this.uuid = uuid;
        this.location = location;
    }

    public long getId() {
        return id;
    }

    public long getUuid() {
        return uuid;
    }

    public String getLocation() {
        return location;
    }
}
