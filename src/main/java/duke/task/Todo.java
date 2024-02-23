package duke.task;

/**
 * Todo task is a type of task that only specifies a description
 */
public class Todo extends Task {

    /**
     * Constructs the todo class with the description
     *
     * @param description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the format of the todo task to be printed
     *
     * @return String
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts the todo task into the specific file format to be written
     *
     * @return String
     */
    @Override
    public  String toFileFormat() {
        return "T" + super.toFileFormat();
    }
}