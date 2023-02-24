package io.wing.assessments.planto.drawingprogram.dao;

import io.wing.assessments.planto.drawingprogram.model.Command;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Wing K.Y
 *
 *
 */
public interface CommandDao extends CrudRepository<Command,Integer> {

}
