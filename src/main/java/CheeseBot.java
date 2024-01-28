import javax.sound.midi.SysexMessage;
import java.util.Scanner;

public class CheeseBot {
    public static void main(String[] args) {
        String greeting = "\t----------------------------------\n" +
                "\tHello! I'm CheeseBot\n" +
                "\tWhat can I do for you?\n" +
                "\t----------------------------------";
        System.out.println(greeting);

        String input;
        Scanner in = new Scanner(System.in);
        input = in.nextLine();
        while (!input.equals("bye")) {
            System.out.println("\t----------------------------------");
            System.out.println("\t" + input);
            System.out.println("\t----------------------------------");
            input = in.nextLine();
        }

        String farewell = "\t----------------------------------\n" +
                "\tBye. Hope to see you again soon!\n" +
                "\t----------------------------------";
        System.out.print(farewell);
    }
}
