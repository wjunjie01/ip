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
            System.out.println("\t\t" + (i + 1) + "." + currentTask);
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

    public static void validateInput(String input) throws InvalidInputException {
        if (input.isEmpty()) {
            throw new InvalidInputException("\tInput is empty! Please type something.");
        }

        int spaceIndex = input.indexOf(" ");
        if (spaceIndex == -1) {
            validateSingleWordCommand(input);
            return;
        }

        String command = input.substring(0, spaceIndex);
        switch (command) {
        case "todo":
            return;

        case "deadline":
            validateDeadlineInput(input);
            return;

        case "event":
            validateEventInput(input);
            return;

        case "mark":
            //Fallthrough
        case "unmark":
            validateMarkAndUnmarkInput(input, spaceIndex);
            return;

        default: // case where there is a space but command does not match any
            throw new InvalidInputException("\tNo such command!");
        }
    }

    private static void validateSingleWordCommand(String input) throws InvalidInputException {
        // for case of single word commands with no space
        if (input.equals("list") || input.equals("bye") || input.equals("help")) {
            return;
        }
        // for commands where >1 arguments are needed
        throw new InvalidInputException("\tWrong command usage or no such command!");
    }

    private static void validateMarkAndUnmarkInput(String input, int spaceIndex) throws InvalidInputException{
        int taskNumber = Integer.parseInt(input.substring(spaceIndex + 1)) - 1;
        if (taskNumber >= numberOfTasks) {
            throw new InvalidInputException("\tInvalid number! Number must be less than the number of tasks ("
                    + numberOfTasks + ").");
        }

        if (taskNumber < 0) {
            throw new InvalidInputException("\tInvalid number! Task number must be more than 0.");
        }
    }

    private static void validateEventInput(String input) throws InvalidInputException{
        int spaceIndex = input.indexOf(" ");
        int fromIndex = input.indexOf("/from");
        int toIndex = input.indexOf("/to");

        if (fromIndex == spaceIndex + 1) {
            throw new InvalidInputException("\tMissing task name!");

        } else if (fromIndex == -1) {
            throw new InvalidInputException("\tMissing '/from' flag!");

        } else if (fromIndex + 6 > input.length() || fromIndex + 6 == toIndex) {
            throw new InvalidInputException("\tMissing /from argument!");

        } else if (toIndex == -1) {
            throw new InvalidInputException("\tMissing '/to' flag!");

        } else if (toIndex + 4 > input.length()) {
            throw new InvalidInputException("\tMissing '/to' argument!");

        } else if (fromIndex > toIndex) {
            throw new InvalidInputException("\tWrong order of flags!");
        }
    }

    private static void validateDeadlineInput(String input) throws InvalidInputException{
        int byIndex = input.indexOf("/by");
        int spaceIndex = input.indexOf(" ");

        if (spaceIndex + 1 == byIndex) {
            throw new InvalidInputException("\tMissing task name!");
        }
        if (byIndex == -1 ) {
            throw new InvalidInputException("\tMissing '/by' flag!");
        }
        if (byIndex + 4 > input.length()) {
            throw new InvalidInputException("\tMissing deadline!");
        }
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
            try {
                String input = getInput(in);
                validateInput(input);

                String[] arguments = parseInput(input);
                String command = arguments[0];

                if (isBye(command)) {
                    break;
                }
                botAction(arguments);
            } catch (InvalidInputException e) {
                // No action required. Just catch the exception.
            }
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
        case "help":
            printCommandList();
        }
    }

    private static void printCommandList() {
        System.out.println("\tAvailable commands: list, mark, unmark, todo, deadline, event, help, bye\n");
        System.out.println("\tCommand 'list' usage: list\n" +
                "\tFunction: Lists all recorded tasks.\n");

        System.out.println("\tCommand 'mark' usage: mark <task number>\n" +
                "\tFunction: Marks <task number> as completed.\n");

        System.out.println("\tCommand 'unmark' usage: unmark <task number>\n" +
                "\tFunction: Marks <task number> as not completed.\n");

        System.out.println("\tCommand 'todo' usage: todo <task name>\n" +
                "\tFunction: Adds a to do task with no deadline.\n");

        System.out.println("\tCommand 'deadline' usage: deadline <task name> /by <deadline>\n" +
                "\tFunction: Adds a to do task with a deadline.\n");

        System.out.println("\tCommand 'event' usage: event <event name> /from <start time> /to <end time>\n" +
                "\tFunction: Adds an event with the start and end timings.\n");

        System.out.println("\tCommand 'help' usage: help\n" +
                "\tFunction: Lists the available command, along with its usage and functionality.\n");

        System.out.println("\tCommand 'bye' usage: bye\n" +
                "\tFunction: Ends session with CheeseBot.");

        System.out.println("\t-------------------------------------------------------------------");


    }

    private static String getInput(Scanner in) {
        String inputPrompt = "\tPlease input your desired action or type 'help' for a list of commands: ";
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
