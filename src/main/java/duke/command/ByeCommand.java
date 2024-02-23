package duke.command;

import duke.Duke;
import duke.task.TaskList;


/**
 * Exits the program.
 */
public class ByeCommand extends Command {
    /**
     * Change the exit variable to be true, which exits the program.
     *
     * @param taskList The existing task list that stores the tasks.
     */
    @Override
    public void execute(TaskList taskList) {
        Duke.isExit = true;
    }
}
