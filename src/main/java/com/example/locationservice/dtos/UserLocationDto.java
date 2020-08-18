package com.example.locationservice.dtos;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class UserLocationDto {
    private int uuid;
    private String location;
}