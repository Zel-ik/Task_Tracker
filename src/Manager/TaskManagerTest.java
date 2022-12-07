package Manager;

import Task.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public abstract class TaskManagerTest<T extends TaskManager>{
    protected T manager;

    @Test
     void subtaskHasEpicTest() {
        EpicTask epicTask = new EpicTask("epic", "epic disc");
        Subtask subtask = new Subtask("subtask", "subtask disc", epicTask);

        Assertions.assertEquals(epicTask, subtask.getEpicTask());
    }

    @Test
    void epicTaskHasSubTasksTest() {
        EpicTask epicTask = new EpicTask("epic", "epic disc");
        Subtask subtask = new Subtask("subtask", "subtask disc", epicTask);
        Subtask subtask2 = new Subtask("subtask2", "subtask disc2", epicTask);


        Assertions.assertEquals(2, epicTask.getSubtasks().size());
    }

    @Test
    void returnCommonTaskListTest(){
        CommonTask task = new CommonTask("Task", "Task disc");
        CommonTask task2 = new CommonTask("Task2", "Task disc2");
        Assertions.assertEquals(0, manager.returnCommonTaskList().size());

        manager.createCommonTask(task);
        manager.createCommonTask(task2);

        Assertions.assertNotNull(manager.returnCommonTaskList());
    }

    @Test
    void returnEpicTaskListTest(){
        EpicTask epicTask = new EpicTask("epicT", "epic disc");
        EpicTask epicTask2 = new EpicTask("epicT", "epic disc");
        Assertions.assertEquals(0,manager.returnEpicTaskList().size());

        manager.createEpicTask(epicTask);
        manager.createEpicTask(epicTask2);
        Assertions.assertNotNull(manager.returnEpicTaskList());
    }

    @Test
    void returnSubtaskListTest(){
        Subtask subtask = new Subtask("subtask", "subtask disc");
        Subtask subtask2 = new Subtask("subtask2", "subtask disc2");

        Assertions.assertEquals(0,manager.returnSubtaskList().size());

        manager.createSubtask(subtask);
        manager.createSubtask(subtask2);

        Assertions.assertNotNull(manager.returnSubtaskList());
    }

    @Test
    void removeCommonTaskListTest(){
        CommonTask task = new CommonTask("commonTask", "task disc");
        manager.createCommonTask(task);
        manager.removeCommonTaskList();

        Assertions.assertEquals(0,manager.returnCommonTaskList().size());
    }

    @Test
    void removeEpicTaskListTest(){
        EpicTask task = new EpicTask("epic", "epic disc");
        manager.createEpicTask(task);
        manager.removeEpicTaskList();

        Assertions.assertEquals(0,manager.returnEpicTaskList().size());
    }

    @Test
    void removeSubtaskListTest(){
        Subtask subtask = new Subtask("sub", "sub man");
        manager.createSubtask(subtask);
        manager.removeSubtaskList();

        Assertions.assertEquals(0,manager.returnSubtaskList().size());
    }

    @Test
    void findEpicTaskIdTest(){
        EpicTask epicTask = new EpicTask("e", "e");
        manager.createEpicTask(epicTask);

        Assertions.assertNotNull(manager.findEpic(0));
        Assertions.assertNull(manager.findEpic(1));
    }

    @Test
    void findCommonTaskIdTest(){
        CommonTask task = new CommonTask("e", "e");
        manager.createCommonTask(task);

        Assertions.assertNotNull(manager.findCommonTask(0));
        Assertions.assertNull(manager.findCommonTask(1));
    }

    @Test
    void findSubtaskIdTest(){
        Subtask subtask = new Subtask("s", "s");
        manager.createSubtask(subtask);

        Assertions.assertNotNull(manager.findSubtask(0));
        Assertions.assertNull(manager.findSubtask(1));
    }

    @Test
    void createEpicTaskTest(){
        EpicTask epicTask = new EpicTask("name", "des");
        manager.createEpicTask(epicTask);

        Assertions.assertNotNull(manager.findEpic(0));
    }

    @Test
    void createSubtaskTest(){
        Subtask subtask = new Subtask("name", "des");
        manager.createSubtask(subtask);

        Assertions.assertNotNull(manager.findSubtask(0));
    }

    @Test
    void createCommonTaskTest(){
        CommonTask commonTask = new CommonTask("name", "des");
        manager.createCommonTask(commonTask);

        Assertions.assertNotNull(manager.findCommonTask(0));
    }

    @Test
    void updateEpicTaskTest(){
        EpicTask epicTask = new EpicTask("name", "des");
        EpicTask anotherEpic = new EpicTask("another name", "another dis");

        manager.createEpicTask(epicTask);
        manager.updateEpicTask(anotherEpic,0);

        Assertions.assertEquals(anotherEpic, manager.findEpic(0));
    }

    @Test
    void updateSubtaskTest(){
        Subtask subtask = new Subtask("name", "des");
        Subtask subtask2 = new Subtask("noName", "noDes");

        manager.createSubtask(subtask);
        manager.updateSubtask(subtask2,0);

        Assertions.assertEquals(subtask2, manager.findSubtask(0));
    }

    @Test
    void updateCommonTaskTest(){
        CommonTask commonTask = new CommonTask("hero", "helping someone");
        CommonTask commonTask1 = new CommonTask("villain", "tricking someone");

        manager.createCommonTask(commonTask);
        manager.updateCommonTask(commonTask1, 0);

        Assertions.assertEquals(commonTask1,manager.findCommonTask(0));

    }

    @Test
    void deleteTaskForIdTest(){
        EpicTask epicTask = new EpicTask("do you delete me?", "no, please, i have the wife and children");
        manager.createEpicTask(epicTask);
        manager.deleteTaskForId(0);

        Assertions.assertNull(manager.findEpic(0));
    }
}
