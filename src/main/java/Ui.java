public class Ui {
    public static final String LINE = "---------------";
    public static final String LOGO = "       __                 __ \n"
            + "      / /___ _____  ___  / /_\n"
            + " __  / / __ `/ __ \\/ _ \\/ __/\n"
            + "/ /_/ / /_/ / / / /  __/ /_  \n"
            + "\\____/\\__,_/_/ /_/\\___/\\__/  \n"
            + "                             \n";
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
    public static void printListTasks(Task[] list) {
        System.out.println("The tasks in your list are: ");
        for (int i = 0; i < Task.getTaskCount(); i++) {
            System.out.println((i + 1) + "." + list[i].toString());
        }
        System.out.println(LINE);
    }

    public static void printMarkTask(int markIndex, Task[] list){
        System.out.println("Well done! This task is now done: ");
        System.out.println(list[markIndex].toString());
        System.out.println(LINE);
    }

    public static void printUnmarkTask(int markIndex, Task[] list){
        System.out.println("Boo! This task is not done yet! ");
        System.out.println(list[markIndex].toString());
        System.out.println(LINE);
    }

    public static void printAddTask(String taskDescription){
        System.out.println("Task added: " + taskDescription);
        System.out.println("You now have " + Task.getTaskCount() + " task(s) in the list.");
        System.out.println(LINE);
    }
}
