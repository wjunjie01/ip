package duke.task;

import java.util.ArrayList;

/**
 * TaskList class manages the tasks in the task list
 */
public class TaskList {
    public ArrayList<Task> taskList;

    /**
     * Constructs a new taskList
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Adds a task into the task list ArrayList
     *
     * @param task
     */
    public void addTask(Task task) {
        taskList.add(task);
    }
}
