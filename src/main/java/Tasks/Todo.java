package Tasks;

/**
 * Represents a Todo task. A Todo task consists of only the task's name and whether it is completed.
 */
public class Todo extends Task {
    public Todo(String taskName) {
        super(taskName);
    }

    @Override
    public String getTaskType() {
        return "T";
    }
}
