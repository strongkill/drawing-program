package io.wing.assessments.planto.drawingprogram.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Table
@Data
@ToString
@Deprecated
public class Line {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column
    private int x1;
    @Column
    private int y1;
    @Column
    private int x2;
    @Column
    private int y2;

    public boolean isStraight(){
        return x1==x2 || y1==y2;
    }
}
