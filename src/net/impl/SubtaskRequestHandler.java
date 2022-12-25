package net.impl;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.sun.net.httpserver.HttpExchange;
import manager.TaskManager;
import net.RequestHandler;
import task.EpicTask;
import task.Subtask;
import task.Task;

import java.io.IOException;

public class SubtaskRequestHandler extends RequestHandler {

    public SubtaskRequestHandler(TaskManager<?> manager) {
        super(manager);
    }

    @Override
    protected void get(HttpExchange exchange) throws IOException {
        respondTaskList(exchange, manager.returnSubtaskList());
    }

    @Override
    protected void getById(HttpExchange exchange, int id) throws IOException {
        Task task = manager.findSubtask(id);
        if (task == null) {
            exchange.sendResponseHeaders(404, 0);
        } else {
            respondTask(exchange, task);
        }
    }

    @Override
    protected void post(HttpExchange exchange, String body) throws IOException {
        try {
            Gson gson = new Gson();
            Subtask task = gson.fromJson(body, Subtask.class);
            manager.createSubtask(task);
            exchange.sendResponseHeaders(200, 0);
        } catch (JsonParseException e) {
            respondString(exchange, 400, "Invalid json");
        }
    }

    @Override
    protected void delete(HttpExchange exchange) {
        manager.removeSubtaskList();
    }

    @Override
    protected void deleteById(HttpExchange exchange, int id) {
        manager.deleteTaskForId(id);
    }

    @Override
    protected boolean extraHandle(HttpExchange exchange) throws IOException {

        if (exchange.getRequestMethod().equals("GET")) {
            String[] path = exchange.getRequestURI().getPath().split("/");
            if (path.length == 5 && path[3].equals("epic")) {
                int id = extractId(path[4]);
                Subtask subtask = (Subtask) manager.findSubtask(id);
                if (subtask == null) {
                    respondString(exchange, 404, "Subtask " + id + " wasn't found");
                } else {
                    EpicTask itsEpic = subtask.getEpicTask();
                    respondTask(exchange, itsEpic);
                }
                return true;
            }
        }

        return false;
    }
}
