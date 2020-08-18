package com.example.locationservice.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.Map;


@Data
@Builder
public class OverallMapDto {
    private Map<Integer, String> locations;
}