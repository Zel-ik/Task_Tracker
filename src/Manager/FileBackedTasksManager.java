package Manager;

import Task.*;

import java.io.*;
import java.util.*;

import static Manager.TaskType.*;


public class FileBackedTasksManager extends InMemoryTaskManager {
    private final File file;

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
            throw new ManagerSaveException("Ошибочка при записи в файл" + e.getMessage());
        }

    }

    static class ManagerSaveException extends RuntimeException {
        public ManagerSaveException() {
        }

        public ManagerSaveException(String message) {
            super(message);
        }
    }

     FileBackedTasksManager loadFromFile(File file) {
        FileBackedTasksManager newFileBackedTasksManager = new FileBackedTasksManager(file.toString());
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.readLine();
            String buffer;
            boolean isEnd = false;
            while ((buffer = reader.readLine()) != null) {
                if(buffer.length()<1) {
                    isEnd = true;
                    continue;
                }
                if(isEnd) {
                    historyFromString(buffer);
                    continue;
                }
                Task task;
                task = fromString(buffer);
                if (task instanceof EpicTask) newFileBackedTasksManager.epicTasks.put(task.getId(), (EpicTask) task);
                if (task instanceof Subtask) newFileBackedTasksManager.subtasks.put(task.getId(), (Subtask) task);
                if (task instanceof CommonTask) commonTasks.put(task.getId(), (CommonTask) task);

            }


        } catch (IOException e) {
            throw new ManagerSaveException("Ошибка при чтении файла" + e.getMessage());
        }
        return newFileBackedTasksManager;
    }


    public Task fromString(String value) {
        String[] sline = value.split(", ");

        if (sline[1].equals(EPIC_TASK.name())) {
            EpicTask x = new EpicTask(sline[2], sline[4]);
            x.setId(Integer.parseInt(sline[0]));
            x.setStatus(Status.valueOf(sline[3]));
            return x;
        } else if (sline[1].equals(SUBTASK.name())) {
            Subtask x = new Subtask(sline[2], sline[4]);
            x.setId(Integer.parseInt(sline[0]));
            x.setStatus(Status.valueOf(sline[3]));
            x.setEpicTask(epicTasks.get(Integer.parseInt(sline[5])));
            return x;
        } else {
            CommonTask x = new CommonTask(sline[2], sline[4]);
            x.setId(Integer.parseInt(sline[0]));
            x.setStatus(Status.valueOf(sline[3]));


            return x;
        }
    }

    public String historyToString(HistoryManager historyManager) {
        StringBuilder x = new StringBuilder();
        for (Task t : historyManager.getHistory()) {
            x.append(t.getId()).append(",");
        }
        return x.toString();
    }

     List<Integer> historyFromString(String value) {
        List<Integer> id = new ArrayList<>();
        String[] sline = value.split(",");
        for (int i = 0; i < sline.length; i++) {
            if(sline[i] != null) {
                id.add(Integer.parseInt(sline[i]));
            }
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
        FileBackedTasksManager fileBackedTaskManager = new FileBackedTasksManager(".idea/Saver.csv");
        EpicTask move = new EpicTask("Переезд", "найтии квартиру, собрать вещи и переехать");
        EpicTask problems = new EpicTask("Решаем проблемы", "Найти тупые ошибки в моем тупом коде");

        Subtask subtask1 = new Subtask("Сбор", "Собираем вещи", move);
        Subtask subtask2 = new Subtask("отвезти вещи", "Везем вещи в новую квартиру", move);
        Subtask subtask3 = new Subtask("Разобрать вещи", "разбираем ящики и сумки",  move);

        fileBackedTaskManager.createEpicTask(move);
        fileBackedTaskManager.createEpicTask(problems);

        fileBackedTaskManager.findEpic(1);
        fileBackedTaskManager.findEpic(0);
        fileBackedTaskManager.findSubtask(3);

        fileBackedTaskManager.createSubtask(subtask1);
        fileBackedTaskManager.createSubtask(subtask2);
        fileBackedTaskManager.createSubtask(subtask3);

        TaskManager taskManager = fileBackedTaskManager.loadFromFile(fileBackedTaskManager.file);
        System.out.println(taskManager.returnEpicTaskList());
        System.out.println(taskManager.returnSubtaskList());




    }

}


