public class Task{
    protected String description;
    protected boolean isDone;

    //constructor
    public Task(String description){
        this.description = description;
        this.isDone = false;
    }
    public String getStatus(){
        return (isDone ? "X": " ");
    }
    public void markTask(){
        this.isDone = true;
    }
    public void unmarkTask(){
        this.isDone = false;
    }
    public String taskInfo(){
        return "[" + getStatus() + "] " + this.description;
    }
}