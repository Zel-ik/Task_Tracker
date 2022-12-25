package net;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import manager.TaskManager;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class RootHandler implements HttpHandler {
    private TaskManager<?> manager;

    public RootHandler(TaskManager<?> manager) {
        this.manager = manager;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("tasks", gson.toJsonTree(manager.returnCommonTaskList(), List.class));
        jsonObject.add("subtasks", gson.toJsonTree(manager.returnSubtaskList(), List.class));
        jsonObject.add("epictasks", gson.toJsonTree(manager.returnEpicTaskList(), List.class));
        byte[] json = gson.toJson(jsonObject).getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(200, json.length);
        try (OutputStream is = exchange.getResponseBody()) {
            is.write(json);
            is.flush();
        }
    }
}
