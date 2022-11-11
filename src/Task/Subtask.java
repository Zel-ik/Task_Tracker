package Task;

import static Manager.TaskType.SUBTASK;

public class Subtask extends Task {
    private EpicTask epicTask;
    private int id;


    public Subtask(String name, String describe) {
        super(name, describe);
        setStatus(Status.NEW);
    }


    public Subtask(String name, String describe, EpicTask epicTask) {
        super(name, describe);
        this.epicTask = epicTask;
        epicTask.setSubtasks(this);
        setStatus(Status.NEW);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public void setEpicTask(EpicTask epicTask) {
        this.epicTask = epicTask;
    }

    public EpicTask getEpicTask() {
        return epicTask;
    }

    @Override
    public String toString() {
        return getId() + ", " + SUBTASK.name() + ", " + getName() + ", " + getStatus() + ", "
                + getDescribe() + ", " + getEpicTask().getId();
    }
}
