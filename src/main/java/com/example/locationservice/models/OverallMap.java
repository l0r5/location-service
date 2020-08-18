package com.example.locationservice.models;

import com.example.locationservice.dtos.UserLocationDto;
import com.example.locationservice.services.OverallMapService;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;


@Data
@Entity
@Component
public class OverallMap implements OverallMapService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @ElementCollection
    private Map<Integer, String> userLocations = new HashMap<>();

    @Override
    public void addUserLocation(UserLocationDto userLocationDto) {
        this.userLocations.put(userLocationDto.getUuid(), userLocationDto.getLocation());
    }

    @Override
    public void updateUserLocation(UserLocationDto userLocationDto) {
        this.userLocations.replace(userLocationDto.getUuid(), userLocationDto.getLocation());
    }
}
