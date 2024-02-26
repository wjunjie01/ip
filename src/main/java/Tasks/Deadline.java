package Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDateTime deadline;
    private final static DateTimeFormatter DESIRED_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
    public Deadline(String taskName, LocalDateTime deadline) {
        super(taskName);
        setDeadline(deadline);
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + deadline.format(DESIRED_FORMAT) + ")";
    }
}
