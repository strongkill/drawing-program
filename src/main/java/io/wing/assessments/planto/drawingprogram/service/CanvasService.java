package io.wing.assessments.planto.drawingprogram.service;

import io.wing.assessments.planto.drawingprogram.dao.CanvasDao;
import io.wing.assessments.planto.drawingprogram.model.Canvas;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Deprecated
@Service
public class CanvasService {
    @Resource
    CanvasDao canvasDao;

    public Optional<Canvas> getCanvas(Integer id){
        return canvasDao.findById(id);
    }
    public Iterable<Canvas> getAllCanvas(){
        return canvasDao.findAll();
    }
}
