package com.example.locationservice.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.Map;


@Data
@Builder
public class UserLocationDto {
    private int uuid;
    private String location;

    public static UserLocationDto from(Map<Integer, String> userLocation) {
        Map.Entry<Integer, String> entry = userLocation.entrySet().iterator().next();
        return UserLocationDto.builder().uuid(entry.getKey()).location(entry.getValue()).build();
    }
}