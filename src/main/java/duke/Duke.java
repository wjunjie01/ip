package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class Duke {
    public static boolean isExit;
    public TaskList taskList;
    public Storage storage;
    public Duke(){
        isExit = false;
        taskList = new TaskList();

        String filePath = "data/duke.txt";
        storage = new Storage(filePath);

        try {
            taskList = storage.loadTaskList();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        Ui.printWelcomeMessage();

        while (!isExit) {
            try {
                String input = Ui.readUserInput();
                Command command = Parser.parse(input, duke.taskList);
                command.execute(input, duke.taskList);
                duke.storage.saveTask(duke.taskList);
            } catch (DukeException e) {
                Ui.printLine();
                System.out.println(e.getMessage());
                Ui.printLine();
            }
        }
        Ui.printGoodbyeMessage();
    }
}
