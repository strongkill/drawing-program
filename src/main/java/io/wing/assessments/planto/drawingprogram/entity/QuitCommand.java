package io.wing.assessments.planto.drawingprogram.entity;

import lombok.Data;

/**
 * @author Wing K.Y
 */
@Data
public class QuitCommand extends BaseCommander {
    public QuitCommand(String cmd) {
        setCommand(cmd);
    }
    @Override
    public boolean valid(){
        return true;
    }
    @Override
    public void excute() {
        System.out.println("Bye.");
        System.exit(0);
    }
}
