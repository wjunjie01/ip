package duke.command;

import duke.Duke;
import duke.task.TaskList;

/**
 * Exits the program.
 */
public class ByeCommand implements Command {
    /**
     * Change the exit variable to be true, which exits the program.
     *
     * @param input
     * @param taskList
     */
    @Override
    public void execute(String input, TaskList taskList) {
        Duke.isExit = true;
    }
}
