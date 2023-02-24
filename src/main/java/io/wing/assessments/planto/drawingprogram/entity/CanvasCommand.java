package io.wing.assessments.planto.drawingprogram.entity;

import io.wing.assessments.planto.drawingprogram.model.Canvas;
import io.wing.assessments.planto.drawingprogram.utils.RegExpTools;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CanvasCommand extends BaseCommander{

    Canvas canvas = new Canvas();
    public CanvasCommand(String cmd) {
        setCommand(cmd);
    }

    @Override
    public boolean valid() {
        String[] cmd = RegExpTools.splitBySpace(getCommand());
        if(cmd.length!=3)
            return false;
        boolean isValid = isNumber(cmd[1]) && isNumber(cmd[2]);
        if(isValid){
            canvas.setWidth(Integer.parseInt( cmd[1]));
            canvas.setHeight(Integer.parseInt(cmd[2]));
        }
        return isValid;
    }

    @Override
    public void excute() {
        for (int i=0;i< canvas.getWidth();i++){
            System.out.print("-");
        }
        System.out.println("");
        for(int i=0;i<canvas.getHeight();i++){
                for (int j = 0; j < canvas.getWidth(); j++) {
                    if(j==0 || j==canvas.getWidth()-1) {
                        System.out.print("|");
                    }else {
                        System.out.print(" ");
                    }
                }
            System.out.println();
        }
        for (int i=0;i< canvas.getWidth();i++){
            System.out.print("-");
        }
        System.out.println();
        super.excute();
    }
}
