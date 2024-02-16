package duke.storage;

import duke.DukeException;
import duke.task.Task;
import duke.task.TaskList;

import java.io.*;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public TaskList loadTaskList() throws IOException, DukeException {
        TaskList taskList = new TaskList();
        String line;

        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            while ((line = br.readLine()) != null) {
                Task task = Task.loadFromFile(line);
                System.out.println(task);
                taskList.addTask(task);
            }
            br.close();
        } catch (FileNotFoundException e) {
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        return taskList;
    }

    public void saveTask(TaskList taskList) throws DukeException {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));

            for (Task task : taskList.taskList) {
                bw.write(task.toFileFormat());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            throw new DukeException("Error saving task!");
        }
    }
}
