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
    public Duke(String filePath) {
        isExit = false;
        taskList = new TaskList();
        storage = new Storage(filePath);

        try {
            taskList = storage.loadTaskList();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void run() {
        Ui.printWelcomeMessage();

        while (!isExit) {
            try {
                String input = Ui.readUserInput();
                Command command = Parser.parse(input, taskList);
                command.execute(taskList);
                storage.saveTask(taskList);
            } catch (DukeException e) {
                Ui.printLine();
                System.out.println(e.getMessage());
                Ui.printLine();
            }
        }
        Ui.printGoodbyeMessage();
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
