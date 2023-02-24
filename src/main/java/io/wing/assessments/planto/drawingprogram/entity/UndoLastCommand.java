package io.wing.assessments.planto.drawingprogram.entity;

import lombok.Data;

@Data
public class UndoLastCommand extends BaseCommander {
    public UndoLastCommand(String cmd) {
        setCommand(cmd);
    }

    @Override
    public boolean valid() {
        return super.valid() && "Z".equalsIgnoreCase(this.command.substring(0,1).toUpperCase().trim());
    }
}
