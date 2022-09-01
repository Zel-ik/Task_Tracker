package Task;

import java.util.ArrayList;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EpicTask epicTask = (EpicTask) o;
        return Objects.equals(subtasks, epicTask.subtasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), subtasks);
    }
}
