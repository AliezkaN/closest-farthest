package com.nahorniak.closestfarthest;

import com.nahorniak.closestfarthest.entity.Point;
import com.nahorniak.closestfarthest.entity.Response;
import com.nahorniak.closestfarthest.forkjointask.DistanceTask;
import com.nahorniak.closestfarthest.solver.Solver;
import com.nahorniak.closestfarthest.util.PointReader;
import com.nahorniak.closestfarthest.util.Printer;
import com.nahorniak.closestfarthest.util.ResponseWriter;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ForkJoinPool;

public class PointApplication {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("ТРСПО - лаб 1 - Нагорняк");
        System.out.println("Data Source type : JSON -> 1 , MEMORY -> 2");
        System.out.print("Your choice :");

        int dataSourceType = scanner.nextInt();

        List<Point> points = dataSourceType == 1
                ? PointReader.readPointsFromJson()
                : Arrays.asList(
                new Point(1,2),
                new Point(2,1),
                new Point(4,1),
                new Point(5,12),
                new Point(111,2),
                new Point(22,33),
                new Point(51,6),
                new Point(6,7),
                new Point(7,9),
                new Point(15,5),
                new Point(5,2));


        System.out.println("Executing type :  SINGLE_THREAD -> 1 , MULTI_THREAD -> 2");
        System.out.print("Your choice :");
        Integer executionType = scanner.nextInt();

        long startTime = System.nanoTime();
        Response response = null;
        ForkJoinPool pool = new ForkJoinPool();
        try {

            response = executionType == 1
                    ? Solver.solve(0,points.size() , points)
                    : pool.invoke(new DistanceTask(points,0,points.size()));

        }finally {
            Printer printer = new Printer();
            long elapsedTime = printer.printElapsedTime(startTime);
            new Printer().printResults(response,pool, points.size());
            ResponseWriter.write(response,dataSourceType, executionType, elapsedTime);
        }
    }
}
