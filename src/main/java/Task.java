public class Task {
    protected static int taskCount = 0;
    protected String description;
    protected boolean isDone;

    //constructor
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        taskCount += 1;
    }

    public String getStatus() {
        return (isDone ? "X": " ");
    }

    public static int getTaskCount() {
        return taskCount;
    }

    public void markTask(){
        this.isDone = true;
    }
    public void unmarkTask(){
        this.isDone = false;
    }
    @Override
    public String toString(){
        return "[" + getStatus() + "] " + this.description;
    }
}
