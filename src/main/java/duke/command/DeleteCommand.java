package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Removes a task from the task list.
 */
public class DeleteCommand extends Command{
    private final int TASK_INDEX;

    /**
     * Create and initialise task index from parsed task.
     *
     * @param taskIndex
     */
    public DeleteCommand(int taskIndex) {
        this.TASK_INDEX = taskIndex;
    }

    /**
     * Deletes the corresponding task from the task list according to task index - 1.
     * Prints the changes.
     *
     * @param input
     * @param taskList
     */
    @Override
    public void execute (String input, TaskList taskList) {
        Ui.printDeleteTask(TASK_INDEX - 1, taskList);
        taskList.taskList.remove(TASK_INDEX - 1);
    }
}
