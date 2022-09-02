package Manager;

import Task.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaskManager {
    int idCounter = 0;

    HashMap<Integer, Task> commonTasks = new HashMap<>();
    HashMap<Integer, EpicTask> epicTasks = new HashMap<>();
    HashMap<Integer, Subtask> subtasks = new HashMap<>();

    public List<Task> returnCommonTaskList() {
        List<Task> allCommonTask = new ArrayList<>(commonTasks.values());
        return allCommonTask;
    }

    public List<Task> returnEpicTaskList() {
        List<Task> allEpicTask = new ArrayList<>(epicTasks.values());
        return allEpicTask;
    }

    public List<Task> returnSubtaskList() {
        List<Task> allSubTask = new ArrayList<>(subtasks.values());
        return allSubTask;
    }

    public void removeCommonTaskList() {
        commonTasks.clear();
    }

    public void removeEpicTaskList() {
        epicTasks.clear();
        subtasks.clear();
    }

    public void removeSubtaskList() {
        subtasks.clear();
        for (int i = 0; i < subtasks.size(); i++) {
            subtasks.get(i).getEpicTask().getSubtasks().clear();
        }
    }


    public Task findEpic(int id) {
        if (epicTasks.containsKey(id)) return epicTasks.get(id);
        return null;
    }

    public Task findCommonTask(int id) {
        if (commonTasks.containsKey(id)) return commonTasks.get(id);
        return null;
    }

    public Task findSubtask(int id) {
        if (subtasks.containsKey(id)) return subtasks.get(id);
        return null;
    }


    public void createSubtask(Subtask task) {
        task.setId(idCounter);
        subtasks.put(idCounter, (Subtask) task);
        idCounter++;

    }

    public void createEpicTask(EpicTask task) {
        task.setId(idCounter);
        epicTasks.put(idCounter, task);
        idCounter++;
    }

    public void createCommonTask(CommonTask task) {
        task.setId(idCounter);
        commonTasks.put(idCounter, task);
        idCounter++;
    }

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

    public void updateSubtask(Subtask subtask, int id) {
        if (subtasks.containsKey(id)) {
            subtask.setEpicTask(subtasks.get(id).getEpicTask());
            subtask.setId(id);
            subtasks.put(id, subtask);
            statusManager(subtask.getEpicTask());
        }
    }

    public void updateCommonTask(CommonTask task, int id) {
        if (commonTasks.containsKey(id)) {
            task.setId(id);
            commonTasks.put(id, task);
        }
    }

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
        if (newStatusCounter == epicTask.getSubtasks().size()) {
            epicTask.setStatus(Status.NEW);
            System.out.println("Дошли сюда? 1");
        } else if (doneStatusCounter == epicTask.getSubtasks().size()) {
            epicTask.setStatus(Status.DONE);
            System.out.println("Дошли сюда? 2");
        } else {
            epicTask.setStatus(Status.IN_PROGRESS);
            System.out.println("Дошли сюда? 3");
        }
    }
}
