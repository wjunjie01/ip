package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Lists all the task in the task list
 */
public class ListCommand implements Command {
    /**
     * Prints all the tasks in the list by calling the utility class Ui.
     *
     * @param input
     * @param taskList
     */
    @Override
    public void execute(String input, TaskList taskList) {
        Ui.printListTasks(taskList);
    }
}
