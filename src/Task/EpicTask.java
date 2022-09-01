package Task;

import java.util.ArrayList;

public class EpicTask extends Task {

    private ArrayList<Subtask> subtasks = new ArrayList<>();

    public ArrayList<Subtask> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(Subtask subtasks) {
        this.getSubtasks().add(subtasks);
    }

    public EpicTask(String name, String describe) {
        super(name, describe);
        setStatus(Status.NEW);
    }


}
