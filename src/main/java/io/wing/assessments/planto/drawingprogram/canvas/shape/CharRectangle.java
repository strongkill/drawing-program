package io.wing.assessments.planto.drawingprogram.canvas.shape;

import io.wing.assessments.planto.drawingprogram.canvas.CharCanvas;

import java.awt.*;

public class CharRectangle implements CharShape {

    private static final char REC_CHAR = 'x';
    private final Point upperLeft;
    private final Point lowerRight;

    CharRectangle(Point upperLeft, Point lowerRight) {
        this.upperLeft = upperLeft;
        this.lowerRight = lowerRight;
    }

    @Override
    public void draw(CharCanvas canvasPainter) {
        drawHorizontal(canvasPainter, upperLeft.y); // top line
        drawHorizontal(canvasPainter, lowerRight.y); // bottom line
        drawVertical(canvasPainter, upperLeft.x); // left line
        drawVertical(canvasPainter, lowerRight.x); // right line
    }

    private void drawHorizontal(CharCanvas canvasPainter, int y) {
        for (int i = upperLeft.x; i <= lowerRight.x; i++) {
            canvasPainter.addPoint(i, y, REC_CHAR);
        }
    }

    private void drawVertical(CharCanvas canvasPainter, int x) {
        for (int i = upperLeft.y; i <= lowerRight.y; i++) {
            canvasPainter.addPoint(x, i, REC_CHAR);
        }
    }

}
