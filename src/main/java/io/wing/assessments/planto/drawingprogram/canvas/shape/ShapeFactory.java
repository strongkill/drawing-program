package io.wing.assessments.planto.drawingprogram.canvas.shape;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ShapeFactory {

    public static CharLine createLine(String command) {
        List<Integer> coordinates = getCoordinates(command);
        Point start = new Point(coordinates.get(0), coordinates.get(1));
        Point end = new Point(coordinates.get(2), coordinates.get(3));
        return new CharLine(start, end);
    }

    public static CharRectangle createRectangle(String command) {
        List<Integer> coordinates = getCoordinates(command);
        Point upperLeft = new Point(coordinates.get(0), coordinates.get(1));
        Point lowerRight = new Point(coordinates.get(2), coordinates.get(3));
        return new CharRectangle(upperLeft, lowerRight);
    }

    public static CharBox createBox(int width, int height) {
        return new CharBox(width, height);
    }

    private static List<Integer> getCoordinates(String command) {
        return Arrays.stream(command.substring(1).trim().split(" "))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }

}
