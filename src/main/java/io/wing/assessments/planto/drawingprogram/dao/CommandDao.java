package io.wing.assessments.planto.drawingprogram.dao;

import io.wing.assessments.planto.drawingprogram.model.Command;
import org.springframework.data.repository.CrudRepository;

public interface CommandDao extends CrudRepository<Command,Integer> {
}
