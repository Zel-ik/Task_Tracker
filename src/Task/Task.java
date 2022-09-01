package Task;

import java.util.Objects;

public abstract class Task {
    private String name;
    private String describe;
    private int id;
    private Status status;

    public Task(String name, String describe) {
        this.name = name;
        this.describe = describe;

    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id
                && Objects.equals(name, task.name)
                && Objects.equals(describe, task.describe)
                && status == task.status;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, describe, id, status);
        return 31 * result;
    }

    @Override
    public String toString() {
        return "name: " + name
                + " describe: " + describe
                + " id: " + id
                + " status: " + status;
    }
}

