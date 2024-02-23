package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Lists all the task in the task list.
 */
public class ListCommand extends Command {
    /**
     * Prints all the tasks in the list by calling the utility class Ui.
     *
     * @param taskList The existing task list that stores the tasks.
     */
    @Override
    public void execute(TaskList taskList) {
        if (taskList.taskList.isEmpty()) {
            Ui.printListNoTasks();
            return;
        }
        Ui.printListTasks(taskList);
    }
}
