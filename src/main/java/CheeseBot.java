import Tasks.*;

import java.io.File;
import java.io.FileNotFoundException;

public class CheeseBot {
    protected final static TasksList tasksList = new TasksList();
    private final Parser PARSER = new Parser();
    private final Ui UI = new Ui();
    private static final Storage STORAGE = new Storage();
    private final static String inFilePath = "./data/CheeseBot.txt";
    private final static String outFilePath = "./data/temp.txt";
    private final static File inFile = new File(inFilePath);
    private final static File outFile = new File(outFilePath);

    private void botAction(String[] arguments) {
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

        case "todo":
            //Fallthrough
        case "deadline":
            //Fallthrough
        case "event":
            addTask(arguments);
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

    public static void addTask(String[] arguments) {
        String command = arguments[0];
        String taskName = arguments[1];

        switch (command) {
        case "todo":
            tasksList.addTask(new Todo(taskName));
            break;

        case "deadline":
            String by = arguments[2];
            tasksList.addTask(new Deadline(taskName, by));
            break;

        case "event":
            String start = arguments[2];
            String end = arguments[3];
            tasksList.addTask(new Event(taskName, start, end));
            break;
        }
    }
}
