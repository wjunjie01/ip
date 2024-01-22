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
        String input = in.nextLine();

        while (!input.equals("bye")) {
            System.out.println(input);
            input = in.nextLine();
        }

        System.out.println("Goodbye! See you again!");

    }
}
