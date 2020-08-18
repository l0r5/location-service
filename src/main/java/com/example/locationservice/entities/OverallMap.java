package com.example.locationservice.entities;

import com.example.locationservice.dtos.OverallMapDto;
import com.example.locationservice.dtos.UserLocationDto;
import lombok.Data;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.HashMap;
import java.util.Map;


@Data
@Entity
public class OverallMap {

    @Id
    public long id = 1L;

    @ElementCollection
    private Map<Integer, String> userLocations = new HashMap<>();

    public OverallMapDto getOverallMapDto() {
        return OverallMapDto.builder().id(this.id).locations(this.userLocations).build();
    }

    public void addUserLocation(UserLocationDto userLocationDto) {
        this.userLocations.put(userLocationDto.getUuid(), userLocationDto.getLocation());
    }
}
