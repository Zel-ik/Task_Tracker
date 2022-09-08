package Manager;

import Task.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTaskManager implements  TaskManager{
    int idCounter = 0;

    HashMap<Integer, Task> commonTasks = new HashMap<>();
    HashMap<Integer, EpicTask> epicTasks = new HashMap<>();
    HashMap<Integer, Subtask> subtasks = new HashMap<>();
    private List<Task> historyList = new ArrayList<>();

    @Override
    public List<Task> getHistory() {
        return historyList;
    }

    @Override
    public List<Task> returnCommonTaskList() {
        return new ArrayList<>(commonTasks.values());
    }

    @Override
    public List<Task> returnEpicTaskList() {
        List<Task> allEpicTask = new ArrayList<>(epicTasks.values());
        for (EpicTask epicTask : epicTasks.values()) {
            statusManager(epicTask);
        }
        return allEpicTask;
    }

    @Override
    public List<Task> returnSubtaskList() {
        return new ArrayList<>(subtasks.values());
    }

    @Override
    public void removeCommonTaskList() {
        commonTasks.clear();
    }

    @Override
    public void removeEpicTaskList() {
        epicTasks.clear();
        subtasks.clear();
    }

    @Override
    public void removeSubtaskList() {
        for (Subtask s : subtasks.values()) {
            s.getEpicTask().getSubtasks().clear();
        }
        subtasks.clear();
    }


    @Override
    public Task findEpic(int id) {
        if (epicTasks.containsKey(id)) {
            if(historyList.size() > 9){
                historyList.remove(0);
                historyList.add(epicTasks.get(id));
                return epicTasks.get(id);
            }
            historyList.add(epicTasks.get(id));
            return epicTasks.get(id);
        }
        return null;
    }

    @Override
    public Task findCommonTask(int id) {
        if (commonTasks.containsKey(id)) {
            if(historyList.size() > 9){
                historyList.remove(0);
                historyList.add(commonTasks.get(id));
                return commonTasks.get(id);
            }
            historyList.add(commonTasks.get(id));
            return commonTasks.get(id);
        }
        return null;
    }

    @Override
    public Task findSubtask(int id) {
        if (subtasks.containsKey(id)) {
            if(historyList.size() > 9){
                historyList.remove(0);
                historyList.add(subtasks.get(id));
                return subtasks.get(id);
            }
            historyList.add(subtasks.get(id));
            return subtasks.get(id);
        }
        return null;
    }


    @Override
    public void createSubtask(Subtask task) {
        task.setId(idCounter);
        subtasks.put(idCounter, (Subtask) task);
        idCounter++;

    }

    @Override
    public void createEpicTask(EpicTask task) {
        task.setId(idCounter);
        epicTasks.put(idCounter, task);
        idCounter++;
    }

    @Override
    public void createCommonTask(CommonTask task) {
        task.setId(idCounter);
        commonTasks.put(idCounter, task);
        idCounter++;
    }

    @Override
    public void updateEpicTask(EpicTask epicTask, int id) {
        if (epicTasks.containsKey(id)) {
            for (Subtask s : epicTasks.get(id).getSubtasks()) {
                epicTask.setSubtasks(s);
                s.setEpicTask(epicTask);
            }
            epicTask.setId(id);
            epicTasks.put(id, epicTask);
            statusManager(epicTask);
        }
    }

    @Override
    public void updateSubtask(Subtask subtask, int id) {
        if (subtasks.containsKey(id)) {
            subtask.setEpicTask(subtasks.get(id).getEpicTask());
            subtask.setId(id);
            subtasks.put(id, subtask);
            statusManager(subtask.getEpicTask());
        }
    }

    @Override
    public void updateCommonTask(CommonTask task, int id) {
        if (commonTasks.containsKey(id)) {
            task.setId(id);
            commonTasks.put(id, task);
        }
    }

    @Override
    public void deleteTaskForId(int id) {
        if (epicTasks.containsKey(id)) epicTasks.remove(id);
        if (subtasks.containsKey(id)) {
            subtasks.remove(id);
            statusManager(((Subtask) findSubtask(id)).getEpicTask());
        }
        if (commonTasks.containsKey(id)) commonTasks.remove(id);
    }

    public ArrayList<Subtask> subtasksList(EpicTask epicTask) {
        return epicTask.getSubtasks();

    }

    public void statusManager(EpicTask epicTask) {
        int doneStatusCounter = 0;
        int newStatusCounter = 0;

        for (int i = 0; i < epicTask.getSubtasks().size(); i++) {
            if (epicTask.getSubtasks().get(i).getStatus().equals(Status.NEW)) {
                newStatusCounter++;
            }
            if (epicTask.getSubtasks().get(i).getStatus().equals(Status.DONE)) {
                doneStatusCounter++;
            }
        }
        if (newStatusCounter == epicTask.getSubtasks().size() || epicTask.getSubtasks().size() == 0) {
            epicTask.setStatus(Status.NEW);
        } else if (doneStatusCounter != 0 && doneStatusCounter == epicTask.getSubtasks().size()) {
            epicTask.setStatus(Status.DONE);
        } else {
            epicTask.setStatus(Status.IN_PROGRESS);
        }
    }
}
