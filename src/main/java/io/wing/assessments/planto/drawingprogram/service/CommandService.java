package io.wing.assessments.planto.drawingprogram.service;


import io.wing.assessments.planto.drawingprogram.dao.CommandDao;
import io.wing.assessments.planto.drawingprogram.model.Command;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommandService {
    @Resource
    CommandDao commandDao;

    public int getCurrentCommandId() {
        return currentCommandId;
    }

    public void setCurrentCommandId(int currentCommandId) {
        this.currentCommandId = currentCommandId;
    }

    int currentCommandId=0;

    public int getLastUndoCommandId() {
        return lastUndoCommandId;
    }

    public void setLastUndoCommandId(int lastUndoCommandId) {
        this.lastUndoCommandId = lastUndoCommandId;
    }

    int lastUndoCommandId = 0;

    public void saveCommand(Command cmd){
        commandDao.save(cmd);
        if(cmd.canUndo())
            setCurrentCommandId(cmd.getId());
    }

    public Command getLastCommand(){
        if(getCurrentCommandId()>0){
            return getCommand(getCurrentCommandId());
        }
        return null;
    }
    public Command getLastUndoCommand(){
        if(getLastUndoCommandId()>0){
            return getCommand(getLastUndoCommandId());
        }
        return null;
    }

    public Iterable<Command> getAllCommand() {
        return commandDao.findAll();
    }

    public Command getCommand(Integer id){
        return commandDao.findById(id).get();
    }
}
