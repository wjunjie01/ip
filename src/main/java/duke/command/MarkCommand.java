package duke.command;

import duke.ui.Ui;
import duke.task.TaskList;

/**
 * Marks the corresponding task as done.
 * Tasks that are done will display a '[X]' symbol.
 */
public class MarkCommand extends Command {
    private final int TASK_INDEX;

    /**
     * Create and initialise task index from parsed task.
     *
     * @param taskIndex Parsed task index to be marked.
     */
    public MarkCommand(int taskIndex) {
        this.TASK_INDEX = taskIndex;
    }

    /**
     * Marks the corresponding task as done according to task index - 1.
     * Prints the changes thereafter.
     *
     * @param taskList The existing task list that stores the tasks.
     */
    @Override
    public void execute(TaskList taskList) {
        taskList.taskList.get(TASK_INDEX - 1).markTask();
        Ui.printMarkTask(TASK_INDEX - 1, taskList);
    }
}
