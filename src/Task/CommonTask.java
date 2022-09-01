package Task;

public class CommonTask extends Task {

    public CommonTask(String name, String describe) {
        super(name, describe);
        setStatus(Status.NEW);
    }

}
