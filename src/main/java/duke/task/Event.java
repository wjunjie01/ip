package duke.task;

/**
 * Event is a type of task that has a description and a time frame for it is valid
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs the event class with the description, from and to dates
     *
     * @param description
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the format of the event task to be printed
     *
     * @return String
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Converts the event task into the specific file format to be written
     *
     * @return String
     */
    @Override
    public String toFileFormat() {
        return "E" + super.toFileFormat() + " | " + from + "-" + to;
    }
}