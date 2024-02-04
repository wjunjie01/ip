public class Event extends Task {
    protected String start;
    protected String end;

    public Event(String taskName, String start, String end) {
        super(taskName);
        setStart(start);
        setEnd(end);
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String toString() {
        return super.toString() + "(from: " + start + " to: " + end + ")";
    }
}
