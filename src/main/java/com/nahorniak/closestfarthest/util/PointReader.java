package com.nahorniak.closestfarthest.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nahorniak.closestfarthest.entity.Point;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static com.nahorniak.closestfarthest.util.PointsGenerator.FILE_NAME;

public class PointReader {
    public static List<Point> readPointsFromJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        InputStream inputStream = PointReader.class.getResourceAsStream(FILE_NAME);
        return mapper.readValue(inputStream, new TypeReference<List<Point>>(){});
    }
}