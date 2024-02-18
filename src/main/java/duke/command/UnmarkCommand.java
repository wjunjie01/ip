package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Marks the corresponding task as not done.
 * Tasks that are not done will display a '[ ]' symbol.
 */
public class UnmarkCommand implements Command{
    private final int TASK_INDEX;

    /**
     * Create and initialise task index from parsed task.
     *
     * @param taskIndex
     */
    public UnmarkCommand(int taskIndex){
        this.TASK_INDEX = taskIndex;
    }

    /**
     * Marks the corresponding task as not done according to task index - 1.
     * Prints the changes thereafter.
     *
     * @param input
     * @param taskList
     */
    public void execute(String input, TaskList taskList) {
        taskList.taskList.get(TASK_INDEX - 1).unmarkTask();
        Ui.printUnmarkTask(TASK_INDEX - 1, taskList);
    }
}
