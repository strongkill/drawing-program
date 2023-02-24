package io.wing.assessments.planto.drawingprogram.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Table
@Data
@ToString
public class Command {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    public Command(String cmd){
        this.command = cmd;
    }

    @Column
    String command;

    @Column
    Integer delFlag = 0;

    boolean persistFlag = true;

    public Command() {

    }
    public boolean canRedo(){
        String firstChar = command.substring(0,1).toUpperCase();
        return "C".equalsIgnoreCase(firstChar) || "L".equalsIgnoreCase(firstChar) || "R".equalsIgnoreCase(firstChar);
    }
    public boolean canUndo(){
        String firstChar = command.substring(0,1).toUpperCase();
        return "L".equalsIgnoreCase(firstChar) || "R".equalsIgnoreCase(firstChar);
    }
}
