package duke.storage;

import duke.DukeException;
import duke.task.Task;
import duke.task.TaskList;

import java.io.*;

/**
 * Class contains methods to load data from an external file,
 * and save the data to an external file.
 */
public class Storage {
    private String filePath;

    //Default file path given in main is "data/duke.txt"
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads data from the file specified by the file path.
     * In the absence of the file, creates a new file in the file path.
     *
     * @return TaskList
     * @throws IOException
     * @throws DukeException
     */
    public TaskList loadTaskList() throws IOException, DukeException {
        TaskList taskList = new TaskList();
        String line;

        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));

            while ((line = br.readLine()) != null) {
                //Parses the line to extract out the task from the file format
                Task task = Task.loadFromFile(line);
                taskList.addTask(task);
            }
            br.close();
        } catch (FileNotFoundException e) {
            //Creates a file if there's no file found
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        return taskList;
    }

    /**
     * Saves the task list to the file.
     *
     * @param taskList
     * @throws DukeException
     */
    public void saveTask(TaskList taskList) throws DukeException {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));

            for (Task task : taskList.taskList) {
                //Converts each task to the appropriate file format to be written in the file.
                bw.write(task.toFileFormat());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            throw new DukeException("Error saving task!");
        }
    }
}
