package Manager;

import Task.*;

import java.util.List;

public interface TaskManager<T extends Task> {

    List<T> getHistory();

    List<Task> returnCommonTaskList();

    List<Task> returnEpicTaskList();

    List<Task> returnSubtaskList();

    void removeCommonTaskList();

    void removeEpicTaskList();

    void removeSubtaskList();

    T findEpic(int id);

    T findCommonTask(int id);

    T findSubtask(int id);

    void createSubtask(Subtask task);

    void createEpicTask(EpicTask task);

    void createCommonTask(CommonTask task);

    void updateEpicTask(EpicTask epicTask, int id);

    void updateSubtask(Subtask subtask, int id);

    void updateCommonTask(CommonTask task, int id);

    void deleteTaskForId(int id);


}
