package io.wing.assessments.planto.drawingprogram.entity;

import lombok.Data;

@Data
public class ShowHistoryCommand extends BaseCommander{
    public ShowHistoryCommand(String cmd) {
        this.command = cmd;
    }

    @Override
    public boolean valid() {
        return super.valid() && "V".equalsIgnoreCase(this.command.substring(0,1).toUpperCase().trim());
    }
}
