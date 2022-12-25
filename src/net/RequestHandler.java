package net;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import manager.TaskManager;
import task.Task;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public abstract class RequestHandler implements HttpHandler {
    protected TaskManager<?> manager;

    public RequestHandler(TaskManager<?> manager) {
        this.manager = manager;
    }

    protected void respondString(HttpExchange exchange, int code, String msg) throws IOException {
        byte[] bytes = msg.getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(code, bytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
            os.flush();
        }

    }

    protected void respondJson(HttpExchange exchange, String json) throws IOException {
        respondString(exchange, 200, json);
    }

    protected void respondTaskList(HttpExchange exchange, List<Task> tasks) throws IOException {
        respondJson(exchange, new Gson().toJson(tasks, List.class));
    }

    protected void respondTask(HttpExchange exchange, Task task) throws IOException {
        respondJson(exchange, new Gson().toJson(task));
    }

    protected abstract void get(HttpExchange exchange) throws IOException;
    protected abstract void getById(HttpExchange exchange, int id) throws IOException;
    protected abstract void post(HttpExchange exchange, String body) throws IOException;
    protected abstract void delete(HttpExchange exchange) throws IOException;
    protected abstract void deleteById(HttpExchange exchange, int id) throws IOException;

    protected boolean extraHandle(HttpExchange exchange) throws IOException {
        return false;
    }

    protected Integer extractId(String id) {
        return Integer.valueOf(id.substring(4));
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {

            final String method = exchange.getRequestMethod();
            final String[] path = exchange.getRequestURI().getPath().split("/");
            final String last = path.length > 3 && !path[3].isEmpty() ? path[3] : null;
            if (method.equals("GET")) {
                if (last != null) {
                    getById(exchange, extractId(last));
                } else {
                    get(exchange);
                }
            } else if (method.equals("POST")) {
                StringBuilder body = new StringBuilder();
                try (InputStream is = exchange.getRequestBody()) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                    String buf;
                    while ((buf = reader.readLine()) != null) {
                        body.append(buf).append("\n");
                    }
                }
                post(exchange, body.toString());
            } else if (method.equals("DELETE")) {
                if (last != null) {
                    deleteById(exchange, extractId(last));
                } else {
                    delete(exchange);
                }
            } else if (!extraHandle(exchange)) {
                throw new IOException("Can't find an endpoint for " + method + " " + exchange.getRequestURI().getPath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
