package Manager;

import Task.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager{
    private List<Task> historyList = new ArrayList<>();


    @Override
    public void add(Task task) {
        historyList.add(task);

    }

    @Override
    public List<Task> getHistory() {
        return historyList;
    }
}
