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
     * @param taskDescription Parsed event's description to be added.
     * @param from Parsed from date to be added.
     * @param to Parsed to date to be added.
     */
    public EventCommand(String taskDescription, String from, String to) {
        this.TASK_DESCRIPTION = taskDescription;
        this.FROM = from;
        this.TO = to;
    }

    /**
     * Adds a new event task to the task list, and prints the changes.
     *
     * @param taskList The existing task list that stores the tasks.
     */
    @Override
    public void execute(TaskList taskList) {
        Event event = new Event(TASK_DESCRIPTION, FROM, TO);
        taskList.addTask(event);
        Ui.printAddTask(TASK_DESCRIPTION, taskList);
    }
}
