package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

public class ListCommand implements Command {
    @Override
    public void execute(String input, TaskList taskList) {
        Ui.printListTasks(taskList);
    }
}
