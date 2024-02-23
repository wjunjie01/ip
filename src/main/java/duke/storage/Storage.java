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
    private final String FILE_PATH;

    //Default file path given in main is "data/duke.txt"
    public Storage(String filePath) {
        this.FILE_PATH = filePath;
    }

    /**
     * Loads data from the file specified by the file path.
     * In the absence of the file, creates a new file in the file path.
     *
     * @return TaskList The existing task list that stores the tasks.
     * @throws IOException Exception is thrown when there is an input / output error.
     * @throws DukeException Exception is thrown when there is a file format error.
     */
    public TaskList loadTaskList() throws IOException, DukeException {
        TaskList taskList = new TaskList();
        String line;

        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));

            while ((line = br.readLine()) != null) {
                //Parses the line to extract out the task from the file format
                //Exception is thrown in this method if the file format is not correct.
                Task task = Task.loadFromFile(line);
                taskList.addTask(task);
            }
            br.close();
        } catch (FileNotFoundException e) {
            //Creates a file if there's no file found
            File file = new File(FILE_PATH);
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        return taskList;
    }

    /**
     * Saves the task list to the file.
     *
     * @param taskList The existing task list that stores the tasks.
     * @throws DukeException Exception is thrown when there is an input / output error.
     */
    public void saveTask(TaskList taskList) throws DukeException {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH));

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
