package io.wing.assessments.planto.drawingprogram.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Table
@Data
@ToString
@Deprecated
public class Canvas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column
    private int width;
    @Column
    private int height;
}
