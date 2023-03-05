package com.nahorniak.closestfarthest.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true, fluent = true)
@NoArgsConstructor
public class Response {
    @JsonProperty("closest")
    Result closest;
    @JsonProperty("farthest")
    Result farthest;
}
