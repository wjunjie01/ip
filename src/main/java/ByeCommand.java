public class ByeCommand implements Command {
    @Override
    public void execute(String input, TaskList taskList) {
        Duke.isExit = true;
    }
}
