import java.util.Arrays;
import java.util.Scanner;

public class CheeseBot {
    private static final String[] OPTIONS = {"list", "mark ?", "unmark ?", "bye"};
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
            String taskName = currentTask.getTaskName();
            if (!currentTask.isTaskDone()) {
                isAllTasksDone = false;
            }
            System.out.println("\t" + (i + 1) + ".[" + currentTask.getStatusIcon() + "] " + taskName);
        }

        if (isAllTasksDone) {
            System.out.println("\tExcellent! You have completed all your tasks!");
        }
        System.out.println("\t-------------------------------------------------------------------");
    }

    public static void addItems(String input) {
        Task newTask = new Task(input);
        TASKS_LIST[numberOfTasks] = newTask;
        numberOfTasks++;
        System.out.println("\tYou have added: " + newTask.getTaskName());
        System.out.println("\tYou have a total of " + numberOfTasks + " completed and uncompleted tasks.");
        System.out.println("\t-------------------------------------------------------------------");
    }

    public static void mark(String input, boolean isDone) {
        if (numberOfTasks == 0) {
            System.out.println("\tThere are no tasks in your list! Please add some tasks.");
            System.out.println("\t-------------------------------------------------------------------");
            return;
        }

        int spaceIndex = input.indexOf(" ");
        if (spaceIndex == -1) {
            System.out.println("\tInvalid format! Please use command with a space and a number");
            System.out.println("\t-------------------------------------------------------------------");
            return;
        }

        try {
            int taskNumber = Integer.parseInt(input.substring(spaceIndex + 1)) - 1;

            if (taskNumber >= numberOfTasks) {
                System.out.println("\tInvalid number! Number must be less than the number of tasks ("
                        + numberOfTasks + ").");
                System.out.println("\t-------------------------------------------------------------------");
                return;
            } else if (taskNumber < 0) {
                System.out.println("\tInvalid number! Task number must be more than 0.");
                System.out.println("\t-------------------------------------------------------------------");
                return;
            }

            Task taskToEdit = TASKS_LIST[taskNumber];
            if (taskToEdit.isTaskDone() && isDone) { //checks if a completed task is to be marked again
                System.out.println("\tTask is already marked done!");
                return;
            } else if (!taskToEdit.isTaskDone() && !isDone) { // checks if an uncompleted task is to be unmarked
                System.out.println("\tTask is already unmarked!");
                return;
            }

            taskToEdit.setTaskDone(isDone);
            if (isDone) {
                System.out.println("\tWell done, you are one step closer to finishing your tasks!");
                System.out.println("\tI've marked this task done for you:");
                System.out.println("\t\t [X] " + taskToEdit.getTaskName());
            } else {
                System.out.println("\tNo worries, let's do our best!");
                System.out.println("\tI've unmarked this task done for you:");
                System.out.println("\t\t [ ] " + taskToEdit.getTaskName());
            }

        } catch (NumberFormatException e) {
            System.out.println("\tInvalid format! Please input a number between 1 and the number of tasks ("
                    + numberOfTasks + ").");
        } finally {
            System.out.println("\t-------------------------------------------------------------------");
        }
    }

    public static void main(String[] args) {
        String greeting = "\t-------------------------------------------------------------------\n"
                + "\tHello! I'm CheeseBot\n"
                + "\tWhat can I do for you?\n"
                + "\t-------------------------------------------------------------------";
        System.out.println(greeting);

        Scanner in = new Scanner(System.in);
        String inputPrompt = "\tPlease key in one of the options: "
                + Arrays.toString(OPTIONS)
                + "\n\tOr simply type your task description to add (Please start task name with capital letter):";

        System.out.println(inputPrompt);
        String input = in.nextLine();
        System.out.println("\t-------------------------------------------------------------------");

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                listItems();
            } else if (input.startsWith("mark")) {
                mark(input, true);
            } else if (input.startsWith("unmark")) {
                mark(input, false);
            } else if (input.isEmpty()) {
                System.out.println("\tPlease enter a command!");
                System.out.println("\t-------------------------------------------------------------------");
            } else {
                addItems(input);
            }
            System.out.println(inputPrompt);
            input = in.nextLine();
            System.out.println("\t-------------------------------------------------------------------");
        }

        String farewell = "\tBye. Hope to see you again soon!\n"
                + "\t-------------------------------------------------------------------";
        System.out.print(farewell);
    }
}
