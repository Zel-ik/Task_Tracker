package Manager;

import Task.*;

import java.io.*;
import java.util.*;

import static Manager.TaskType.*;


public class FileBackedTasksManager extends InMemoryTaskManager implements TaskManager {
    File file;

    public FileBackedTasksManager(String URL) {
        this.file = new File(URL);

    }


    public void save() {

        try (FileWriter writer = new FileWriter(file)) {
            final String title = "id,type,name,status,description,epic" + System.lineSeparator();
            writer.write(title);

            // добавляем в CSV файл таск из каждой мапы
            for (Task t : commonTasks.values()) writer.write(t.toString() + System.lineSeparator());
            for (Task t : epicTasks.values()) writer.write(t.toString() + System.lineSeparator());
            for (Task t : subtasks.values()) writer.write(t.toString() + System.lineSeparator());

            writer.write("\n");

            writer.write(historyToString(historyManager));

            writer.flush();
        } catch (IOException e) {
            throw new ManagerSaveException("Ошибочка");
        }

    }

    static class ManagerSaveException extends RuntimeException {
        public ManagerSaveException() {
        }

        public ManagerSaveException(String message) {
            super(message);
        }
    }


     public Task fromString(String value) {
        String[] sline = value.split(", ");

        if (sline[1].equals(EPIC_TASK.name())) {
            return new EpicTask(Integer.parseInt(sline[0]), Status.valueOf(sline[2]), sline[3], sline[4]);
        } else if (sline[1].equals(SUBTASK.name())) {
            Subtask x = new Subtask(sline[3], sline[4], (EpicTask) findEpic(Integer.parseInt(sline[5])));
            x.setId(Integer.parseInt(sline[1]));
            x.setStatus(Status.valueOf(sline[2]));
            return x;
        } else {
            CommonTask x = new CommonTask(sline[3], sline[4]);
            x.setId(Integer.parseInt(sline[1]));
            x.setStatus(Status.valueOf(sline[2]));
            return x;
        }
    }

    static public String historyToString(HistoryManager historyManager) {
        StringBuilder x = new StringBuilder();
        for (Task t : historyManager.getHistory()) {
            x.append(t.getId()).append(",");
        }
        return x.toString();
    }

    static List<Integer> historyFromString(String value) {
        List<Integer> id = new ArrayList<>();
        String[] sline = value.split(",");
        for (int i = 0; i < sline.length; i++) {
            id.add(Integer.parseInt(sline[i]));
        }
        return id;
    }


    @Override
    public List getHistory() {
        return super.getHistory();
    }

    @Override
    public List<Task> returnCommonTaskList() {
        return super.returnCommonTaskList();
    }

    @Override
    public List<Task> returnEpicTaskList() {
        return super.returnEpicTaskList();
    }

    @Override
    public List<Task> returnSubtaskList() {
        return super.returnSubtaskList();
    }

    @Override
    public void removeCommonTaskList() {
        super.removeCommonTaskList();
        save();
    }

    @Override
    public void removeEpicTaskList() {
        super.removeEpicTaskList();
        save();
    }

    @Override
    public void removeSubtaskList() {
        super.removeSubtaskList();
        save();
    }

    @Override
    public Task findEpic(int id) {
        return super.findEpic(id);
    }

    @Override
    public Task findCommonTask(int id) {
        return super.findCommonTask(id);
    }

    @Override
    public Task findSubtask(int id) {
        return super.findSubtask(id);
    }

    @Override
    public void deleteTaskForId(int id) {
        super.deleteTaskForId(id);
        save();
    }

    @Override
    public void updateCommonTask(CommonTask task, int id) {
        super.updateCommonTask(task, id);
        save();
    }

    @Override
    public void updateSubtask(Subtask subtask, int id) {
        super.updateSubtask(subtask, id);
        save();
    }

    @Override
    public void updateEpicTask(EpicTask epicTask, int id) {
        super.updateEpicTask(epicTask, id);
        save();
    }

    @Override
    public void createCommonTask(CommonTask task) {
        super.createCommonTask(task);
        save();
    }

    @Override
    public void createEpicTask(EpicTask task) {
        super.createEpicTask(task);
        save();
    }

    @Override
    public void createSubtask(Subtask task) {
        super.createSubtask(task);
        save();
    }

    public static void main(String[] args) {
        //* Я не понимаю что мне дальше делать, задайте направления пожалуйста!!!!!!!!!


        FileBackedTasksManager fileBackedTaskManager = new FileBackedTasksManager(".idea/Saver.csv");
        EpicTask move = new EpicTask("Переезд", "найтии квартиру, собрать вещи и переехать");
        EpicTask problems = new EpicTask("Решаем проблемы", "Найти тупые ошибки в моем тупом коде");

        Subtask subtask1 = new Subtask("Сбор", "Собираем вещи", move);
        Subtask subtask2 = new Subtask("отвезти вещи", "Везем вещи в новую квартиру", move);


        fileBackedTaskManager.createEpicTask(move);
        fileBackedTaskManager.createEpicTask(problems);
        fileBackedTaskManager.createSubtask(subtask1);
        fileBackedTaskManager.createSubtask(subtask2);

        fileBackedTaskManager.save();

    }
}


