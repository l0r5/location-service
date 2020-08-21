package com.example.locationservice.dtos;

import com.example.locationservice.models.UserLocation;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class UserLocationDto {
    private int uuid;
    private String location;

    public static UserLocationDto from(UserLocation userLocation) {
        return UserLocationDto.builder().uuid(userLocation.getUuid()).location(userLocation.getLocation()).build();
    }

    public UserLocation toUserLocation() {
        return UserLocation.builder().uuid(getUuid()).location(getLocation()).build();
    }
}