package io.wing.assessments.planto.drawingprogram.entity;

import lombok.Data;

@Data
public class HelpCommand  extends BaseCommander{
    public HelpCommand(String cmd) {
        setCommand(cmd);
    }

    @Override
    public boolean valid() {
        return true;
    }

    @Override
    public void excute() {
        printHelp();
    }

    public static void printHelp(){
        System.out.println("Command 		Description\n"+
                "C w h           To create a new canvas of width w and height h.\n"+
                "L x1 y1 x2 y2   To create a new line from (x1,y1) to (x2,y2). Currently only\n"+
                "                horizontal or vertical lines are supported. Horizontal and vertical lines\n"+
                "                will be drawn using the 'x' character.\n"+
                "R x1 y1 x2 y2   To create a new rectangle, whose upper left corner is (x1,y1) and\n"+
                "                lower right corner is (x2,y2). Horizontal and vertical lines will be drawn\n"+
                "                using the 'x' character.\n"+
                "Z               To undo the last command.\n"+
                "X               To redo the last undo command.\n"+
                "V               To Display command history.\n"+
                "H               To Display this Help.\n"+
                "Q               To quit the program.\n");
    }
}
