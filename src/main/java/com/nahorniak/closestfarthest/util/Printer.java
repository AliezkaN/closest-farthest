package com.nahorniak.closestfarthest.util;

import com.nahorniak.closestfarthest.entity.Response;

import java.util.Objects;
import java.util.concurrent.ForkJoinPool;

public class Printer {

    private void printResults(Response response){
        if (Objects.isNull(response)) throw new RuntimeException("Something went wrong during processing");

        String format = "Points: ( %f, %f ) and ( %f, %f )";

        System.out.println("Closest distance: " + response.closest().distance());
        System.out.println(String.format(format,
                response.closest().first().getX(),
                response.closest().first().getY(),
                response.closest().second().getX(),
                response.closest().second().getY()
        ));
        System.out.println("Farthest distance: " + response.farthest().distance());
        System.out.println(String.format(format,
                response.farthest().first().getX(),
                response.farthest().first().getY(),
                response.farthest().second().getX(),
                response.farthest().second().getY()
        ));
    }

    public void printResults(Response response, ForkJoinPool pool, int pointsNumber){
        System.out.println("There is " + pointsNumber + " points");
        printResults(response);
        int size = pool.getPoolSize();
        System.out.println("Number of threads: " + (size == 0 ? 1 : size));
    }

    public long printElapsedTime(long startTime){
        long endTime = System.nanoTime();
        long elapsedTime = (endTime - startTime) / 1000000;
        System.out.println("Elapsed time: " + elapsedTime + " ms");
        return elapsedTime;
    }
}
