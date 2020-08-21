package com.example.locationservice.dtos;

import com.example.locationservice.models.OverallMap;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
@Builder
public class OverallMapDto {

    private List<UserLocationDto> locations;

    public static OverallMapDto from(OverallMap overallMap) {
        List<UserLocationDto> userLocationDtos = new ArrayList<>();
        overallMap.getUserLocations().forEach(userLocation -> {
            userLocationDtos.add(UserLocationDto.from(userLocation));
        });
        return OverallMapDto.builder().locations(userLocationDtos).build();
    }
}