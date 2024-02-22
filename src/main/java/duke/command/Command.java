package duke.command;

import duke.task.TaskList;

/**
 * Interface that groups all the commands together.
 */
public abstract class Command {
    /**
     * Executes the command behaviour according to the command.
     *
     * @param input
     * @param taskList
     */
    public abstract void execute(String input, TaskList taskList);
}
