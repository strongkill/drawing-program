package io.wing.assessments.planto.drawingprogram;

import io.wing.assessments.planto.drawingprogram.model.Canvas;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCanvasEntity {

    @Test
    public void whenGet_ThenSet() {
        Canvas canvas = new Canvas();
        canvas.setHeight(100);
        canvas.setWidth(100);
        canvas.setId(1);
        Integer expected =1;
        Integer actual = canvas.getId();
        assertEquals(expected,actual);
        assertEquals(100,canvas.getWidth());
        assertEquals(100,canvas.getHeight());
    }

}
