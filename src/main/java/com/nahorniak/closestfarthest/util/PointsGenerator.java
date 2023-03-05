package com.nahorniak.closestfarthest.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.nahorniak.closestfarthest.entity.Point;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class PointsGenerator {

    public static String FILE_NAME = "/points.json";
    public static String PATH = "src/main/resources";

    public static void main(String[] args){

        List<Point> points = generateRandomPoints(100000);
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(PATH + FILE_NAME);

        long startTime = System.nanoTime();
        try {
            mapper.writeValue(file, points);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            new Printer().printElapsedTime(startTime);
        }
    }

    private static List<Point> generateRandomPoints(int n) {
        List<Point> points = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            double x = random.nextDouble() * 100;
            double y = random.nextDouble() * 100;
            Point point = new Point(x, y);
            points.add(point);
        }
        return points;
    }
}
