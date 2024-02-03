public class Task {
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

    public String getTaskType() {
        return "";
    }
}
