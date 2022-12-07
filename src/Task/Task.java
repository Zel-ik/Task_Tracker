package Task;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Task {
    /*
    Я просто решил что абстрактный класс подойдет лучше, так как у всех трех классов почти все сходится помимо, того что
    у Эпика есть список под задач. А если в будущем придется изменить Обычный Таск то это будет сделать проще.
    */



    private String name;
    private String describe;
    private int id;
    private Status status;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int duration;

    public Task(String name, String describe) {
        this.name = name;
        this.describe = describe;

    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public int getDuration() {
        return duration;
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

