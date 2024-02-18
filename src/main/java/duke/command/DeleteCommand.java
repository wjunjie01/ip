package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command{
    private final int TASK_INDEX;

    public DeleteCommand(int taskIndex) {
        this.TASK_INDEX = taskIndex;
    }

    @Override
    public void execute (String input, TaskList taskList) {
        Ui.printDeleteTask(TASK_INDEX - 1, taskList);
        taskList.taskList.remove(TASK_INDEX - 1);
    }
}
