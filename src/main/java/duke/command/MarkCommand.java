package duke.command;

import duke.ui.Ui;
import duke.task.TaskList;

public class MarkCommand extends Command{
    private final int TASK_INDEX;
    public MarkCommand(int taskIndex) {
        this.TASK_INDEX = taskIndex;
    }
    @Override
    public void execute(String input, TaskList taskList) {
        taskList.taskList.get(TASK_INDEX - 1).markTask();
        Ui.printMarkTask(TASK_INDEX - 1, taskList);
    }
}
