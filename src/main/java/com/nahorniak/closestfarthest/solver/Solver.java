package com.nahorniak.closestfarthest.solver;

import com.nahorniak.closestfarthest.entity.Point;
import com.nahorniak.closestfarthest.entity.Response;
import com.nahorniak.closestfarthest.entity.Result;

import java.util.List;

public class Solver {

    public static Response solve(int lowIndex, int highIndex, List<Point> points) {
        System.out.println(Thread.currentThread().getName());
        double minDistance = Double.MAX_VALUE;
        double maxDistance = Double.MIN_VALUE;
        Point minPoint1 = null, minPoint2 = null, maxPoint1 = null, maxPoint2 = null;
        for (int i = lowIndex; i < highIndex; i++) {
            Point first = points.get(i);
            for (Point second : points){

                if (first.equals(second)) continue;

                double distance = distance(first,second);
                if (distance < minDistance) {
                    minDistance = distance;
                    minPoint1 = first;
                    minPoint2 = second;
                }
                if (distance > maxDistance) {
                    maxDistance = distance;
                    maxPoint1 = first;
                    maxPoint2 = second;
                }

            }
        }
        return new Response()
                .closest(new Result(minDistance, minPoint1, minPoint2))
                .farthest(new Result(maxDistance, maxPoint1, maxPoint2));
    }

    public static double distance(Point p1, Point p2) {
        double dx = p1.getX() - p2.getX();
        double dy = p1.getY() - p2.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }
}
