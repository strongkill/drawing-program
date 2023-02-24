package io.wing.assessments.planto.drawingprogram.canvas;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class CharCanvas extends JPanel {

    private static final int FONT_SIZE = 20;
    private char[][] chars;
    private int width;
    private int height;

    CharCanvas(int width, int height) {
        this.width = width;
        this.height = height;
    }

    void initialise(int w, int h) {
        this.width = w;
        this.height = h;
        initialiseChars();
        setCanvasSize();
    }

    public void addPoint(int x, int y, char charValue) {
        chars[y][x] = charValue;
    }

    private void initialiseChars() {
        chars = new char[height][width];
        for (int i = 0; i < height; i++) {
            Arrays.fill(chars[i], ' ');
        }
    }

    @Override
    protected void paintComponent(Graphics g) { // called back via repaint()
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(new Font("Arial", Font.PLAIN, FONT_SIZE));
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                g2d.drawString(String.valueOf(chars[i][j]), j * FONT_SIZE, (i + 1) * FONT_SIZE);
            }
        }
    }

    private void setCanvasSize() {
        setPreferredSize(new Dimension(width * FONT_SIZE, height * FONT_SIZE));
    }

}