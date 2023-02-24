package io.wing.assessments.planto.drawingprogram.utils;

import io.wing.assessments.planto.drawingprogram.entity.*;

public class RegExpTools {

    public static String[] splitBySpace(String spaceStr) {
        return spaceStr.split(" ");
    }

    public static BaseCommander identifyCommand(String cmdStr){
        BaseCommander ret;
        String firstChar = cmdStr.substring(0,1).toUpperCase();
        ret = switch (firstChar) {
            case "C" -> new CanvasCommand(cmdStr);
            case "L" -> new LineCommand(cmdStr);
            case "R" -> new RectangleCommand(cmdStr);
            case "Z" -> new UndoLastCommand(cmdStr);
            case "X" -> new RedoLastUndoCommand(cmdStr);
            case "H" -> new HelpCommand(cmdStr);
            case "Q" -> new QuitCommand(cmdStr);
            default -> null;
        };
        return ret;
    }
}
