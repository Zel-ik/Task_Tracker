package Test;

import Manager.FileBackedTasksManager;
import Manager.TaskManagerTest;

public class FileBackedTasksManagerTest extends TaskManagerTest<FileBackedTasksManager> {

    public FileBackedTasksManagerTest() {
        manager = new FileBackedTasksManager(".idea/Saver.csv");
    }
}
