package Task;

public class Subtask extends Task {
    private EpicTask epicTask;


    public Subtask(String name, String describe, EpicTask epicTask) {
        super(name, describe);
        this.epicTask = epicTask;
        epicTask.setSubtasks(this);
        setStatus(Status.NEW);
    }

    public void setEpicTask(EpicTask epicTask) {
        this.epicTask = epicTask;
    }

    public EpicTask getEpicTask() {
        return epicTask;
    }
}
