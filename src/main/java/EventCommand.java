public class EventCommand implements Command{
    private String taskDescription;
    private String from;
    private String to;

    public EventCommand(String taskDescription, String from, String to) {
        this.taskDescription = taskDescription;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(String input, TaskList taskList) {
        Event event = new Event(taskDescription, from, to);
        taskList.addTask(event);
        Ui.printAddTask(taskDescription);
    }
}
