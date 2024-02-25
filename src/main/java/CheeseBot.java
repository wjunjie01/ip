import Tasks.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class CheeseBot {
    protected final static TasksList tasksList = new TasksList();
    private final Parser PARSER;
    private final Ui UI;
    private final static String inFilePath = "./data/CheeseBot.txt";
    private final static String outFilePath = "./data/temp.txt";
    private final static File inFile = new File(inFilePath);
    private final static File outFile = new File(outFilePath);

    public CheeseBot() {
        PARSER = new Parser();
        UI = new Ui();
    }
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

        case "delete":
            tasksList.delete(arguments);
        }
    }
    public void inputLoop() {
        while (true) {
            try {
                String input = UI.getInput();
                PARSER.validateInput(input);

                String[] arguments = PARSER.parseInput(input);
                String command = arguments[0];

                if (PARSER.isBye(command)) {
                    break;
                }
                botAction(arguments);
            } catch (InvalidInputException e) {
                // No action required. Just catch the exception.
            }
        }
    }

    public void run() {
        UI.printGreeting();
        try {
            readFromInputFile();
        } catch (FileNotFoundException e) {
            createFile(inFile);
        }
        createFile(outFile);
        inputLoop();
        storeData();
        UI.printFarewell();
    }

    public static void main(String[] args) {
        CheeseBot cheeseBot = new CheeseBot();
        cheeseBot.run();
    }

    private static void storeData() {
        storeDataIntoTempFile();
        copyData();
        deleteTempFile();
    }

    private static void storeDataIntoTempFile() {
        try {
            tasksList.outputDataIntoFile(outFilePath);
        } catch (IOException e) {
            System.out.println("Cannot save to output file!");
            System.exit(1);
        }
    }

    private static void deleteTempFile() {
        try {
            Files.delete(Paths.get(outFilePath));
        } catch (IOException e) {
            System.out.println("Temp file cannot be deleted");
            System.exit(1);
        }
    }

    private static void copyData() {
        try {
            Files.copy(Paths.get(outFilePath), Paths.get(inFilePath), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("Data file cannot be replaced");
            System.exit(1);
        }
    }

    private static void readFromInputFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(inFile);

        while (scanner.hasNext()) {
            String nextLine = scanner.nextLine();
            String[] arguments = nextLine.split(" \\| ");
            boolean isTaskDone = Boolean.parseBoolean(arguments[arguments.length - 1]);

            switch (arguments[0]) {
            case "T":
                Todo newTodo = new Todo(arguments[1]);
                newTodo.setTaskDone(isTaskDone);
                tasksList.addTask(newTodo);
                break;

            case "D":
                Deadline newDeadline = new Deadline(arguments[1], arguments[2]);
                newDeadline.setTaskDone(isTaskDone);
                tasksList.addTask(newDeadline);
                break;

            case "E":
                Event newEvent = new Event(arguments[1], arguments[2], arguments[3]);
                newEvent.setTaskDone(isTaskDone);
                tasksList.addTask(newEvent);
                break;
            }
        }
        scanner.close();
    }

    private static void createFile(File file) {
        file.getParentFile().mkdirs();
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.exit(1);
        }
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
