package com.example.locationservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OverallMap {

    private long id;

    private Collection<UserLocation> userLocations;

    public OverallMap(Collection<UserLocation> userLocations) {
        this.userLocations = userLocations;
    }
}
