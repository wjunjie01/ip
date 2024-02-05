public class UnmarkCommand implements Command{
    private int taskIndex;
    public UnmarkCommand(int taskIndex){
        this.taskIndex = taskIndex;
    }
    public void execute(String input, TaskList taskList) {
        taskList.taskList.get(taskIndex - 1).unmarkTask();
        Ui.printUnmarkTask(taskIndex - 1, taskList);
    }
}
