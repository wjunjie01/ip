package Tasks;

/**
 * An abstract class type that is inherited by Todo, Deadline and Event.
 * A task has a name and a done status.
 */
public abstract class Task {
    protected String taskName;
    protected boolean isTaskDone;

    public Task(String taskName) {
        setTaskName(taskName);
        setTaskDone(false);
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public boolean isTaskDone() {
        return isTaskDone;
    }

    public void setTaskDone(boolean taskDone) {
        isTaskDone = taskDone;
    }

    public String getStatusIcon() {
        return (isTaskDone ? "X" : " ");
    }

    public abstract String getTaskType();

    @Override
    public String toString() {
        return "[" + getTaskType() + "][" + getStatusIcon() + "] " + getTaskName();
    }
}
