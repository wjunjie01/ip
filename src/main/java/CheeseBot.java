import Tasks.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CheeseBot {
    protected final static TasksList tasksList = new TasksList();
    private final Parser PARSER = new Parser();
    private final Ui UI = new Ui();
    private static final Storage STORAGE = new Storage();
    private final static String inFilePath = "./data/CheeseBot.txt";
    private final static String outFilePath = "./data/temp.txt";
    private final static File inFile = new File(inFilePath);
    private final static File outFile = new File(outFilePath);
    public final static DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    private void botAction(String[] arguments) throws InvalidInputException {
        String command = arguments[0];

        switch (command) {
        case "list":
            tasksList.listTasks();
            break;

        case "mark":
            tasksList.mark(arguments, true);
            break;

        case "unmark":
            tasksList.mark(arguments, false);
            break;

        case "help":
            UI.printCommandList();
            break;

        case "delete":
            tasksList.delete(arguments);
            break;

        case "find":
            tasksList.find(arguments);
            break;

        default:
            addTask(arguments);
        }
    }

    public void inputLoop() {
        while (true) {
            try {
                String input = UI.getInput();
                UI.printDivider();
                PARSER.validateInput(input);

                String[] arguments = PARSER.parseInput(input);
                String command = arguments[0];

                if (PARSER.isBye(command)) {
                    break;
                }
                botAction(arguments);
            } catch (InvalidInputException e) {
                // No action required. Just catch the exception.
            } finally {
                UI.printDivider();
            }
        }
    }

    public void run() {
        UI.printGreeting();
        try {
            STORAGE.readFromInputFile(inFile);
        } catch (FileNotFoundException e) {
            STORAGE.createFile(inFile);
        }
        STORAGE.createFile(outFile);
        UI.printDivider();
        inputLoop();
        STORAGE.storeData(inFile, outFile);
        UI.printFarewell();
    }

    public static void main(String[] args) {
        CheeseBot cheeseBot = new CheeseBot();
        cheeseBot.run();
    }

    public static void addTask(String[] arguments) throws InvalidInputException {
        String command = arguments[0];
        String taskName = arguments[1];

        switch (command) {
        case "todo":
            tasksList.addTask(new Todo(taskName));
            break;

        case "deadline":
            try {
                LocalDateTime by = LocalDateTime.parse(arguments[2], INPUT_FORMAT);
                tasksList.addTask(new Deadline(taskName, by));
                break;
            } catch (DateTimeParseException e) {
                throw new InvalidInputException("Wrong time and date format for deadline task!");
            }

        case "event":
            try {
                LocalDateTime start = LocalDateTime.parse(arguments[2], INPUT_FORMAT);
                LocalDateTime end = LocalDateTime.parse(arguments[3], INPUT_FORMAT);
                tasksList.addTask(new Event(taskName, start, end));
                break;
            } catch (DateTimeParseException e) {
                throw new InvalidInputException("Wrong time and date format for event task!");
            }
        }
    }
}
