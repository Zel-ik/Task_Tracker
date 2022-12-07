package Manager;

import LinckedList.*;
import Task.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryHistoryManager implements HistoryManager {
    Map<Integer, Node<Task>> tasksHistory = new HashMap<>();
    CustomLinkedList<Task> customLinkedList = new CustomLinkedList<>();


    @Override
    public void add(Task task) {
        Node<Task> node = new Node<>(task);
        customLinkedList.linkedListAdd(node);
        tasksHistory.put(task.getId(), node);

    }

    @Override
    public void remove(int id) {
        customLinkedList.removeNode(tasksHistory.get(id));
        tasksHistory.remove(id);

    }

    @Override
    public List<Task> getHistory() {
        return customLinkedList.getTasks();
    }


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

    @Test
    void getHistoryTest(){
        EpicTask epicTask = new EpicTask("epic","des");
        add(epicTask);

        Assertions.assertEquals(1, customLinkedList.getTasks().size());

    }

}
