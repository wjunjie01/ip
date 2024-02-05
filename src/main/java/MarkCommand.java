public class MarkCommand implements Command{
    private int taskIndex;
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }
    @Override
    public void execute(String input, TaskList taskList) {
        taskList.taskList.get(taskIndex - 1).markTask();
        Ui.printMarkTask(taskIndex - 1, taskList);
    }
}
