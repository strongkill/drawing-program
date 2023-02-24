package io.wing.assessments.planto.drawingprogram.canvas.shape;

import io.wing.assessments.planto.drawingprogram.canvas.CharCanvas;

import java.awt.*;

public class CharLine implements CharShape {

    private static final char LINE_CHAR = 'x';
    private final Point start;
    private final Point end;

    CharLine(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void draw(CharCanvas canvasPainter) {
        if (start.x == end.x) { // vertical line
            drawVertical(canvasPainter);
        } else if (start.y == end.y) { // horizontal line
            drawHorizontal(canvasPainter);
        } else {
            throw new IllegalArgumentException("Only vertical and horizontal lines are supported");
        }
    }

    private void drawHorizontal(CharCanvas canvasPainter) {
        for (int i = start.x; i <= end.x; i++) {
            canvasPainter.addPoint(i, start.y, LINE_CHAR);
        }
    }

    private void drawVertical(CharCanvas canvasPainter) {
        for (int i = start.y; i <= end.y; i++) {
            canvasPainter.addPoint(start.x, i, LINE_CHAR);
        }
    }

}
