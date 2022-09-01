package Task;

public class CommonTask extends Task {

    public CommonTask(String name, String describe) {
        super(name, describe);
        setStatus(Status.NEW);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
