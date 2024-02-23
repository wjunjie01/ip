package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

/**
 * The UI class is in charge of printing the output to the user from all the commands
 */
public class Ui {
    static Scanner in = new Scanner(System.in);
    public static final String LINE = "---------------";
    public static final String LOGO = "       __                 __ \n"
            + "      / /___ _____  ___  / /_\n"
            + " __  / / __ `/ __ \\/ _ \\/ __/\n"
            + "/ /_/ / /_/ / / / /  __/ /_  \n"
            + "\\____/\\__,_/_/ /_/\\___/\\__/  \n"
            + "                             \n";

    public static String readUserInput() {
        return in.nextLine();
    }

    public static void printLine() {
        System.out.println(LINE);
    }

    public static void printGoodbyeMessage() {
        System.out.println("Goodbye! See you again!");
        printLine();
    }

    public static void printWelcomeMessage() {
        System.out.println(LOGO);
        System.out.println("Hi! I'm Janet, your personal assistant.");
        System.out.println("What is your request?");
        printLine();
    }

    public static void printListTasks(TaskList taskList) {
        System.out.println("The tasks in your list are: ");
        //Iterates through the task in the task list, gets the task and prints it
        for (int i = 0; i < taskList.taskList.size(); i++) {
            System.out.println((i + 1) + "." + taskList.taskList.get(i).toString());
        }
        printLine();
    }

    public static void printListNoTasks() {
        System.out.println("No task in your list, yay!");
        printLine();
    }

    public static void printMarkTask(int taskIndex, TaskList taskList) {
        System.out.println("Well done! This task is now done: ");
        printTask(taskIndex, taskList);
    }

    public static void printUnmarkTask(int taskIndex, TaskList taskList) {
        System.out.println("Boo! This task is not done yet! ");
        printTask(taskIndex, taskList);
    }

    private static void printTask(int taskIndex, TaskList taskList) {
        System.out.println("\t" + taskList.taskList.get(taskIndex).toString());
        printLine();
    }

    public static void printAddTask(String taskDescription, TaskList taskList) {
        System.out.println("added: " + taskDescription);
        System.out.println("You now have " + taskList.taskList.size() + " task"
                + (taskList.taskList.size() == 1 ? "" : "s") + " in the list.");
        printLine();
    }

    public static void printDeleteTask(int taskIndex, TaskList taskList) {
        System.out.println("Okay, I've removed the task");
        printTask(taskIndex, taskList);
        System.out.println("You now have " + (taskList.taskList.size() - 1) + " task"
                + (taskList.taskList.size() == 1 ? "" : "s") + " in the list.");
        printLine();
    }

    public static void printFoundTask(Task task, int count) {
        if (count == 1) {
            System.out.println("Here are the matching task(s) in your list: ");
            printLine();
        }
        System.out.println(count + ". " + task);
    }

    public static void printNotFoundTask(int count) {
        printLine();
        if (count == 0) {
            System.out.println("Hmm... task not found :( ");
            printLine();
        }
    }
}
