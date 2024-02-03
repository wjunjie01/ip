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
            String[] inputArray = input.split(" ");
            String inputDescription = input.substring(firstWord.length());

            switch (firstWord) {
            case "bye": {
                break reqUserInput;
            }
            case "mark": {
                Parser.parseMarkTask(input, list);
                break;
            }
            case "unmark": {
                Parser.parseUnmarkTask(input, list);
                break;
            }
            case "list": {
                Ui.printListTasks(list);
                break;
            }
            case "todo": {
                Parser.parseTodoTask(inputDescription, list);
                break;
            }
            case "deadline": {
                Parser.parseDeadlineTask(inputDescription, list);
                break;
            }
            case "event": {
                Parser.parseEventTask(inputDescription, list);
                break;
            }
            }
        }

        Ui.printGoodbyeMessage();
    }
}
