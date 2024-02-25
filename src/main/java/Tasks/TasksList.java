package Tasks;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class TasksList {
    private int numberOfTasks = 0;
    private final ArrayList<Task> tasks = new ArrayList<>();

    public void listTasks() {
        if (isTasksListEmpty()) {
            return;
        }
        printTasksList();
    }

    public void addTask(Task newTask) {
        tasks.add(newTask);
        numberOfTasks++;
        System.out.println("\tYou have added: " + newTask.getTaskName());
        System.out.println("\tYou have a total of " + numberOfTasks + " completed and uncompleted tasks.");
    }

    public void printTasksList() {
        boolean isAllTasksDone = true;
        System.out.println("\tHere's your current list of tasks:");

        int i = 0;
        for (Task task: tasks) {
            if (!task.isTaskDone()) {
                isAllTasksDone = false;
            }

            System.out.println("\t\t" + (i + 1) + "." + task);
            i += 1;
        }

        System.out.println("\tNow you have " + numberOfTasks + " tasks in your list.");

        if (isAllTasksDone) {
            System.out.println("\tExcellent! You have completed all your tasks!");
        }
    }
    public boolean isTasksListEmpty() {
        if (tasks.isEmpty()) {
            System.out.println("\tThere are no tasks in your list! Please add some tasks.");
            return true;
        }
        return false;
    }
    public void mark(String[] arguments, boolean isDone) {
        if (isTasksListEmpty()) {
            return;
        }

        int taskNumber = Integer.parseInt(arguments[1]) - 1;
        Task taskToEdit = tasks.get(taskNumber);

        if (isAlreadyMarked(isDone, taskToEdit) || isAlreadyUnmarked(isDone, taskToEdit)) {
            return;
        }

        changeTaskStatus(isDone, taskToEdit, taskNumber);
    }
    private void changeTaskStatus(boolean isDone, Task taskToEdit, int taskNumber) {
        taskToEdit.setTaskDone(isDone);
        if (isDone) {
            System.out.println("\tWell done, you are one step closer to finishing your tasks!");
            System.out.println("\tI've marked this task done for you:");
        } else {
            System.out.println("\tNo worries, let's do our best!");
            System.out.println("\tI've unmarked this task done for you:");
        }
        System.out.println("\t\t" + (taskNumber + 1) + ". " + taskToEdit);
    }

    private boolean isAlreadyUnmarked(boolean isDone, Task taskToEdit) {
        if (!taskToEdit.isTaskDone() && !isDone) {
            System.out.println("\tTask is already unmarked!");
            return true;
        }
        return false;
    }

    private boolean isAlreadyMarked(boolean isDone, Task taskToEdit) {
        if (taskToEdit.isTaskDone() && isDone) {
            System.out.println("\tTask is already marked done!");
            return true;
        }
        return false;
    }
    public int getNumberOfTasks() {
        return numberOfTasks;
    }

    public void delete(String[] arguments) {
        int taskNumber = Integer.parseInt(arguments[1]) - 1;
        Task taskToDelete = tasks.get(taskNumber);

        tasks.remove(taskToDelete);
        numberOfTasks--;
        System.out.println("\t Done! I have deleted the following task.");
        System.out.println(taskToDelete);
        System.out.println("\tNow you have " + numberOfTasks + " tasks in your list");
    }

    public void outputDataIntoFile(String outputFilePath) throws IOException {
        FileWriter fw = new FileWriter(outputFilePath);
        for (Task task: tasks) {
            String output = task.getTaskType() +  " | " + task.getTaskName();
            switch (task.getTaskType()) {
            case "D":
                Deadline deadline = (Deadline) task;
                output = output.concat(" | " + deadline.getDeadline());
                break;

            case "E":
                Event event = (Event) task;
                output = output.concat(" | " + event.getStart() + " | " + event.getEnd());
                break;

            default:
                break;
            }
            output = output + " | " + task.isTaskDone() + "\n";
            fw.write(output);
        }

        fw.close();
    }
}
