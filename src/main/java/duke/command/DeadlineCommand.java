package duke.command;

import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Adds a new deadline task to the task list.
 */
public class DeadlineCommand extends Command {
    private final String TASK_DESCRIPTION;
    private final String BY;

    /**
     * Create and initialise the parsed task and the by date from the parser class.
     *
     * @param taskDescription
     * @param by
     */
    public DeadlineCommand(String taskDescription, String by) {
        this.TASK_DESCRIPTION = taskDescription;
        this.BY = by;
    }

    /**
     * Adds a new deadline task to the task list, and prints the changes.
     *
     * @param input
     * @param taskList
     */
    @Override
    public void execute(String input, TaskList taskList) {
        Deadline deadline = new Deadline(TASK_DESCRIPTION, BY);
        taskList.addTask(deadline);
        Ui.printAddTask(TASK_DESCRIPTION);
    }
}
