package com.example.locationservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserLocation {

    private int uuid;

    private String location;
}
