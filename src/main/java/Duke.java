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
        String[]list = new String[100];
        int wordCount = 0;
        String input;

        while (true){
            input = in.nextLine();
            if (input.equals("bye")){
                break;
            }
            if (input.equals("list")){
                String[]addedTaskList = Arrays.copyOf(list, wordCount);
                for (int i = 0; i < wordCount; i++){
                    System.out.println((i+1) + ". " + addedTaskList[i]);
                }
                System.out.println(line);
            }
            else{
                System.out.println("added: " + input);
                System.out.println(line);
                list[wordCount] = input;
                wordCount += 1;
            }
        }

        System.out.println("Goodbye! See you again!");
        System.out.println(line);

    }
}
