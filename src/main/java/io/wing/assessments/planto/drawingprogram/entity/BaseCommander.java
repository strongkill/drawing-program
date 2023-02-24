package io.wing.assessments.planto.drawingprogram.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class BaseCommander implements Serializable {
    protected String command;
    public boolean valid(){
        return command!=null && command.length()>0;
    }

    public static void err(String command){
        System.out.println("ERROR: Unknown Command: " + command );
        System.out.println("Type H for Help!");
    }

    public void excute(){

    }

    public boolean isNumber(String strNumber){
        try {
            int number = Integer.parseInt(strNumber);
            return number>0;
        }catch (Exception e){
            return false;
        }
    }
}
