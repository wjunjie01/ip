import javax.sound.midi.SysexMessage;
import java.util.Scanner;

public class CheeseBot {
    private static int numberOfTasks = 0;
    private static String[] inputStore = new String[100];
    public static void listItems() {
        String output;
        System.out.println("\t----------------------------------");
        for (int i = 0; i < numberOfTasks; i++) {
            output = "\t" + (i + 1) + ". " + inputStore[i];
            System.out.println(output);
        }
        System.out.println("\t----------------------------------");
    }

    public static void addItems(String input) {
        inputStore[numberOfTasks] = input;
        System.out.println("\t----------------------------------");
        System.out.println("\tadded: " + input);
        System.out.println("\t----------------------------------");
        numberOfTasks++;
    }
    public static void main(String[] args) {
        String greeting = "\t----------------------------------\n" +
                "\tHello! I'm CheeseBot\n" +
                "\tWhat can I do for you?\n" +
                "\t----------------------------------";
        System.out.println(greeting);

        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                listItems();
            } else {
                addItems(input);
            }
            input = in.nextLine();
        }

        String farewell = "\t----------------------------------\n" +
                "\tBye. Hope to see you again soon!\n" +
                "\t----------------------------------";
        System.out.print(farewell);
    }
}
