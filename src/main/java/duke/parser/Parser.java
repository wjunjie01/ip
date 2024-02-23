package duke.parser;

import duke.command.*;
import duke.task.TaskList;
import duke.DukeException;

/**
 * Parser class parses the inputs provided by the user.
 * Matches it to the corresponding command to be executed.
 */
public class Parser {
    /**
     * Parses input provided by the user.
     * Matches it to the appropriate parser method / command.
     * And throws an error if there is any error in the inputs.
     *
     * @param input The user's inputs.
     * @param taskList The existing task list that stores the tasks.
     * @return Command The relevant command to be executed.
     * @throws DukeException Exception thrown when the user's command is not valid.
     */
    public static Command parse(String input, TaskList taskList) throws DukeException {
        String firstWord = input.split(" ")[0];

        switch (firstWord) {
        case "bye":
            return new ByeCommand();
        case "mark":
            return parseMarkTask(input, taskList);
        case "unmark":
            return parseUnmarkTask(input, taskList);
        case "list":
            return new ListCommand();
        case "todo":
            return parseTodoTask(input);
        case "deadline":
            return parseDeadlineTask(input);
        case "event":
            return parseEventTask(input);
        case "delete":
            return parseDeleteTask(input, taskList);
        case "find":
            return parseFindTask(input);
        default:
            throw new DukeException("Not a valid command!!");
        }
    }

    /**
     * Parses the input under the find function to check for errors.
     * Returns the find command to find the keyword in the task list.
     *
     * @param input The user's inputs to be checked for the correct format.
     * @return Command Find command to be executed.
     * @throws DukeException Exception thrown when field is empty.
     */
    public static Command parseFindTask(String input) throws DukeException {
        String[] inputArray = input.split(" ", 2);
        if (inputArray[1].trim().isEmpty()) {
            throw new DukeException("You must specify a keyword to find! "
                    + "Correct usage: find [keyword]");
        }

        return new FindCommand(inputArray[1]);
    }

