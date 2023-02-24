package io.wing.assessments.planto.drawingprogram.canvas;


import io.wing.assessments.planto.drawingprogram.canvas.shape.CharBox;
import io.wing.assessments.planto.drawingprogram.canvas.shape.CharLine;
import io.wing.assessments.planto.drawingprogram.canvas.shape.CharRectangle;
import io.wing.assessments.planto.drawingprogram.canvas.shape.ShapeFactory;
import io.wing.assessments.planto.drawingprogram.entity.*;
import io.wing.assessments.planto.drawingprogram.model.Command;
import io.wing.assessments.planto.drawingprogram.service.CommandService;

import javax.swing.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static io.wing.assessments.planto.drawingprogram.canvas.CommandParser.getCoordinates;


public class CanvasExercise {

    CommandService commandService;
    private final CanvasComponent canvasComponent;
    private final Map<String, Consumer<String>> shapeMapping;
    private int currentWidth;
    private int currentHeight;

    boolean persistFlag = true;

    public CanvasExercise(){
        currentWidth = 0;
        currentHeight = 0;
        canvasComponent = new CanvasComponent();
        shapeMapping = new HashMap<String, Consumer<String>>() {{
            put("L", (command -> drawLine(command)));
            put("R", (command -> drawRectangle(command)));
            put("C", (command -> drawCanvas(command)));
            put("Z", (command -> undoLastDraw(command)));
            put("X",(command -> redoLastUndoDraw(command)));
            put("H", (command -> helpMessage(command)));
            put("V", (command -> showHistoryCommand(command)));
            put("Q",(command -> Quit(command)));
        }};
        awaitCommand();
    }

    public CanvasExercise(CommandService commandService) {
        this.commandService = commandService;
        currentWidth = 0;
        currentHeight = 0;
        canvasComponent = new CanvasComponent();
        shapeMapping = new HashMap<String, Consumer<String>>() {{
            put("L", (command -> drawLine(command)));
            put("R", (command -> drawRectangle(command)));
            put("C", (command -> drawCanvas(command)));
            put("Z", (command -> undoLastDraw(command)));
            put("X",(command -> redoLastUndoDraw(command)));
            put("H", (command -> helpMessage(command)));
            put("V", (command -> showHistoryCommand(command)));
            put("Q",(command -> Quit(command)));
        }};
        awaitCommand();
    }

    public static void main(String[] args) {
        new CanvasExercise();
    }

    private void awaitCommand() {
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            System.out.print("Enter your Command: ");
            input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                executeCommand(input);
            }
        }
    }

    public void executeCommand(String command) {
        String firstChar = command.substring(0, 1).toUpperCase();
        Optional.ofNullable(shapeMapping.get(firstChar))
                .orElseGet(this::invalidLetterCommand)
                .accept(command);
    }

    private void showHistoryCommand(String command){
        ShowHistoryCommand showHistoryCommand = new ShowHistoryCommand(command);
        if(showHistoryCommand.valid()){
            Iterable<Command> historyCommand = commandService.getAllCommand();
            if(historyCommand!=null && historyCommand.iterator().hasNext()){
                for (Command cmd:commandService.getAllCommand()) {
                    System.out.println(cmd.getId() + " -> "+ cmd.getCommand());
                }
            }else{
                System.out.println("Command history not found.");
            }
        }else{
            BaseCommander.err(command);
        }
    }

    private void undoLastDraw(String command){
        UndoLastCommand undoCommand = new UndoLastCommand(command);
        if( undoCommand.valid()) {
            commandService.saveCommand(new Command(command));
            Command lastCommand = commandService.getLastCommand();
            if (lastCommand != null) {
                commandService.setLastUndoCommandId(lastCommand.getId());
                commandService.setCurrentCommandId(0);
                System.out.println("Undo command : " + lastCommand.getCommand());

                List<Command> cmds = commandService.getCommandsBefore(lastCommand.getId());
                persistFlag = false;
                for(Command cmd : cmds){
                    executeCommand(cmd.getCommand());
                }
                persistFlag = true;
            } else {
                System.out.println("Error: no available command to Process..");
            }
        }else{
            BaseCommander.err(command);
        }
    }

    private void redoLastUndoDraw(String command){
        RedoLastUndoCommand redoLastUndoCommand = new RedoLastUndoCommand(command);
        if(redoLastUndoCommand.valid()){
            commandService.saveCommand(new Command(command));
            Command lastUndoCommand = commandService.getLastUndoCommand();

            if(lastUndoCommand!=null){
                commandService.setLastUndoCommandId(0);
                commandService.setCurrentCommandId(lastUndoCommand.getId());
                System.out.println("Redo command : " + lastUndoCommand.getCommand());
                persistFlag = false;
                executeCommand(lastUndoCommand.getCommand());
                persistFlag = true;
            }else{
                System.out.println("Error: no available undo command to Process..");
            }
        }else{
            BaseCommander.err(command);
        }
    }

    private void Quit(String command){
        new QuitCommand(command).excute();
    }
    private void helpMessage(String command){
        new HelpCommand(command).excute();
    }

    private void drawCanvas(String command) {
        if (validCommand(() -> CommandParser.validateCanvas(command))) {
            runLater(() -> {
                if(persistFlag)
                    commandService.saveCommand(new Command(command));
                initialiseCanvas(command);
                CharBox box = ShapeFactory.createBox(currentWidth + 1, currentHeight + 1);
                canvasComponent.updateCanvas(box);
            });
        }
    }

    private void initialiseCanvas(String command) {
        List<Integer> coordinates = getCoordinates(command);
        currentWidth = coordinates.get(0);
        currentHeight = coordinates.get(1);
        int width = currentWidth + 2; // add 2 for vertical borders of the box
        int height = currentHeight + 2; // add 2 for horizontal borders of the box
        canvasComponent.initialise(width, height);
    }

    private void drawLine(String command) {
        if (canvasExists() && validCommand(() -> CommandParser.validateLine(command, currentWidth, currentHeight))) {
            if(persistFlag)
                commandService.saveCommand(new Command(command));
            CharLine charLine = ShapeFactory.createLine(command);
            runLater(() -> canvasComponent.updateCanvas(charLine));
        }
    }

    private void drawRectangle(String command) {
        if (canvasExists() && validCommand(() -> CommandParser.validateRectangle(command, currentWidth, currentHeight))) {
            if(persistFlag)
                commandService.saveCommand(new Command(command));
            CharRectangle recPainter = ShapeFactory.createRectangle(command);
            runLater(() -> canvasComponent.updateCanvas(recPainter));
        }
    }

    private void runLater(Runnable run) {
        SwingUtilities.invokeLater((run));
    }

    private Consumer<String> invalidLetterCommand() {
        return (command) -> System.out.println("Invalid shape letter in command: " + command);
    }

    private boolean canvasExists() {
        if (Objects.isNull(canvasComponent)) {
            System.out.println("Please enter a 'Canvas' command first: C w h");
            return false;
        }
        return true;
    }

    private boolean validCommand(Supplier<Optional<String>> result) {
        Optional<String> errorMessage = result.get();
        if (errorMessage.isPresent()) {
            System.out.println(errorMessage.get());
            return false;
        }
        return true;
    }
}
