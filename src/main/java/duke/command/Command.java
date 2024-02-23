package duke.command;

import duke.task.TaskList;

/**
 * Abstract class that groups all the commands together.
 */
public abstract class Command {
    /**
     * Executes the command behaviour according to the command.
     *
     * @param taskList The existing task list that stores the tasks.
     */
    public abstract void execute(TaskList taskList);
}
