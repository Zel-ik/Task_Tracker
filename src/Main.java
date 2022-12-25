import com.sun.net.httpserver.HttpServer;
import manager.Managers;
import manager.TaskManager;
import net.HistoryHandler;
import net.RootHandler;
import net.impl.EpicRequestHandler;
import net.impl.SubtaskRequestHandler;
import net.impl.TaskRequestHandler;
import task.*;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {
    private static TaskManager<?> getTaskManager() {
        TaskManager<?> taskManager = Managers.getDefault();

        EpicTask move = new EpicTask("Переезд", "найтии квартиру, собрать вещи и переехать");
        EpicTask problems = new EpicTask("Решаем проблемы", "Найти тупые ошибки в моем тупом коде");

        Subtask subtask1 = new Subtask("Сбор", "Собираем вещи", move);
        Subtask subtask2 = new Subtask("отвезти вещи", "Везем вещи в новую квартиру", move);
        Subtask subtask3 = new Subtask("Разобрать вещи", "разбираем ящики и сумки",  move);

        taskManager.createEpicTask(move);
        taskManager.createEpicTask(problems);

        taskManager.createSubtask(subtask1);
        taskManager.createSubtask(subtask2);
        taskManager.createSubtask(subtask3);

        taskManager.findEpic(1);
        taskManager.findEpic(0);
        taskManager.findSubtask(3);
        taskManager.deleteTaskForId(0);

        return taskManager;
    }

    public static void main(String[] args) throws IOException {
        TaskManager<?> taskManager = getTaskManager();

        HttpServer httpServer = HttpServer.create();

        httpServer.bind(new InetSocketAddress(8090), 0);
        httpServer.createContext("/tasks", new RootHandler(taskManager));
        httpServer.createContext("/tasks/history", new HistoryHandler(taskManager));
        httpServer.createContext("/tasks/task", new TaskRequestHandler(taskManager));
        httpServer.createContext("/tasks/subtask", new SubtaskRequestHandler(taskManager));
        httpServer.createContext("/tasks/epic", new EpicRequestHandler(taskManager));

        httpServer.start();
    }

}
