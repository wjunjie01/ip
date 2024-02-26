/**
 * Helper class used to parse input supplied by user into CheeseBot.
 */
public class Parser {
    public boolean isBye(String command) {
        return command.equals("bye");
    }

    /**
     * Parses the input supplied by the user into CheeseBot and returns all the components in the input.
     * Assumes that the input string passed into the function is of the correct form.
     *
     * @param input Input supplied by the user of CheeseBot.
     * @return An array of fixed size of 4, consisting of the parsed components.
     */
    public String[] parseInput(String input) {
        String[] parsed = new String[4];
        int spaceIndex = input.indexOf(" ");
        if (spaceIndex == -1) {
            parsed[0] = input;
            return parsed;
        }

        String command = input.substring(0, spaceIndex);
        String taskName;
        String keyword;
        parsed[0] = command;

        switch (command) {
        case "find":
            keyword = input.substring(spaceIndex + 1);
            parsed[1] = keyword;
            break;

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
            //Fallthrough
        case "delete":
            String taskNumber = input.substring(spaceIndex + 1);
            parsed[1] = taskNumber;
            break;
        }
        return parsed;
    }
    private static void validateSingleWordCommand(String input) throws InvalidInputException {
        // for case of single word commands with no space
        if (input.equals("list") || input.equals("bye") || input.equals("help")) {
            return;
        }
        // for commands where >1 arguments are needed
        throw new InvalidInputException("\tWrong command usage or no such command!");
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

    private static void validateIntegerInput(String input, int spaceIndex) throws InvalidInputException{
        int taskNumber = Integer.parseInt(input.substring(spaceIndex + 1)) - 1;
        if (taskNumber >= CheeseBot.tasksList.getNumberOfTasks()) {
            throw new InvalidInputException("\tInvalid number! Number must be less than the number of tasks ("
                    + CheeseBot.tasksList.getNumberOfTasks() + ").");
        }

        if (taskNumber < 0) {
            throw new InvalidInputException("\tInvalid number! Task number must be more than 0.");
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

    /**
     * Validates if the input string has a valid command. Then validates if input has the correct form according to the
     * type of command.
     * If input has valid command with a correct corresponding form, function returns normally.
     * Else, throws InvalidInputException.
     *
     * @param input Input supplied to CheeseBot
     * @throws InvalidInputException Throws InvalidInputException if there is not an exact number of expected arguments for
     * that particular command.
     */
    public void validateInput(String input) throws InvalidInputException {
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
            //Fallthrough
        case "find":
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
            //Fallthrough
        case "delete":
            validateIntegerInput(input, spaceIndex);
            return;

        default: // case where there is a space but command does not match any
            throw new InvalidInputException("\tNo such command!");
        }
    }
}
