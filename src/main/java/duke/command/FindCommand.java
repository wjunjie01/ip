package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    public String description;
    public FindCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(String input, TaskList taskList) {
        int count = 0;

        for (Task task : taskList.taskList) {
            if (task.toString().contains(description)) {
                count += 1;
                Ui.printFoundTask(task, count);
            }
        }
        Ui.printNotFoundTask(count);
    }
}
