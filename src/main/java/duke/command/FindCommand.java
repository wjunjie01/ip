package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * FindCommand attempts to find a specific keyword in the task list.
 */
public class FindCommand extends Command {
    public String keyword;

    /**
     * Creates and initialises the find command with the keyword.
     *
     * @param keyword The keyword to find in the task list.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    /**
     * Prints out a list of tasks that matches with the keyword.
     * If there is no match, it will tell the user that the task with the keyword cannot be found.
     *
     * @param taskList The existing task list that stores the tasks.
     */
    @Override
    public void execute(TaskList taskList) {
        int count = 0;

        for (Task task : taskList.taskList) {
            if (task.toString().toLowerCase().contains(keyword)) {
                count += 1;
                Ui.printFoundTask(task, count);
            }
        }
        Ui.printNotFoundTask(count);
    }
}
