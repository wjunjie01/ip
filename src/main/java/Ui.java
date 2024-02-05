import java.util.Scanner;

public class Ui {
    static Scanner in = new Scanner(System.in);
    public static final String LINE = "---------------";
    public static final String LOGO = "       __                 __ \n"
            + "      / /___ _____  ___  / /_\n"
            + " __  / / __ `/ __ \\/ _ \\/ __/\n"
            + "/ /_/ / /_/ / / / /  __/ /_  \n"
            + "\\____/\\__,_/_/ /_/\\___/\\__/  \n"
            + "                             \n";

    public static String readUserInput(){
        return in.nextLine();
    }

    public static void printLine() {
        System.out.println(LINE);
    }
    public static void printGoodbyeMessage() {
        System.out.println("Goodbye! See you again!");
        System.out.println(LINE);
    }
    public static void printWelcomeMessage() {
        System.out.println(LOGO);
        System.out.println("Hi! I'm Janet, your personal assistant.");
        System.out.println("What is your request?");
        System.out.println(LINE);
    }
    public static void printListTasks(TaskList taskList) {
        System.out.println("The tasks in your list are: ");
        for (int i = 0; i < taskList.taskList.size(); i++) {
            System.out.println((i + 1) + "." + taskList.taskList.get(i).toString());
        }
        System.out.println(LINE);
    }

    public static void printMarkTask(int taskIndex, TaskList taskList){
        System.out.println("Well done! This task is now done: ");
        System.out.println(taskList.taskList.get(taskIndex).toString());
        System.out.println(LINE);
    }

    public static void printUnmarkTask(int taskIndex, TaskList taskList){
        System.out.println("Boo! This task is not done yet! ");
        System.out.println(taskList.taskList.get(taskIndex).toString());
        System.out.println(LINE);
    }

    public static void printAddTask(String taskDescription){
        System.out.println("Task added: " + taskDescription);
        System.out.println("You now have " + Task.getTaskCount() + " task(s) in the list.");
        System.out.println(LINE);
    }
}
