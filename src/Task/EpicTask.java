package Task;

import java.util.ArrayList;
import java.util.Objects;

import static Manager.TaskType.EPIC_TASK;

public class EpicTask extends Task {

    private int id;

    private ArrayList<Subtask> subtasks = new ArrayList<>();

    public EpicTask(String name, String describe) {
        super(name, describe);
        setStatus(Status.NEW);
    }

    public void setSubtasks(Subtask subtasks) {
        this.getSubtasks().add(subtasks);
    }

    public ArrayList<Subtask> getSubtasks() {
        return subtasks;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return getId() + ", " + EPIC_TASK.name() + ", " + getName() + ", " + getStatus() + ", "
                + getDescribe();
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
