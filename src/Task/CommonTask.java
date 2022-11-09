package Task;

import static Manager.TaskType.COMMON_TASK;
import static Manager.TaskType.EPIC_TASK;

public class CommonTask extends Task {
    private int id;
    private Status status;

    public CommonTask(String name, String describe) {
        super(name, describe);
        setStatus(Status.NEW);
    }

    @Override
    public String toString() {
        return getId() + ", " + COMMON_TASK.name() + ", " + getName() + ", " + getStatus() + ", "
                + getDescribe();
    }
}
