//Coding standard is according to: https://se-education.org/guides/conventions/java/basic.html

import java.util.Scanner;
public class Duke {

    public static final String LINE = "---------------";
    public static final int MAX_TASKS = 100;

    public static void main(String[] args) {
        Ui.printWelcomeMessage();

        Scanner in = new Scanner(System.in);
        Task[]list = new Task[MAX_TASKS];
        String input;
        String firstWord;

        reqUserInput:
        while (true){
            input = in.nextLine();
            firstWord = input.split(" ")[0];

            switch (firstWord) {
            case "bye": {
                break reqUserInput;
            }
            case "mark": {
                executeMarkTask(input, list);
                break;
            }
            case "unmark": {
                String taskNumDone = input.split(" ")[1];
                int markIndex = Integer.parseInt(taskNumDone) - 1;
                list[markIndex].unmarkTask();
                System.out.println("Boo! This task is not done yet! ");
                System.out.println(list[markIndex].toString());
                System.out.println(LINE);
                break;
            }
            case "list":
                System.out.println("The tasks in your list are: ");
                for (int i = 0; i < Task.getTaskCount(); i++) {
                    System.out.println((i + 1) + "." + list[i].toString());
                }
                System.out.println(LINE);
                break;
            default:  //Adds a new task - todo,deadline, event
                String[] inputArray = input.split(" ");
                String taskType = inputArray[0];
                String inputDescription = input.substring(taskType.length() + 1);
                String taskDescription = "";

                switch (taskType) {
                case "todo":
                    taskDescription = inputDescription;
                    list[Task.getTaskCount()] = new Todo(taskDescription);
                    break;
                case "deadline":
                    int slashIndex = inputDescription.indexOf(" /by ");
                    taskDescription = inputDescription.substring(0, slashIndex);
                    String by = inputDescription.substring(slashIndex + 5);
                    list[Task.getTaskCount()] = new Deadline(taskDescription, by);
                    break;

                case "event":
                    int fromSlash = inputDescription.indexOf(" /from ");
                    int toSlash = inputDescription.indexOf(" /to ");
                    taskDescription = inputDescription.substring(0, fromSlash);
                    String from = inputDescription.substring(fromSlash + 7, toSlash);
                    String to = inputDescription.substring(toSlash + 5);
                    list[Task.getTaskCount()] = new Event(taskDescription, from, to);
                    break;
                }

                System.out.println("Task added: " + taskDescription);
                System.out.println("You now have " + Task.getTaskCount() + " task(s) in the list.");
                System.out.println(LINE);
                break;
            }
        }

        Ui.printGoodbyeMessage();
    }
    private static void executeMarkTask(String input, Task[] list) {
        String taskNumDone = input.split(" ")[1];
        int markIndex = Integer.parseInt(taskNumDone) - 1;
        list[markIndex].markTask();
        System.out.println("Well done! This task is now done: ");
        System.out.println(list[markIndex].toString());
        System.out.println(LINE);
    }
}
