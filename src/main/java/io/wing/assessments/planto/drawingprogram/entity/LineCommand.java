package io.wing.assessments.planto.drawingprogram.entity;

import io.wing.assessments.planto.drawingprogram.model.Line;
import io.wing.assessments.planto.drawingprogram.utils.RegExpTools;
import lombok.Data;

@Data
public class LineCommand  extends BaseCommander{

    Line line = new Line();
    public LineCommand(String cmd) {
        setCommand(cmd);
    }

    @Override
    public boolean valid() {
        String[] cmd = RegExpTools.splitBySpace(getCommand());
        if(cmd.length!=5)
            return false;
        boolean isValid = isNumber(cmd[1]) && isNumber(cmd[2]) && isNumber(cmd[3]) && isNumber(cmd[4]);
        if(isValid){
            line.setX1(Integer.parseInt(cmd[1]));
            line.setY1(Integer.parseInt(cmd[2]));
            line.setX2(Integer.parseInt(cmd[3]));
            line.setY2(Integer.parseInt(cmd[4]));
        }
        isValid = line.isStraight();
        return isValid;
    }

    @Override
    public void excute() {
        for(int y1 = 0;y1<line.getY1();y1++){

        }

        super.excute();
    }
}
