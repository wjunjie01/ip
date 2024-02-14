package duke.command;

import duke.task.TaskList;

public interface Command {
    void execute(String input, TaskList taskList);
}
