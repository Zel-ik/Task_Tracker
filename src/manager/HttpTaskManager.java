package manager;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import task.EpicTask;
import task.Subtask;
import task.Task;

import java.io.File;
import java.util.List;

public class HttpTaskManager extends FileBackedTasksManager {
    private KVClient kvClient;

    public HttpTaskManager(String URL) {
        super(URL);
        kvClient = new KVClient(URL);
        kvClient.register();
    }

    @Override
    public void save() {
        Gson gson = new Gson();
        JsonObject root = new JsonObject();
        root.add("history", new JsonPrimitive(historyToString(historyManager)));
        root.add("epics", gson.toJsonTree(returnEpicTaskList(), List.class));
        root.add("subs", gson.toJsonTree(returnSubtaskList(), List.class));
        root.add("tasks", gson.toJsonTree(returnCommonTaskList(), List.class));
        kvClient.put("data", gson.toJson(root));
    }

    @Override
    FileBackedTasksManager loadFromFile(File file) {
        String data = kvClient.load("data");
        Gson gson = new Gson();
        JsonObject obj = JsonParser.parseString(data).getAsJsonObject();
        for (Integer i : historyFromString(obj.get("history").getAsString())) {
//            historyManager.add(i);
        }
        List<EpicTask> epics = gson.fromJson(obj.get("epics").getAsString(), List.class);
        List<Subtask> subs = gson.fromJson(obj.get("subs").getAsString(), List.class);
        List<Task> tasks = gson.fromJson(obj.get("tasks").getAsString(), List.class);
        for (EpicTask task : epics) {
            epicTasks.put(task.getId(), task);
        }
        for (Subtask task : subs) {
            subtasks.put(task.getId(), task);
        }
        for (Task task : tasks) {
            commonTasks.put(task.getId(), task);
        }
        return this;
    }
}
