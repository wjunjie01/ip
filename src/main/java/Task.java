public class Task{
    protected static int taskCount = 0;
    protected String description;
    protected boolean isDone;

    //constructor
    public Task(String description){
        this.description = description;
        this.isDone = false;
        taskCount += 1;
    }
    public String getStatus(){

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

//Inheritance and Polymorphism
class Deadline extends Task{

    protected String by;
    public Deadline(String description, String by){
        super(description);
        this.by = by;
    }
    @Override
    public String toString() {

        return "[D]"+ super.toString() + " (by: " + by + ")";
    }
}

class Todo extends Task{

    public Todo(String description){
        super(description);
    }
    @Override
    public String toString() {

        return "[T]"+ super.toString();
    }
}

class Event extends Task{
    protected String from;
    protected String to;
    public Event(String description, String from, String to){
        super(description);
        this.from = from;
        this.to = to;
    }
    @Override
    public String toString() {

        return "[E]"+ super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
