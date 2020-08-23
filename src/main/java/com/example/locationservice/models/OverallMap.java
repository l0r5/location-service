package com.example.locationservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OverallMap {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToMany
    private Collection<UserLocation> userLocations;

    public OverallMap(Collection<UserLocation> userLocations) {
        this.userLocations = userLocations;
    }
}
