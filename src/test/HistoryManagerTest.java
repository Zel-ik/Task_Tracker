package test;

import manager.InMemoryHistoryManager;
import task.EpicTask;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HistoryManagerTest extends InMemoryHistoryManager {
    @Test
    void emptyHistoryTest(){
        Assertions.assertEquals(0,getHistory().size());
    }

    @Test
    void addTest() {
        EpicTask epicTask = new EpicTask("epic","des");
        add(epicTask);

        Assertions.assertNotNull(tasksHistory);
        Assertions.assertEquals(1,tasksHistory.size());
    }

    @Test
    void removeTest(){
        EpicTask epicTask = new EpicTask("epic","des");
        add(epicTask);
        remove(0);

        Assertions.assertEquals(0,tasksHistory.size());
    }

    @Test
    void removeMiddleAndLastTest(){
        EpicTask epicTask1 = new EpicTask("epic","des");
        EpicTask epicTask2 = new EpicTask("epic","des");
        EpicTask epicTask3 = new EpicTask("epic","des");
        epicTask1.setId(1);
        epicTask2.setId(2);
        epicTask3.setId(3);

        add(epicTask1);
        add(epicTask2);
        add(epicTask3);

        remove(1);

        Assertions.assertEquals(epicTask1, getHistory().get(0));
        Assertions.assertEquals(epicTask3,getHistory().get(1));
        Assertions.assertEquals(2,getHistory().size());

        remove(2);

        Assertions.assertEquals(epicTask1, getHistory().get(0));
        Assertions.assertEquals(1,getHistory().size());


    }
}
