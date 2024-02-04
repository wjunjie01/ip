import java.util.Arrays;
import java.util.Scanner;

public class CheeseBot {
    private static int numberOfTasks = 0;
    private static final Task[] TASKS_LIST = new Task[100];


    public static void listItems() {
        if (numberOfTasks == 0) {
            System.out.println("\tThere are no tasks in your list! Please add some tasks.");
            System.out.println("\t-------------------------------------------------------------------");
            return;
        }

        boolean isAllTasksDone = true;
        System.out.println("\tHere's your current list of tasks:");

        for (int i = 0; i < numberOfTasks; i++) {
            Task currentTask = TASKS_LIST[i];
            if (!currentTask.isTaskDone()) {
                isAllTasksDone = false;
            }
            System.out.println("\t" + (i + 1) + "." + currentTask);
        }
        System.out.println("\tNow you have " + numberOfTasks + " tasks in your list.");

        if (isAllTasksDone) {
            System.out.println("\tExcellent! You have completed all your tasks!");
        }
        System.out.println("\t-------------------------------------------------------------------");
    }

    public static void addTask(String[] arguments) {
        String command = arguments[0];
        String taskName = arguments[1];

        switch (command) {
        case "todo":
            TASKS_LIST[numberOfTasks] = new Todo(taskName);
            break;
        case "deadline":
            String by = arguments[2];
            TASKS_LIST[numberOfTasks] = new Deadline(taskName, by);
            break;
        case "event":
            String start = arguments[2];
            String end = arguments[3];
            TASKS_LIST[numberOfTasks] = new Event(taskName, start, end);
            break;
        }

        numberOfTasks++;
        System.out.println("\tYou have added: " + taskName);
        System.out.println("\tYou have a total of " + numberOfTasks + " completed and uncompleted tasks.");
        System.out.println("\t-------------------------------------------------------------------");
    }

    public static boolean validate(String input) {
        if (input.isEmpty()) {
            System.out.println("\tInput is empty! Please type something.");
            return false;
        }

        int spaceIndex = input.indexOf(" ");
        if (spaceIndex == -1) {
            if (input.equals("list")) {
                return true;
            }
            System.out.println("invalid format");
            return false;
        }
        String command = input.substring(0, spaceIndex);
        switch (command) {
        case "todo":
            return true;

        case "deadline":
            int byIndex = input.indexOf("/by ");
            if (byIndex == -1 || byIndex + 4 > input.length()) {
                System.out.println("\tMissing parameters! ");
                return false;
            }

            return true;

        case "event":
            int fromIndex = input.indexOf("/from ");
            int toIndex = input.indexOf("/to ");
            if (fromIndex == -1 || toIndex == -1) {
                System.out.println("\tMissing arguments");
                return false;
            }
            if (fromIndex > toIndex) {
                System.out.println("\tWrong order of arguments!");
                return false;
            }
            if (fromIndex + 6 > input.length() || toIndex + 4 > input.length() || fromIndex + 6 == toIndex) {
                System.out.println("\tMissing arguments");
                return false;
            }
            return true;

        case "mark":
        case "unmark":
            int taskNumber = Integer.parseInt(input.substring(spaceIndex + 1)) - 1;
            if (taskNumber >= numberOfTasks) {
                System.out.println("\tInvalid number! Number must be less than the number of tasks ("
                        + numberOfTasks + ").");
                System.out.println("\t-------------------------------------------------------------------");
                return false;

            } else if (taskNumber < 0) {
                System.out.println("\tInvalid number! Task number must be more than 0.");
                System.out.println("\t-------------------------------------------------------------------");
                return false;

            } else {
                return true;
            }
        default:
            return false;
        }

    }

    public static String[] parse(String input) {
        String[] parsed = new String[4];
        int spaceIndex = input.indexOf(" ");
        if (spaceIndex == -1) {
            parsed[0] = input;
            return parsed;
        }
        String command = input.substring(0, spaceIndex);
        String taskName;

        parsed[0] = command;

        switch (command) {
        case "todo":
            taskName = input.substring(spaceIndex + 1);
            parsed[1] = taskName;
            return parsed;

        case "deadline":
            int byIndex = input.indexOf("/by ");

            taskName = input.substring(spaceIndex + 1, byIndex);
            String by = input.substring(byIndex + 4);
            parsed[1] = taskName;
            parsed[2] = by;

            return parsed;

        case "event":
            int fromIndex = input.indexOf("/from ");
            int toIndex = input.indexOf("/to ");

            taskName = input.substring(spaceIndex + 1, fromIndex);
            String from = input.substring(fromIndex + 6, toIndex);
            String to = input.substring(toIndex + 4);
            parsed[1] = taskName;
            parsed[2] = from;
            parsed[3] = to;

            return parsed;
        default:
            String taskNumber = input.substring(spaceIndex + 1);
            parsed[1] = taskNumber;

            return parsed;
        }

    }

    public static void mark(String[] arguments, boolean isDone) {
        if (numberOfTasks == 0) {
            System.out.println("\tThere are no tasks in your list! Please add some tasks.");
            System.out.println("\t-------------------------------------------------------------------");
            return;
        }

        int taskNumber = Integer.parseInt(arguments[1]) - 1;
        Task taskToEdit = TASKS_LIST[taskNumber];

        if (taskToEdit.isTaskDone() && isDone) { //checks if a completed task is to be marked again
            System.out.println("\tTask is already marked done!");
            System.out.println("\t-------------------------------------------------------------------");
            return;
        } else if (!taskToEdit.isTaskDone() && !isDone) { // checks if an uncompleted task is to be unmarked
            System.out.println("\tTask is already unmarked!");
            System.out.println("\t-------------------------------------------------------------------");
            return;
        }

        taskToEdit.setTaskDone(isDone);
        if (isDone) {
            System.out.println("\tWell done, you are one step closer to finishing your tasks!");
            System.out.println("\tI've marked this task done for you:");
        } else {
            System.out.println("\tNo worries, let's do our best!");
            System.out.println("\tI've unmarked this task done for you:");
        }
        System.out.println("\t\t" + (taskNumber + 1) + ". " + taskToEdit);
        System.out.println("\t-------------------------------------------------------------------");
    }

    public static void main(String[] args) {
        String greeting = "\t-------------------------------------------------------------------\n"
                + "\tHello! I'm CheeseBot\n"
                + "\tWhat can I do for you?\n"
                + "\t-------------------------------------------------------------------";
        System.out.println(greeting);

        Scanner in = new Scanner(System.in);
        String inputPrompt = "\tPlease input your desired action: ";

        System.out.println(inputPrompt);
        String input = in.nextLine().strip();
        System.out.println("\t-------------------------------------------------------------------");

        while (true) {
            if (validate(input)) {
                String[] arguments = parse(input);
                String command = arguments[0];
                if (command.equals("bye")) {
                    break;
                }

                switch (command) {
                case "list":
                    listItems();
                    break;
                case "mark":
                    mark(arguments, true);
                    break;
                case "unmark":
                    mark(arguments, false);
                    break;
                case "todo":
                case "deadline":
                case "event":
                    addTask(arguments);
                    break;
                }
            }

            System.out.println(inputPrompt);
            input = in.nextLine().strip();
            System.out.println("\t-------------------------------------------------------------------");
        }

        String farewell = "\tBye. Hope to see you again soon!\n"
                + "\t-------------------------------------------------------------------";
        System.out.print(farewell);
    }
}
