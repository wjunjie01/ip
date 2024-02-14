package duke.command;

import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeadlineCommand implements Command {
    private final String TASK_DESCRIPTION;
    private final String BY;
    public DeadlineCommand(String taskDescription, String by) {
        this.TASK_DESCRIPTION = taskDescription;
        this.BY = by;
    }
    @Override
    public void execute(String input, TaskList taskList) {
        Deadline deadline = new Deadline(TASK_DESCRIPTION, BY);
        taskList.addTask(deadline);
        Ui.printAddTask(TASK_DESCRIPTION);
    }
}
