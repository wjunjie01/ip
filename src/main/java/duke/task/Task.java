package duke.task;

import duke.Duke;
import duke.DukeException;

/**
 * The task class is the base superclass for all the task derivatives that follow
 * It consists of the basic methods that can be used throughout the task derivative classes
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs the task class with the description and a "not done" status
     *
      * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status of the completion of the task
     *
     * @return String
     */
    public String getStatus() {
        return (isDone ? "X": " ");
    }

    /**
     * Marks the task as done
     */
    public void markTask() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done
     */
    public void unmarkTask() {
        this.isDone = false;
    }

    /**
     * Returns the format of the task to be printed
     *
     * @return String
     */
    @Override
    public String toString() {
        return "[" + getStatus() + "] " + this.description;
    }

    /**
     * Converts the task into the file format to be written
     *
     * @return String
     */
    public String toFileFormat() {
        return " | " + (this.isDone? "1" : "0") + " | " + this.description;
    }

    /**
     * Loads the file's inputs and categorises into its respective task type
     * Returns the task and its type to be added into the task list
     * Throws an error if the file format is incorrect
     *
     * @param line
     * @return Task
     * @throws DukeException
     */
    public static Task loadFromFile(String line) throws DukeException {
        //splits according to " | "
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
