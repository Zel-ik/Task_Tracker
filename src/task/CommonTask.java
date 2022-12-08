package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static manager.TaskType.COMMON_TASK;

public class CommonTask extends Task {
    private int id;
    private Status status;

    private LocalDateTime startTime;
    private int duration;
    private LocalDateTime endTime;


    public CommonTask(String name, String describe, String startTime, int duration) {
        super(name, describe);
        setStatus(Status.NEW);

        DateTimeFormatter formatterForStart = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        this.startTime = LocalDateTime.parse(startTime, formatterForStart);
        this.duration = duration;
    }

    public CommonTask(String name, String describe) {
        super(name, describe);
        setStatus(Status.NEW);
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public int getDuration() {
        return duration;
    }

    public LocalDateTime getEndTime() {
        return getStartTime().plusMinutes(duration);
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
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return getId() + ", " + COMMON_TASK.name() + ", " + getName() + ", " + getStatus() + ", "
                + getDescribe();
    }
}
