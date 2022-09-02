import Manager.TaskManager;
import Task.*;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

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

        System.out.println(taskManager.returnEpicTaskList());
        System.out.println(taskManager.returnSubtaskList());

    }

}
