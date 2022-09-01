package Task;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    int idCounter = 0;

    HashMap<Integer, Task> commonTasks = new HashMap<>();
    HashMap<Integer, Task> epicTasks = new HashMap<>();
    HashMap<Integer, Task> subtasks = new HashMap<>();

    public HashMap<Integer, Task> returnCommonTaskList() { // метод возвращает список задач
        return commonTasks;
    }

    public HashMap<Integer, Task> returnEpicTaskList() {// метод возвращает список Эпиков
        return epicTasks;
    }

    public HashMap<Integer, Task> returnSubtaskList() {// метод возвращает список ПодЗадач
        return subtasks;
    }

    public void removeCommonTaskList(int taskTypeNum) { // метод который очищет список Задач
        commonTasks.clear();
    }

    public void removeEpicTaskList(int taskTypeNum) {// метод который очищет список Эпиков
        epicTasks.clear();
    }

    public void removeSubtaskList(int taskTypeNum) {// метод который очищет список Подзадач
        subtasks.clear();
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


    public void createSubtask(Task task) { //Метод который добавляте только созданную Подзадачу во множество
        subtasks.put(idCounter, task);
        idCounter++;

    }

    public void createEpicTask(Task task) {//Метод который добавляте только созданный Эпик во множество
        epicTasks.put(idCounter, task);
        idCounter++;
    }

    public void createCommonTask(Task task) {//Метод который добавляте только созданную задачу во множество
        commonTasks.put(idCounter, task);
        idCounter++;
    }

    public void updateTask(Task task, int id) {
        if (epicTasks.containsKey(id)) epicTasks.put(id, task);
        if (subtasks.containsKey(id)) subtasks.put(id, task);
        if (commonTasks.containsKey(id)) commonTasks.put(id, task);
    }

    public void deleteTaskForId(int id) {
        if (epicTasks.containsKey(id)) epicTasks.remove(id);
        if (subtasks.containsKey(id)) subtasks.remove(id);
        if (commonTasks.containsKey(id)) commonTasks.remove(id);
    }

    public ArrayList<Subtask> subtasksList(EpicTask epicTask) {
        return epicTask.getSubtasks();

    }

    public void statusManager(EpicTask epicTask) {
        if (epicTask.getStatus().equals(Status.NEW) || epicTask.getStatus().equals(Status.IN_PROGRESS)) {
            int statusCounter = 0;

            for (int i = 0; i < epicTask.getSubtasks().size(); i++) {
                if (epicTask.getSubtasks().get(i).getStatus().equals(Status.IN_PROGRESS)) {
                    statusCounter++;
                }

                if (epicTask.getSubtasks().get(i).getStatus().equals(Status.IN_PROGRESS)) {
                    epicTask.setStatus(Status.DONE);
                    break;
                }
            }
            if (statusCounter == epicTask.getSubtasks().size()) epicTask.setStatus(Status.IN_PROGRESS);
        }
    }

}
