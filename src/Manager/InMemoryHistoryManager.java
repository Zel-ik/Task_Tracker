package Manager;

import LinckedList.*;
import Task.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryHistoryManager<E> implements HistoryManager{
    Map<Integer, Node<Task>> tasksHistory = new HashMap<>();
    CustomLinkedList<Task> customLinkedList = new CustomLinkedList<>();


    @Override
    public void add(Task task ) {
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

}
