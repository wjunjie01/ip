import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> taskList;
    public TaskList() {
        taskList = new ArrayList<>();
    }
    public void addTask(Task task) {
        taskList.add(task);
    }
}
