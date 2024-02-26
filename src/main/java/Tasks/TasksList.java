package Tasks;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Helper class to manage task-related operations. Primarily manages the array of tasks stored.
 */
public class TasksList {
    private int numberOfTasks = 0;
    private final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Lists all the tasks stored in the array of tasks.
     */
    public void listTasks() {
        if (isTasksListEmpty()) {
            return;
        }
        printTasksList();
    }

    /**
     * Adds a new Task into array of tasks.
     *
     * @param newTask A Task to be added. Can be of 1 of the 3 inherited types of Task (Todo, Deadline or Event).
     */
    public void addTask(Task newTask) {
        tasks.add(newTask);
        numberOfTasks++;
        System.out.println("\tYou have added: " + newTask.getTaskName());
        System.out.println("\tYou have a total of " + numberOfTasks + " completed and uncompleted tasks.");
    }

    private void printTasksList() {
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

    private boolean isTasksListEmpty() {
        if (tasks.isEmpty()) {
            System.out.println("\tThere are no tasks in your list! Please add some tasks.");
            return true;
        }
        return false;
    }

    /**
     * Marks or unmarks a specific task according to the isDone value.
     *
     * @param arguments Contains the arguments for the mark or unmark command. Mainly uses arguments[1], which
     *                  corresponds to the task number of the task in the current most updated list which the user
     *                  wishes to mark or unmark.
     * @param isDone Value to be updated for the task
     */
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

    /**
     * Deletes a specific task, which is specified by the task number. Mainly uses arguments[1] which corresponds to the
     * task number of the task in the current most updated list which the user wishes to delete.
     *
     * @param arguments The array containing arguments supplied by user for the delete command.
     */
    public void delete(String[] arguments) {
        int taskNumber = Integer.parseInt(arguments[1]) - 1;
        Task taskToDelete = tasks.get(taskNumber);

        tasks.remove(taskToDelete);
        numberOfTasks--;
        System.out.println("\t Done! I have deleted the following task.");
        System.out.println(taskToDelete);
        System.out.println("\tNow you have " + numberOfTasks + " tasks in your list");
    }

    /**
     * Stores the final status of the array into the file specified by the outputFilePath.
     * Formats each Task into a specific format before saving into save file.
     *
     * @param outputFilePath The file location where the output file is located at.
     * @throws IOException Throws IO Exception if data cannot be stored into file.
     */
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
