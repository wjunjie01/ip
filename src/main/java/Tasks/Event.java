package Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task.
 * An Event task consists of a task's name, its start date and time, its end data and time, and whether it is completed.
 */
public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;
    private final static DateTimeFormatter DESIRED_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");

    public Event(String taskName, LocalDateTime start, LocalDateTime end) {
        super(taskName);
        setStart(start);
        setEnd(end);
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String toString() {
        return super.toString() +
                " (from: " + start.format(DESIRED_FORMAT) + " to: " + end.format(DESIRED_FORMAT) + ")";
    }
}
