package duke.command;

import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * Adds a new todo task to the task list.
 */
public class TodoCommand implements Command {
    public static final int BEGIN_TODO_INDEX = 5;

    /**
     * Adds a new todo task to the task list and prints the changes.
     *
     * @param input
     * @param taskList
     */
    @Override
    public void execute(String input, TaskList taskList) {
        String taskDescription = input.substring(BEGIN_TODO_INDEX);
        taskList.addTask(new Todo(taskDescription));
        Ui.printAddTask(taskDescription);
    }
}
