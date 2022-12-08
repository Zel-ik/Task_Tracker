package manager;

import linckedList.CustomLinkedList;
import linckedList.Node;
import task.EpicTask;
import task.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryHistoryManager implements HistoryManager {
    protected Map<Integer, Node<Task>> tasksHistory = new HashMap<>();
    protected CustomLinkedList<Task> customLinkedList = new CustomLinkedList<>();


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
    void getHistoryTest() {
        EpicTask epicTask = new EpicTask("epic", "des");
        add(epicTask);

        Assertions.assertEquals(1, customLinkedList.getTasks().size());

    }

}
