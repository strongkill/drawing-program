package io.wing.assessments.planto.drawingprogram.model;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
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

    public Command() {

    }

    public boolean canUndo(){
        String firstChar = command.substring(0,1).toUpperCase();
        return "L".equalsIgnoreCase(firstChar) || "R".equalsIgnoreCase(firstChar);
    }
}
