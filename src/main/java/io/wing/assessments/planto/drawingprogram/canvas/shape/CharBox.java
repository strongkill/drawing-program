package io.wing.assessments.planto.drawingprogram.canvas.shape;

import io.wing.assessments.planto.drawingprogram.canvas.CharCanvas;
public class CharBox implements CharShape {

    private static final char WIDTH_CHAR = '-';
    private static final char HEIGHT_CHAR = '|';
    private final int width;
    private final int height;

    CharBox(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(CharCanvas canvasPainter) {
        updateVerticalBorder(canvasPainter, 0);// left border
        updateVerticalBorder(canvasPainter, width - 1); //right border starts from 0
        updateHorizontalBorder(canvasPainter, 0); // top border
        updateHorizontalBorder(canvasPainter, height - 1); // bottom border starts from 0
    }

    private void updateHorizontalBorder(CharCanvas canvasPainter, int y) {
        for (int i = 0; i < width ; i++) {
            canvasPainter.addPoint(i, y, WIDTH_CHAR);
        }
    }

    private void updateVerticalBorder(CharCanvas canvasPainter, int x) {
        for (int i = 0; i < height; i++) {
            canvasPainter.addPoint(x, i, HEIGHT_CHAR);
        }
    }

}
