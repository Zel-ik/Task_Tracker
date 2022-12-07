package Test;

import Manager.InMemoryTaskManager;
import Manager.TaskManagerTest;

public class InMemoryTaskManagerTest extends TaskManagerTest<InMemoryTaskManager> {

    public InMemoryTaskManagerTest() {
        manager = new InMemoryTaskManager();
    }
}
