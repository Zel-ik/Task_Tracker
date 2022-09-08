import Manager.InMemoryTaskManager;
import Manager.Managers;
import Manager.TaskManager;
import Task.*;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = Managers.getDefault();

        EpicTask move = new EpicTask("Переезд", "найтии квартиру, собрать вещи и переехать");
        EpicTask moveInAnotherCountry = new EpicTask("Переезд за рубеж", "нужно купить билет, собрать вещи и улететь");
        EpicTask problems = new EpicTask("Решаем проблемы", "Найти тупые ошибки в моем тупом коде");

        Subtask subtask1 = new Subtask("Сбор", "Собираем вещи", move);
        Subtask subtask2 = new Subtask("отвезти вещи", "Везем вещи в новую квартиру", move);

        taskManager.createEpicTask(move);
        taskManager.createEpicTask(problems);
        taskManager.createSubtask(subtask1);
        taskManager.createSubtask(subtask2);

        subtask1.setStatus(Status.DONE);
        subtask2.setStatus(Status.DONE);

        taskManager.updateEpicTask(moveInAnotherCountry, move.getId());

        taskManager.findEpic(0);
        taskManager.findEpic(0);
        taskManager.findSubtask(2);
        taskManager.findEpic(0);
        taskManager.findEpic(0);
        taskManager.findEpic(0);
        taskManager.findEpic(0);
        taskManager.findEpic(0);
        taskManager.findEpic(0);
        taskManager.findEpic(0);
        taskManager.findSubtask(2);

        System.out.println(taskManager.returnEpicTaskList());
        System.out.println(taskManager.returnSubtaskList());
        System.out.println(taskManager.getHistory());

    }

}
