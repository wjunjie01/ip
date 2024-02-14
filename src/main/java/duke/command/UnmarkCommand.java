package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
public class UnmarkCommand implements Command{
    private final int TASK_INDEX;
    public UnmarkCommand(int taskIndex){
        this.TASK_INDEX = taskIndex;
    }
    public void execute(String input, TaskList taskList) {
        taskList.taskList.get(TASK_INDEX - 1).unmarkTask();
        Ui.printUnmarkTask(TASK_INDEX - 1, taskList);
    }
}
