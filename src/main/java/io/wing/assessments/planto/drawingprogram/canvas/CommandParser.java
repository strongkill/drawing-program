package io.wing.assessments.planto.drawingprogram.canvas;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class CommandParser {
    private static final String DELIMITER = " ";
    private static final String LINE_REGEX = "[Ll]\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)";
    private static final String REC_REGEX = "[Rr]\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)";
    private static final String CANVAS_REGEX = "[Cc]\\s+(\\d+)\\s+(\\d+)";

    static Optional<String> validateLine(String command, int widthMax, int heightMax) {
        String errorMessage =
                invalidPattern(command, LINE_REGEX, "Line", "L x0 y0 x1 y1")
                        .orElseGet(() -> invalidDiemension(command)
                                .orElseGet(() -> invalidCornerOrder(command)
                                        .orElseGet(() -> invalidDirection(command)
                                                .orElseGet(() -> outsideCanvas(command, widthMax, heightMax)
                                                        .orElseGet(() -> null)))));

        return Optional.ofNullable(errorMessage);
    }

    static Optional<String> validateRectangle(String command, int widthMax, int heightMax) {
        String errorMessage =
                invalidPattern(command, REC_REGEX, "Rectangle", "R x0 y0 x1 y1")
                        .orElseGet(() -> invalidDiemension(command)
                                .orElseGet(() -> invalidCornerOrder(command)
                                        .orElseGet(() -> invalidRecSize(command)
                                                .orElseGet(() -> outsideCanvas(command, widthMax, heightMax)
                                                        .orElseGet(() -> null)))));

        return Optional.ofNullable(errorMessage);
    }

    static Optional<String> validateCanvas(String command) {
        String errorMessage =
                invalidPattern(command, CANVAS_REGEX, "Canvas", "C w h")
                        .orElseGet(() -> invalidDiemension(command)
                                .orElseGet(() -> null));

        return Optional.ofNullable(errorMessage);
    }

    private static Optional<String> invalidDirection(String command) {
        List<Integer> coordinates = getCoordinates(command);
        boolean invalidDimensions =
                coordinates.get(0).compareTo(coordinates.get(2)) != 0 && // x should be same or
                        coordinates.get(1).compareTo(coordinates.get(3)) != 0; // y should be same
        String message = "Lines should be horizontal or vertical";

        return Optional.ofNullable(invalidDimensions ? message : null);
    }

    private static Optional<String> invalidPattern(String command,
                                                   String pattern,
                                                   String shapeName,
                                                   String examplePattern) {

        boolean invalidPattern = !command.matches(pattern);
        String message = String.format("Invalid %s command pattern: %s i.e. [%s]", shapeName, command, examplePattern);

        return Optional.ofNullable(invalidPattern ? message : null);
    }

    private static Optional<String> invalidDiemension(String command) {
        return getCoordinates(command)
                .stream()
                .filter(value -> value <= 0)
                .map(value -> "Negative or zero dimensions entered: " + command)
                .findAny();
    }

    private static Optional<String> invalidCornerOrder(String command) {
        List<Integer> coordinates = getCoordinates(command);
        boolean invalidOrder =
                coordinates.get(0) > coordinates.get(2) || // left x should be before right x
                        coordinates.get(1) > coordinates.get(3); // upper y should be before lower y
        String message = "Please specify the top left corner first.";

        return Optional.ofNullable(invalidOrder ? message : null);
    }

    private static Optional<String> invalidRecSize(String command) {
        List<Integer> coordinates = getCoordinates(command);
        boolean invalidOrder =
                coordinates.get(0).compareTo(coordinates.get(2)) == 0 || // left x should not be same as right x
                        coordinates.get(1).compareTo(coordinates.get(3)) == 0; // upper y should  not be same as lower y
        String message = "Rectangle with no width or height";

        return Optional.ofNullable(invalidOrder ? message : null);
    }

    private static Optional<String> outsideCanvas(String command, int widthMax, int heightMax) {
        List<Integer> coordinates = getCoordinates(command);
        boolean outsideCanvas =
                coordinates.get(2) > widthMax ||
                        coordinates.get(3) > heightMax;
        String message = "Specified dimensions fall outside canvas area: [" + widthMax + ":" + heightMax + "]";

        return Optional.ofNullable(outsideCanvas ? message : null);
    }

    static List<Integer> getCoordinates(String command) {
        return Arrays.stream(command.substring(1).trim().split(DELIMITER))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }

}
