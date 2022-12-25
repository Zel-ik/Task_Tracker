package net;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import manager.TaskManager;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class HistoryHandler implements HttpHandler {
    private TaskManager<?> manager;

    public HistoryHandler(TaskManager<?> manager) {
        this.manager = manager;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        List<?> history = manager.getHistory();
        byte[] json = new Gson().toJson(history, List.class).getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(200, json.length);
        try (OutputStream is = exchange.getResponseBody()) {
            is.write(json);
            is.flush();
        }
    }
}
