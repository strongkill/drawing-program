package io.wing.assessments.planto.drawingprogram.canvas;


import io.wing.assessments.planto.drawingprogram.canvas.shape.CharShape;

import javax.swing.*;
import java.awt.*;

class CanvasComponent extends JFrame {

    private CharCanvas canvas;

    CanvasComponent() throws HeadlessException {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Canvas Paint Exercise");
        setLocationRelativeTo(null);
    }

    void initialise(int width, int height) {
        if (canvas == null) {
            canvas = new CharCanvas(width, height);
            add(canvas);
            pack();
            setVisible(true);
        }

        canvas.initialise(width, height);
        pack();
    }

    void updateCanvas(CharShape shape) {
        shape.draw(canvas);
        repaint();
    }
}
