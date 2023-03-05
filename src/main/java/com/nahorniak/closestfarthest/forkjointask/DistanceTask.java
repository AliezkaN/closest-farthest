package com.nahorniak.closestfarthest.forkjointask;

import com.nahorniak.closestfarthest.entity.Point;
import com.nahorniak.closestfarthest.entity.Response;
import com.nahorniak.closestfarthest.entity.Result;
import com.nahorniak.closestfarthest.solver.Solver;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.concurrent.RecursiveTask;

@RequiredArgsConstructor
public class DistanceTask extends RecursiveTask<Response> {

    private static final int SEQUENTIAL_THRESHOLD = 100;
    private final List<Point> points;
    private final Integer lowIndex;
    private final Integer highIndex;

    @Override
    protected Response compute() {

        if (highIndex - lowIndex <= SEQUENTIAL_THRESHOLD) {
            return Solver.solve(lowIndex, highIndex, points);
        }

        Integer midIndex = lowIndex + (highIndex - lowIndex) / 2;
        DistanceTask left = new DistanceTask(points, lowIndex, midIndex);
        DistanceTask right = new DistanceTask(points, midIndex, highIndex);
        left.fork();
        right.fork();
        Response rightResponse = right.join();
        Response leftResponse = left.join();
        return generateResult(leftResponse, rightResponse);
    }

    private Response generateResult(Response leftResponse, Response rightResponse) {

        Result closest = leftResponse.closest().distance() < rightResponse.closest().distance()
                ? leftResponse.closest()
                : rightResponse.closest();

        Result farthest = leftResponse.farthest().distance() > rightResponse.farthest().distance()
                ? leftResponse.farthest()
                : rightResponse.farthest();

        return new Response()
                .closest(closest)
                .farthest(farthest);

    }


}
