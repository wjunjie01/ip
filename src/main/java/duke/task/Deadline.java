package duke.task;

/**
 * Deadline is a type of task that has a description and a deadline.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructs the deadline class with the description and a by date to complete by.
     *
     * @param description Deadline task's name to be added.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the format of the deadline task to be printed.
     *
     * @return String Format of the deadline task to print.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Converts the deadline task into the specific file format to be written.
     *
     * @return String Format of the task to be written and saved into the file.
     */
    @Override
    public String toFileFormat() {
        return "D" + super.toFileFormat() + " | " + by;
    }
}
