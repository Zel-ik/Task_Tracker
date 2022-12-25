package task;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static manager.TaskType.SUBTASK;

public class Subtask extends Task {
    private EpicTask epicTask;

    public transient LocalDateTime startTime;
    public transient LocalDateTime endTime;

    public DateTimeFormatter getFormatter() {
        return Formatter;
    }

    public Subtask(String name, String describe) {
        super(name, describe);
        setStatus(Status.NEW);
    }

    public Subtask(String name, String describe, EpicTask epicTask, String startTime, int duration) {
        super(name, describe);
        setStatus(Status.NEW);

        this.startTime = LocalDateTime.parse(startTime, getFormatter());// делаем LocalDateTime

        this.duration = duration;


        this.epicTask = epicTask;
        if (epicTask.getSubtasks().size() == 0) {
            epicTask.setStartTime(this.startTime);
        }

        epicTask.setSubtasks(this);
        if (epicTask.getSubtasks().get(epicTask.getSubtasks().size() - 1).id == this.id) {
            epicTask.setEndTime(this.endTime);
        }
        epicTask.setWholeDuration((int) getDuration());
    }


    public Subtask(String name, String describe, EpicTask epicTask) {
        super(name, describe);
        setStatus(Status.NEW);

        this.epicTask = epicTask;
        epicTask.setSubtasks(this);

    }


    private transient final DateTimeFormatter Formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"); // определил входной формат

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = LocalDateTime.parse(LocalDateTime.parse(endTime).format(getFormatter()));

    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setStartTime(String startTime) {
        this.startTime = LocalDateTime.parse(startTime, getFormatter());
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

    public void setEpicTask(EpicTask epicTask) {
        this.epicTask = epicTask;
    }

    public EpicTask getEpicTask() {
        return epicTask;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Subtask subtask = (Subtask) o;

        if (id != subtask.id) return false;
        return Objects.equals(epicTask, subtask.epicTask);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (epicTask != null ? epicTask.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }

    @Override
    public String toString() {
        return getId() + ", " + SUBTASK.name() + ", " + getName() + ", " + getStatus() + ", "
                + getDescribe() + ", " + getEpicTask().getId() + ", " + getStartTime().format(getFormatter())
                + ", " + getDuration();
    }
}
