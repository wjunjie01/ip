package Tasks;

public class Deadline extends Task {

    private String deadline;
    public Deadline(String taskName, String deadline) {
        super(taskName);
        setDeadline(deadline);
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + deadline + ")";
    }
}
