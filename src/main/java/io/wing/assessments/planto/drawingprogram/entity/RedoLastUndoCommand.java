package io.wing.assessments.planto.drawingprogram.entity;

import lombok.Data;

@Data
public class RedoLastUndoCommand extends BaseCommander {
    public RedoLastUndoCommand(String cmd) {
        setCommand(cmd);
    }

    @Override
    public boolean valid() {
        return super.valid() && "X".equalsIgnoreCase(this.command.substring(0,1).toUpperCase().trim());
    }
}
