package duke.task;

import duke.Duke;
import duke.DukeException;

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

    public String toFileFormat() {
        return " | " + (this.isDone? "1" : "0") + " | " + this.description;
    }

    public static Task loadFromFile(String line) throws DukeException {
        String[] taskDescArray = line.split(" \\| ");
        String taskType = taskDescArray[0];
        boolean isDone = taskDescArray[1].equals("1");
        String taskDescription = taskDescArray[2];

        Task task;
        switch (taskType) {
        case "T":
            task = new Todo(taskDescription);
            break;
        case "D":
            String by = taskDescArray[3];
            task = new Deadline(taskDescription, by);
            break;
        case "E":
            String eventFromTo = taskDescArray[3];
            String[] eventFromToArray = eventFromTo.split("-");
            String from = eventFromToArray[0];
            String to = eventFromToArray[1];

            task = new Event(taskDescription, from, to);
            break;
        default:
            throw new DukeException("Error file format is incorrect and unable to load! ");
        }

        if (isDone) {
            task.markTask();
        }
        return task;
    }
}
