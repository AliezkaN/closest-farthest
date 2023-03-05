package com.nahorniak.closestfarthest.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(fluent = true)
public class Result {
    @JsonProperty("distance")
    private double distance;
    @JsonProperty("first")
    private Point first;
    @JsonProperty("second")
    private Point second;
}
