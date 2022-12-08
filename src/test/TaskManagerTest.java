package test;

import manager.InMemoryTaskManager;
import manager.TaskManager;
import task.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public abstract class TaskManagerTest<T extends TaskManager> extends InMemoryTaskManager {
    protected T manager;

    @Test
    void statusEmptyTest() {
        EpicTask epicTask = new EpicTask("Тест", "Тестовый");
        Assertions.assertFalse(epicTask.getSubtasks().size() > 0);
    }

    @Test
    void allOfSubtasksHaveNewStatusTest() {
        EpicTask epicTaskTest = new EpicTask("Тест", "Тестовый");
        Subtask subtaskTest = new Subtask("subTest", "SubTestDes", epicTaskTest);
        Subtask subtaskTest2 = new Subtask("subTest2", "SubTestDes2", epicTaskTest);
        subtaskTest2.setStatus(Status.DONE);
//        statusManager(epicTaskTest);
//        int correct = 0;
//        if (epicTaskTest.getStatus() == Status.NEW) {
//            correct = 1;
//        }
//        Assertions.assertEquals(1,correct);
//        Тут проверка на New epicTaskTest, если вдруг я неправильно понял задание, этот тест тоже работает

        int newStatusCounter = 0;
        for (Subtask s : epicTaskTest.getSubtasks()) {
            if (s.getStatus() == Status.NEW) {
                newStatusCounter += 1;
            }
        }
        Assertions.assertEquals(1, newStatusCounter);
    }

    @Test
    void allOfSubtasksHaveDoneStatusTest() {
        EpicTask epicTaskTest = new EpicTask("Тест", "Тестовый");
        Subtask subtaskTest = new Subtask("subTest", "SubTestDes", epicTaskTest, "12-04-2020 15:55", 15);
        Subtask subtaskTest2 = new Subtask("subTest2", "SubTestDes2", epicTaskTest, "12-04-2020 15:55", 15);
        subtaskTest2.setStatus(Status.DONE);
        subtaskTest.setStatus(Status.DONE);
        statusManager(epicTaskTest);

        int correct = 0;
        if (epicTaskTest.getStatus() == Status.DONE) {
            correct = 1;
        }
        Assertions.assertEquals(1, correct);

//        int newStatusCounter = 0;
//        for(Subtask s : epicTaskTest.getSubtasks()){
//            if(s.getStatus() != Status.DONE){
//                newStatusCounter +=1;
//            }
//        }
//
//        Assertions.assertEquals(0,newStatusCounter);
    }

    @Test
    void statusNewAndDoneTogetherTest() {
        EpicTask epicTaskTest = new EpicTask("Тест", "Тестовый");
        Subtask subtaskTest = new Subtask("subTest", "SubTestDes", epicTaskTest);
        Subtask subtaskTest2 = new Subtask("subTest2", "SubTestDes2", epicTaskTest);
        Subtask subtaskTest3 = new Subtask("subTest2", "SubTestDes2", epicTaskTest);
        subtaskTest2.setStatus(Status.DONE);
        subtaskTest2.setStatus(Status.IN_PROGRESS);
        statusManager(epicTaskTest);

        int StatusCounter = 0;
        for (Subtask s : epicTaskTest.getSubtasks()) {
            if (s.getStatus() == Status.NEW) {
                StatusCounter += 1;
            } else if (s.getStatus() == Status.DONE) {
                StatusCounter += 1;
            }
        }
        Assertions.assertEquals(2, StatusCounter);
    }

    @Test
    void statusInProgressTest() {
        EpicTask epicTaskTest = new EpicTask("Тест", "Тестовый");
        Subtask subtaskTest = new Subtask("subTest", "SubTestDes", epicTaskTest);
        Subtask subtaskTest2 = new Subtask("subTest2", "SubTestDes2", epicTaskTest);
        Subtask subtaskTest3 = new Subtask("subTest2", "SubTestDes2", epicTaskTest);
        subtaskTest.setStatus(Status.IN_PROGRESS);
        subtaskTest2.setStatus(Status.IN_PROGRESS);
        subtaskTest3.setStatus(Status.IN_PROGRESS);
        statusManager(epicTaskTest);

        int StatusCounter = 0;
        for (Subtask s : epicTaskTest.getSubtasks()) {
            if (s.getStatus() == Status.IN_PROGRESS) {
                StatusCounter += 1;
            }
        }
        Assertions.assertEquals(3, StatusCounter);
    }

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
        CommonTask task = new CommonTask("task", "Task disc");
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
        EpicTask epicTask = new EpicTask("epicT", "epic disc");
        Subtask subtask = new Subtask("subtask", "subtask disc",epicTask, "22-04-2022 15:41", 51);
        Subtask subtask2 = new Subtask("subtask2", "subtask disc2",epicTask, "22-04-2022 15:41", 51);

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
        EpicTask epicTask = new EpicTask("e", "e");
        Subtask subtask = new Subtask("sub", "sub man", epicTask, "21-04-2022 15:55", 14);
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
        EpicTask epicTask = new EpicTask("e", "e");
        Subtask subtask = new Subtask("s", "s", epicTask, "20-12-2022 15:55", 531);
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
        EpicTask epicTask = new EpicTask("t", "e");
        Subtask subtask = new Subtask("name", "des", epicTask, "22-04-2022 15:55", 16);
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
        EpicTask epicTask = new EpicTask("name", "des");
        Subtask subtask = new Subtask("name", "des", epicTask, "22-04-2022 15:16", 16);
        Subtask subtask2 = new Subtask("noName", "noDes",epicTask, "22-04-2022 15:32", 40);

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
