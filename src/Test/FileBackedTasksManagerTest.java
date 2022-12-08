package Test;

import Manager.FileBackedTasksManager;

public class FileBackedTasksManagerTest extends TaskManagerTest<FileBackedTasksManager> {

    public FileBackedTasksManagerTest() {
        manager = new FileBackedTasksManager(".idea/Saver.csv");
    }
}
