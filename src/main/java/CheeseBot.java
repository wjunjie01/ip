import java.util.Scanner;

public class CheeseBot {
    private static int numberOfTasks = 0;
    private static final Task[] TASKS_LIST = new Task[100];


    public static void listTasks() {
        if (isTasksListEmpty()) {
            return;
        }
        printTasksList();
    }

    private static void printTasksList() {
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

    public static boolean isValidInput(String input) {
        if (input.isEmpty()) {
            System.out.println("\tInput is empty! Please type something.");
            return false;
        }

        int spaceIndex = input.indexOf(" ");
        if (spaceIndex == -1) {
            return isValidSingleWordCommand(input);
        }

        String command = input.substring(0, spaceIndex);
        switch (command) {
        case "todo":
            return true;

        case "deadline":
            return isValidDeadlineInput(input);

        case "event":
            return isValidEventInput(input);

        case "mark":
            //Fallthrough
        case "unmark":
            return isValidMarkAndUnmarkInput(input, spaceIndex);

        default: // case where there is a space but command does not match any
            System.out.println("\tNo such command!");
            return false;
        }
    }

    private static boolean isValidSingleWordCommand(String input) {
        if (input.equals("list") || input.equals("bye")) { // for case of single word commands with no space
            return true;
        } else { // for commands where >1 arguments are needed
            System.out.println("\tWrong command usage!");
            return false;
        }
    }

    private static boolean isValidMarkAndUnmarkInput(String input, int spaceIndex) {
        int taskNumber = Integer.parseInt(input.substring(spaceIndex + 1)) - 1;
        if (taskNumber >= numberOfTasks) {
            System.out.println("\tInvalid number! Number must be less than the number of tasks ("
                    + numberOfTasks + ").");
            System.out.println("\t-------------------------------------------------------------------");
            return false;
        }

        if (taskNumber < 0) {
            System.out.println("\tInvalid number! Task number must be more than 0.");
            System.out.println("\t-------------------------------------------------------------------");
            return false;

        }
        return true;
    }

    private static boolean isValidEventInput(String input) {
        int spaceIndex = input.indexOf(" ");
        int fromIndex = input.indexOf("/from");
        int toIndex = input.indexOf("/to");

        if (fromIndex == -1) {
            System.out.println("\tMissing '/from' field");
            return false;
        } else if (fromIndex + 6 > input.length()) {
            System.out.println("\tMissing /from argument");
            return false;
        } else if (toIndex == -1) {
            System.out.println("\tMissing '/to' field");
            return false;
        } else if (fromIndex == spaceIndex) {
            System.out.println("\tMissing task name!");
            return false;
        } else if (fromIndex > toIndex) {
            System.out.println("\tWrong order of arguments!");
            return false;

        } else if (toIndex + 4 > input.length()) {
            System.out.println("\tMissing /to argument");
            return false;
        } else if (fromIndex + 6 == toIndex) {
            System.out.println("\tMissing /from argument");
            return false;
        }
        return true;
    }

    private static boolean isValidDeadlineInput(String input) {
        int byIndex = input.indexOf("/by ");
        int spaceIndex = input.indexOf(" ");
        if (byIndex == -1 || byIndex + 4 > input.length()) {
            System.out.println("\tMissing deadline! ");
            return false;
        }
        if (spaceIndex + 1 == byIndex) {
            System.out.println("\tMissing task name!");
            return false;
        }
        return true;
    }

    public static String[] parseInput(String input) {
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
            break;

        case "deadline":
            int byIndex = input.indexOf("/by ");

            taskName = input.substring(spaceIndex + 1, byIndex - 1);
            String by = input.substring(byIndex + 4);
            parsed[1] = taskName;
            parsed[2] = by;
            break;

        case "event":
            int fromIndex = input.indexOf("/from ");
            int toIndex = input.indexOf("/to ");

            taskName = input.substring(spaceIndex + 1, fromIndex - 1);
            String from = input.substring(fromIndex + 6, toIndex - 1);
            String to = input.substring(toIndex + 4);
            parsed[1] = taskName;
            parsed[2] = from;
            parsed[3] = to;
            break;

        case "mark":
            //Fallthrough
        case "unmark":
            String taskNumber = input.substring(spaceIndex + 1);
            parsed[1] = taskNumber;
            break;
        }
        return parsed;
    }

    public static void mark(String[] arguments, boolean isDone) {
        if (isTasksListEmpty()) {
            return;
        }

        int taskNumber = Integer.parseInt(arguments[1]) - 1;
        Task taskToEdit = TASKS_LIST[taskNumber];

        if (isAlreadyMarked(isDone, taskToEdit) || isAlreadyUnmarked(isDone, taskToEdit)) {
            return;
        }

        changeTaskStatus(isDone, taskToEdit, taskNumber);
    }

    private static boolean isTasksListEmpty() {
        if (numberOfTasks == 0) {
            System.out.println("\tThere are no tasks in your list! Please add some tasks.");
            System.out.println("\t-------------------------------------------------------------------");
            return true;
        }
        return false;
    }

    private static void changeTaskStatus(boolean isDone, Task taskToEdit, int taskNumber) {
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

    private static boolean isAlreadyUnmarked(boolean isDone, Task taskToEdit) {
        if (!taskToEdit.isTaskDone() && !isDone) {
            System.out.println("\tTask is already unmarked!");
            System.out.println("\t-------------------------------------------------------------------");
            return true;
        }
        return false;
    }

    private static boolean isAlreadyMarked(boolean isDone, Task taskToEdit) {
        if (taskToEdit.isTaskDone() && isDone) {
            System.out.println("\tTask is already marked done!");
            System.out.println("\t-------------------------------------------------------------------");
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        printGreeting();
        inputLoop();
        printFarewell();
    }

    private static void inputLoop() {
        Scanner in = new Scanner(System.in);

        while (true) {
            String input = getInput(in);
            if (!isValidInput(input)) {
                continue;
            }
            String[] arguments = parseInput(input);
            String command = arguments[0];

            if (isBye(command)) {
                break;
            }
             botAction(arguments);
        }
    }

    private static boolean isBye(String command) {
        return command.equals("bye");
    }

    private static void botAction(String[] arguments) {
        String command = arguments[0];
        
        switch (command) {
        case "list":
            listTasks();
            break;
        case "mark":
            mark(arguments, true);
            break;
        case "unmark":
            mark(arguments, false);
            break;
        case "todo":
            //Fallthrough
        case "deadline":
            //Fallthrough
        case "event":
            addTask(arguments);
            break;
        }
    }

    private static String getInput(Scanner in) {
        String inputPrompt = "\tPlease input your desired action: ";
        System.out.println(inputPrompt);
        String input = in.nextLine().strip();
        System.out.println("\t-------------------------------------------------------------------");
        return input;
    }

    private static void printFarewell() {
        String farewell = "\tBye. Hope to see you again soon!\n"
                + "\t-------------------------------------------------------------------";
        System.out.print(farewell);
    }

    private static void printGreeting() {
        String greeting = "\t-------------------------------------------------------------------\n"
                + "\tHello! I'm CheeseBot\n"
                + "\tWhat can I do for you?\n"
                + "\t-------------------------------------------------------------------";
        System.out.println(greeting);
    }
}
