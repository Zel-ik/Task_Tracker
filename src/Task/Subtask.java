package Task;

public class Subtask extends Task {


    public Subtask(String name, String describe, EpicTask epicTask) {
        super(name, describe);
        epicTask.setSubtasks(this);
        setStatus(Status.NEW);
    }

}
