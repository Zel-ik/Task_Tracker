package Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Objects;

import static Manager.TaskType.EPIC_TASK;

public class EpicTask extends Task {

    private int id;
    private ArrayList<Subtask> subtasks = new ArrayList<>();

    private final DateTimeFormatter formatterForStart = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int duration;



    public EpicTask(String name, String describe) {
        super(name, describe);
        setStatus(Status.NEW);
        if(!subtasks.isEmpty()){
            this.startTime = subtasks.get(0).getStartTime();
        }

    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setWholeDuration(int duration){
        this.duration += duration;

    }


    public void setDuration(LocalDateTime localDateTime1, LocalDateTime localDateTime2) {
        LocalDate date1 = LocalDate.from(localDateTime1);
        LocalDate date2 = LocalDate.from(localDateTime2);
        this.duration = Period.between(date1, date2).getDays()*24;
        duration += endTime.getHour()*60;
        duration += endTime.getMinute();
    }

    public int getDuration() {
        return duration;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime =  endTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }


    public void setSubtasks(Subtask subtasks) {
        this.getSubtasks().add(subtasks);
    }

    public ArrayList<Subtask> getSubtasks() {
        return subtasks;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = LocalDateTime.parse(startTime,formatterForStart);
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
    public String toString() {
        return getId() + ", " + EPIC_TASK.name() + ", " + getName() + ", " + getStatus() + ", "
                + getDescribe() + ", " + getStartTime().format(formatterForStart) + ", " + getDuration();
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EpicTask epicTask = (EpicTask) o;
        return Objects.equals(subtasks, epicTask.subtasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), subtasks) * 31;
    }
}
