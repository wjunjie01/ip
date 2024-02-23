package duke.command;

import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * Adds a new todo task to the task list.
 */
public class TodoCommand extends Command {
    private final String TASK_DESCRIPTION;

    /**
     * Creates and initialises the parsed todo task.
     *
     * @param TASK_DESCRIPTION Parsed todo description to be added.
     */
    public TodoCommand(String TASK_DESCRIPTION) {
        this.TASK_DESCRIPTION = TASK_DESCRIPTION;
    }

    /**
     * Adds a new todo task to the task list and prints the changes.
     *
     * @param taskList The existing task list that stores the tasks.
     */
    @Override
    public void execute(TaskList taskList) {
        taskList.addTask(new Todo(TASK_DESCRIPTION));
        Ui.printAddTask(TASK_DESCRIPTION, taskList);
    }
}
