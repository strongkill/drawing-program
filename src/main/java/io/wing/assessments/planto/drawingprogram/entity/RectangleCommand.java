package io.wing.assessments.planto.drawingprogram.entity;

import lombok.Data;

@Data
public class RectangleCommand  extends BaseCommander{
    public RectangleCommand(String cmd) {
        setCommand(cmd);
    }
}
