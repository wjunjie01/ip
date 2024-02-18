package duke.command;

import duke.task.Event;
import duke.task.TaskList;
import duke.ui.Ui;

public class EventCommand extends Command{
    private final String TASK_DESCRIPTION;
    private final String FROM;
    private final String TO;

    public EventCommand(String taskDescription, String from, String to) {
        this.TASK_DESCRIPTION = taskDescription;
        this.FROM = from;
        this.TO = to;
    }

    @Override
    public void execute(String input, TaskList taskList) {
        Event event = new Event(TASK_DESCRIPTION, FROM, TO);
        taskList.addTask(event);
        Ui.printAddTask(TASK_DESCRIPTION);
    }
}
