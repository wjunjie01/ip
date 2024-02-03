public class Parser {
    static String taskDescription;

    public static void parseMarkTask(String input, Task[] list){
        String taskNumDone = input.split(" ")[1];
        int markIndex = Integer.parseInt(taskNumDone) - 1;
        list[markIndex].markTask();
        Ui.printMarkTask(markIndex, list);
    }

    public static void parseUnmarkTask(String input, Task[] list){
        String taskNumDone = input.split(" ")[1];
        int markIndex = Integer.parseInt(taskNumDone) - 1;
        list[markIndex].unmarkTask();
        Ui.printUnmarkTask(markIndex, list);
    }

    public static void parseTodoTask(String inputDescription, Task[] list) {
        taskDescription = inputDescription;
        list[Task.getTaskCount()] = new Todo(taskDescription);
        Ui.printAddTask(taskDescription);
    }

    public static void parseDeadlineTask(String inputDescription, Task[] list) {
        int slashIndex = inputDescription.indexOf(" /by ");
        taskDescription = inputDescription.substring(0, slashIndex);
        String by = inputDescription.substring(slashIndex + 5);
        list[Task.getTaskCount()] = new Deadline(taskDescription, by);
        Ui.printAddTask(taskDescription);
    }

    public static void parseEventTask(String inputDescription, Task[] list) {
        int fromSlash = inputDescription.indexOf(" /from ");
        int toSlash = inputDescription.indexOf(" /to ");
        taskDescription = inputDescription.substring(0, fromSlash);
        String from = inputDescription.substring(fromSlash + 7, toSlash);
        String to = inputDescription.substring(toSlash + 5);
        list[Task.getTaskCount()] = new Event(taskDescription, from, to);
        Ui.printAddTask(taskDescription);
    }

    //public static void parse()
}
