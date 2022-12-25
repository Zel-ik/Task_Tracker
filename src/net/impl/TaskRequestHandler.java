package net.impl;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.sun.net.httpserver.HttpExchange;
import manager.TaskManager;
import net.RequestHandler;
import task.CommonTask;
import task.Task;

import java.io.IOException;

public class TaskRequestHandler extends RequestHandler {

    public TaskRequestHandler(TaskManager<?> manager) {
        super(manager);
    }

    @Override
    protected void get(HttpExchange exchange) throws IOException {
        respondTaskList(exchange, manager.returnCommonTaskList());
    }

    @Override
    protected void getById(HttpExchange exchange, int id) throws IOException {
        Task task = manager.findCommonTask(id);
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
            CommonTask task = gson.fromJson(body, CommonTask.class);
            manager.createCommonTask(task);
            exchange.sendResponseHeaders(200, 0);
        } catch (JsonParseException e) {
            respondString(exchange, 400, "Invalid json");
        }
    }

    @Override
    protected void delete(HttpExchange exchange) {
        manager.removeCommonTaskList();
    }

    @Override
    protected void deleteById(HttpExchange exchange, int id) {
        manager.deleteTaskForId(id);
    }
}
