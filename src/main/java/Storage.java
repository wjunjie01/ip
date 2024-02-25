import Tasks.Deadline;
import Tasks.Event;
import Tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class Storage {

    public void storeData(File inFile, File outFile) {
        String inFilePath = inFile.getPath();
        String outFilePath = outFile.getPath();

        storeDataIntoTempFile(outFilePath);
        copyData(inFilePath, outFilePath);
        deleteTempFile(outFilePath);
    }

    private static void storeDataIntoTempFile(String outFilePath) {
        try {
            CheeseBot.tasksList.outputDataIntoFile(outFilePath);
        } catch (IOException e) {
            System.out.println("Cannot save to output file!");
            System.exit(1);
        }
    }

    private static void deleteTempFile(String outFilePath) {
        try {
            Files.delete(Paths.get(outFilePath));
        } catch (IOException e) {
            System.out.println("Temp file cannot be deleted");
            System.exit(1);
        }
    }

    private static void copyData(String inFilePath, String outFilePath) {
        try {
            Files.copy(Paths.get(outFilePath), Paths.get(inFilePath), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("Data file cannot be replaced");
            System.exit(1);
        }
    }
    public void createFile(File file) {
        file.getParentFile().mkdirs();
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.exit(1);
        }
    }
    public void readFromInputFile(File inFile) throws FileNotFoundException {
        Scanner scanner = new Scanner(inFile);

        while (scanner.hasNext()) {
            String nextLine = scanner.nextLine();
            String[] arguments = nextLine.split(" \\| ");
            boolean isTaskDone = Boolean.parseBoolean(arguments[arguments.length - 1]);

            switch (arguments[0]) {
            case "T":
                Todo newTodo = new Todo(arguments[1]);
                newTodo.setTaskDone(isTaskDone);
                CheeseBot.tasksList.addTask(newTodo);
                break;

            case "D":
                Deadline newDeadline = new Deadline(arguments[1], arguments[2]);
                newDeadline.setTaskDone(isTaskDone);
                CheeseBot.tasksList.addTask(newDeadline);
                break;

            case "E":
                Event newEvent = new Event(arguments[1], arguments[2], arguments[3]);
                newEvent.setTaskDone(isTaskDone);
                CheeseBot.tasksList.addTask(newEvent);
                break;
            }
        }
        scanner.close();
    }
}
