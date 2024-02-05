public class Duke {
    public static boolean isExit;
    public TaskList taskList;
    public Duke(){
        isExit = false;
        taskList = new TaskList();
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        Ui.printWelcomeMessage();

        while (!isExit) {
            try {
                String input = Ui.readUserInput();
                Command command = Parser.parse(input, duke.taskList);
                command.execute(input, duke.taskList);
            } catch (DukeException e) {
                Ui.printLine();
                System.out.println(e.getMessage());
                Ui.printLine();
            }
        }
        Ui.printGoodbyeMessage();
    }
}
