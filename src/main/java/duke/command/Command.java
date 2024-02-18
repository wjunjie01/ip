package duke.command;

import duke.task.TaskList;

/**
 * Interface that groups all the commands together.
 */
public interface Command {
    /**
     * Executes the command behaviour according to the command.
     *
     * @param input
     * @param taskList
     */
    void execute(String input, TaskList taskList);
}
