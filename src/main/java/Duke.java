import java.util.Arrays;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        //ASCII art
        String logo ="       __                 __ \n" +
                "      / /___ _____  ___  / /_\n" +
                " __  / / __ `/ __ \\/ _ \\/ __/\n" +
                "/ /_/ / /_/ / / / /  __/ /_  \n" +
                "\\____/\\__,_/_/ /_/\\___/\\__/  \n" +
                "                             \n";
        String line = "------------";


        System.out.println(logo);
        System.out.println("Hi! I'm Janet, your personal assistant.");
        System.out.println("What is your request?");
        System.out.println(line);

        Scanner in = new Scanner(System.in);
        Task[]list = new Task[100]; //change to task
        int taskCount = 0;
        String input;

        while (true){
            input = in.nextLine();
            if (input.equals("bye")){
                break;
            }

            if (input.split(" ")[0].equals("mark")){
                String taskNumDone = input.split(" ")[1];
                int markIndex = Integer.parseInt(taskNumDone) - 1;
                list[markIndex].markTask();
                System.out.println("Well done! This task is now done: ");
                System.out.println(list[markIndex].toString());
                System.out.println(line);
                continue;
            }

            if (input.equals("list")){
                //String[]addedTaskList = Arrays.copyOf(list, taskCount);
                System.out.println("The tasks in your list are: ");
                for (int i = 0; i < taskCount; i++){
                    System.out.println((i+1) + "." + list[i].toString());
                }
                System.out.println(line);
            }
            else{
                System.out.println("added: " + input);
                System.out.println(line);
                //add a new task to the list by use of constructor
                list[taskCount] = new Task(input);
                taskCount += 1;
            }
        }

        System.out.println("Goodbye! See you again!");
        System.out.println(line);

    }
}
