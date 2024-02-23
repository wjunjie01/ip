package duke.command;

import duke.task.Event;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Adds a new event task to the task list.
 */
public class EventCommand extends Command {
    private final String TASK_DESCRIPTION;
    private final String FROM;
    private final String TO;

    /**
     * Create and initialise the parsed task, from and to date from the parser class.
     *
     * @param taskDescription
     * @param from
     * @param to
     */
    public EventCommand(String taskDescription, String from, String to) {
        this.TASK_DESCRIPTION = taskDescription;
        this.FROM = from;
        this.TO = to;
    }

    /**
     * Adds a new event task to the task list, and prints the changes.
     *
     * @param input
     * @param taskList
     */
    @Override
    public void execute(String input, TaskList taskList) {
        Event event = new Event(TASK_DESCRIPTION, FROM, TO);
        taskList.addTask(event);
        Ui.printAddTask(TASK_DESCRIPTION, taskList);
    }
}
