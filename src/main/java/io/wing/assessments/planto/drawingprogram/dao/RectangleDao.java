package io.wing.assessments.planto.drawingprogram.dao;

import io.wing.assessments.planto.drawingprogram.model.Rectangle;
import org.springframework.data.repository.CrudRepository;

@Deprecated
public interface RectangleDao extends CrudRepository<Rectangle,Integer> {
}
