package duke.command;

import duke.Duke;
import duke.task.TaskList;
public class ByeCommand extends Command {
    @Override
    public void execute(String input, TaskList taskList) {
        Duke.isExit = true;
    }
}
