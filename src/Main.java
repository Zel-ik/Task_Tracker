import Task.*;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        EpicTask move = new EpicTask("Переезд", "найтии квартиру, собрать вещи и переехать");
        EpicTask moveInAnotherCountry = new EpicTask("Переезд за рубеж", "нужно купить билет, собрать вещи и улететь");
        Subtask moveInAnotherFlat = new Subtask("Отвезти вещи", "после перелета везем вещи в новую вартиру", move);
        Subtask subtask;

        taskManager.createEpicTask(move);
        taskManager.createSubtask(new Subtask("Сбор", "Собираем вещи", move));
        taskManager.createSubtask(new Subtask("отвезти вещи", "Везем вещи в новую квартиру", move));

        taskManager.updateTask(moveInAnotherCountry, move.getId());
        taskManager.updateTask(moveInAnotherFlat, 2);
        System.out.println(taskManager.returnEpicTaskList());
        System.out.println(taskManager.returnSubtaskList().toString());

    }

}
