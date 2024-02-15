package duke.parser;

import duke.command.*;
import duke.task.TaskList;
import duke.DukeException;

public class Parser {
    public static final int BEGIN_DEADLINE_INDEX = 9;
    public static final int BEGIN_EVENT_INDEX = 6;

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
        default:
            throw new DukeException("Not a valid command!!");
        }
    }

    public static Command parseDeleteTask(String input, TaskList taskList) throws DukeException {
        String[] inputArray = input.split(" ");

        int taskIndex;

        if (inputArray.length < 2) {
            throw new DukeException("Please specify a VALID task number to delete! "
                    + "Correct usage: mark [Task Number]");
        }

        try {
            taskIndex = Integer.parseInt(inputArray[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("Task number must be in integer! "
                    + "Correct usage: delete [Task Number]");
        }

        if ((taskIndex > taskList.taskList.size()) || (taskIndex <= 0)) {
            throw new DukeException("Please specify a task number that is within the list! "
                    + "Correct usage: delete [Task Number]");
        }
        return new DeleteCommand(taskIndex);
    }
    public static Command parseMarkTask(String input, TaskList taskList) throws DukeException {
        String[] inputArray = input.split(" ");

        if (inputArray.length < 2) {
            throw new DukeException("Please specify a task number to mark! "
                    + "Correct usage: mark [Task Number]");
        }

        int taskIndex;

        try {
            taskIndex = Integer.parseInt(inputArray[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("Task number must be in integer! "
                    + "Correct usage: mark [Task Number]");
        }

        if (taskIndex > taskList.taskList.size() || (taskIndex <= 0)) {
            throw new DukeException("Please specify a task number that is within the list! "
                    + "Correct usage: mark [Task Number]");
        }
        return new MarkCommand(taskIndex);
    }

    public static Command parseUnmarkTask(String input, TaskList taskList) throws DukeException {
        String[] inputArray = input.split(" ");

        if (inputArray.length < 2) {
            throw new DukeException("Please specify a task number to mark! "
                    + "Correct usage: unmark [Task Number]");
        }

        int taskIndex;

        try {
            taskIndex = Integer.parseInt(inputArray[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("duke.task.Task number must be in integer! "
                    + "Correct usage: unmark [Task Number]");
        }

        if (taskIndex > taskList.taskList.size() || (taskIndex <= 0)) {
            throw new DukeException("Please specify a task number that is within the list! "
                    + "Correct usage: unmark [Task Number]");
        }

        return new UnmarkCommand(taskIndex);
    }

    public static Command parseTodoTask(String input) throws DukeException {
        if (input.split(" ").length < 2){
            throw new DukeException("Your todo task cannot be empty! Correct usage: todo [Name of task]");
        }
        return new TodoCommand();
    }

    public static Command parseDeadlineTask(String input) throws DukeException {
        String taskInformation = input.substring(BEGIN_DEADLINE_INDEX);
        String[] taskInfoArray = taskInformation.split(" /by ");

        if (taskInfoArray[0].trim().isEmpty()){
            throw new DukeException("Your deadline task cannot be empty! "
                    + "Correct usage: deadline [Name of task] /by [duke.task.Deadline]");
        }

        if (taskInfoArray.length < 2){
            throw new DukeException("You deadline task must include the '/by' keyword! "
                    + "Correct usage: deadline [Name of task] /by [duke.task.Deadline]");
        }

        if (taskInfoArray[1].trim().isEmpty()){
            throw new DukeException("Your deadline cannot be empty! "
                    + "Correct usage: deadline [Name of task] /by [duke.task.Deadline]");
        }

        return new DeadlineCommand(taskInfoArray[0], taskInfoArray[1]);
    }

    public static Command parseEventTask(String input) throws DukeException {
        String taskInformation = input.substring(BEGIN_EVENT_INDEX);
        String[] fromSplitArray = taskInformation.split(" /from ");

        if (fromSplitArray.length < 2 || !fromSplitArray[1].contains(" /to ")) {
            throw new DukeException("Your event task must include /from and /to ! "
                    + "Correct usage: event [Name of task] /from [Start date] /to [End date]");
        }

        String[] toSplitArray = fromSplitArray[1].split(" /to ");
        String taskDescription = fromSplitArray[0].trim();
        String startDate = toSplitArray[0].trim();
        String endDate = toSplitArray[1].trim();

        if (startDate.isEmpty() || endDate.isEmpty() || taskDescription.isEmpty()) {
            throw new DukeException("You have a missing field, in either your task, start date or end date! "
                    + "Correct usage: event [Name of task] /from [Start date] /to [End date]");
        }

        return new EventCommand(taskDescription, startDate, endDate);
    }
    
}