    /**
     * Parses the input under the delete function to check for errors.
     * Returns the delete command to be executed with the task index to be deleted.
     *
     * @param input The user's inputs to be checked for the correct format.
     * @param taskList The existing task list that stores the tasks.
     * @return Command Delete command to be executed.
     * @throws DukeException Exception thrown when field is empty.
     */
    public static Command parseDeleteTask(String input, TaskList taskList) throws DukeException {
        String[] inputArray = input.split(" ");

        int taskIndex;

        if (inputArray.length < 2) {
            throw new DukeException("Please specify a VALID task number to delete! "
                    + "Correct usage: mark [task number]");
        }

        try {
            taskIndex = Integer.parseInt(inputArray[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("Task number must be in integer! "
                    + "Correct usage: delete [task number]");
        }

        if ((taskIndex > taskList.taskList.size()) || (taskIndex <= 0)) {
            throw new DukeException("Please specify a task number that is within the list! "
                    + "Correct usage: delete [task number]");
        }
        return new DeleteCommand(taskIndex);
    }

    /**
     * Parses the input under the mark function to check for errors.
     * Returns the mark command to be executed with the task index to be marked.
     *
     * @param input The user's inputs to be checked for the correct format.
     * @param taskList The existing task list that stores the tasks.
     * @return Command Mark command to be executed.
     * @throws DukeException Exception thrown when the field is empty or erroneous.
     */
    public static Command parseMarkTask(String input, TaskList taskList) throws DukeException {
        String[] inputArray = input.split(" ");

        if (inputArray.length < 2) {
            throw new DukeException("Please specify a task number to mark! "
                    + "Correct usage: mark [task number]");
        }

        int taskIndex;

        try {
            taskIndex = Integer.parseInt(inputArray[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("Task number must be in integer! "
                    + "Correct usage: mark [task number]");
        }

        if (taskIndex > taskList.taskList.size() || (taskIndex <= 0)) {
            throw new DukeException("Please specify a task number that is within the list! "
                    + "Correct usage: mark [task number]");
        }
        return new MarkCommand(taskIndex);
    }

    /**
     * Parses the input under the unmark function to check for errors.
     * Returns the unmark command to be executed with the task index to be unmark.
     *
     * @param input The user's inputs to be checked for the correct format.
     * @param taskList The existing task list that stores the tasks.
     * @return Command Unmark command to be executed.
     * @throws DukeException Exception thrown when the field is empty or erroneous.
     */
    public static Command parseUnmarkTask(String input, TaskList taskList) throws DukeException {
        String[] inputArray = input.split(" ");

        if (inputArray.length < 2) {
            throw new DukeException("Please specify a task number to mark! "
                    + "Correct usage: unmark [task number]");
        }

        int taskIndex;

        try {
            taskIndex = Integer.parseInt(inputArray[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("duke.task.Task number must be in integer! "
                    + "Correct usage: unmark [task number]");
        }

        if (taskIndex > taskList.taskList.size() || (taskIndex <= 0)) {
            throw new DukeException("Please specify a task number that is within the list! "
                    + "Correct usage: unmark [task number]");
        }

        return new UnmarkCommand(taskIndex);
    }

    /**
     * Parses the input under the Todo function to check for errors.
     * Returns the Todo command.
     *
     * @param input The user's inputs to be checked for the correct format.
     * @return Command Todo command to be executed.
     * @throws DukeException Exception thrown when the field is empty.
     */
    public static Command parseTodoTask(String input) throws DukeException {
        String[] inputArray = input.split(" ", 2);
        if (inputArray.length < 2) {
            throw new DukeException("Your todo task cannot be empty! Correct usage: todo [name of task]");
        }
        return new TodoCommand(inputArray[1]);
    }

    /**
     * Parses the input under the deadline function to check for errors.
     * Returns the deadline command to be executed with the task and the deadline.
     *
     * @param input The user's inputs to be checked for the correct format.
     * @return Command Deadline command to be executed.
     * @throws DukeException Exception thrown when the field is empty or erroneous.
     */
    public static Command parseDeadlineTask(String input) throws DukeException {
        String[] taskInformation = input.split(" ", 2);
        String[] taskInfoArray = taskInformation[1].split(" /by ");

        if (taskInfoArray[0].trim().isEmpty()) {
            throw new DukeException("Your deadline task cannot be empty! "
                    + "Correct usage: deadline [name of task] /by [due date]");
        }

        if (taskInfoArray.length < 2) {
            throw new DukeException("You deadline task must include the '/by' keyword! "
                    + "Correct usage: deadline [name of task] /by [due date]");
        }

        if (taskInfoArray[1].trim().isEmpty()) {
            throw new DukeException("Your deadline cannot be empty! "
                    + "Correct usage: deadline [name of task] /by [due date]");
        }

        return new DeadlineCommand(taskInfoArray[0], taskInfoArray[1]);
    }

    /**
     * Parses the input under the event function to check for errors.
     * Returns the event command to be executed with the task, 'from' date and 'to' date.
     *
     * @param input The user's inputs to be checked for the correct format.
     * @return Command Event command to be executed.
     * @throws DukeException Exception thrown when the field is empty or erroneous.
     */
    public static Command parseEventTask(String input) throws DukeException {
        String[] taskInformation = input.split(" ", 2);
        String[] fromSplitArray = taskInformation[1].split(" /from ", 2);

        if (fromSplitArray.length < 2 || !fromSplitArray[1].contains(" /to ")) {
            throw new DukeException("Your event task must include /from and /to ! "
                    + "Correct usage: event [name of task] /from [start date] /to [end date]");
        }

        String[] toSplitArray = fromSplitArray[1].split(" /to ", 2);
        String taskDescription = fromSplitArray[0].trim();
        String startDate = toSplitArray[0].trim();
        String endDate = toSplitArray[1].trim();

        if (startDate.isEmpty() || endDate.isEmpty() || taskDescription.isEmpty()) {
            throw new DukeException("You have a missing field, in either your task, start date or end date! "
                    + "Correct usage: event [name of task] /from [start date] /to [end date]");
        }

        return new EventCommand(taskDescription, startDate, endDate);
    }
}
