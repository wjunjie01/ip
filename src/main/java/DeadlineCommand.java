public class DeadlineCommand implements Command {
    public static final int BEGIN_DEADLINE_INDEX = 9;

    private String taskDescription;
    private String by;
    public DeadlineCommand(String taskDescription, String by) {
        this.taskDescription = taskDescription;
        this.by = by;
    }
    @Override
    public void execute(String input, TaskList taskList) {
        Deadline deadline = new Deadline(taskDescription, by);
        taskList.addTask(deadline);
        Ui.printAddTask(taskDescription);
    }
}
