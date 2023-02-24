package io.wing.assessments.planto.drawingprogram.dao;

import io.wing.assessments.planto.drawingprogram.model.Line;
import org.springframework.data.repository.CrudRepository;
@Deprecated
public interface LineDao extends CrudRepository<Line,Integer> {
}
