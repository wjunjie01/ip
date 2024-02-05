public class TodoCommand implements Command {
    public static final int BEGIN_TODO_INDEX = 5;
    @Override
    public void execute(String input, TaskList taskList) {
        String taskDescription = input.substring(BEGIN_TODO_INDEX);
        taskList.addTask(new Todo(taskDescription));
        Ui.printAddTask(taskDescription);
    }
}
