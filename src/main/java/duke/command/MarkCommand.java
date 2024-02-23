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
     * @param taskIndex
     */
    public MarkCommand(int taskIndex) {
        this.TASK_INDEX = taskIndex;
    }

    /**
     * Marks the corresponding task as done according to task index - 1.
     * Prints the changes thereafter.
     *
     * @param input
     * @param taskList
     */
    @Override
    public void execute(String input, TaskList taskList) {
        taskList.taskList.get(TASK_INDEX - 1).markTask();
        Ui.printMarkTask(TASK_INDEX - 1, taskList);
    }
}
