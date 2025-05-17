package com.example.todoapp;

public class TaskUpdateHandler {

    private TaskManager taskManager;

    public TaskUpdateHandler(TaskManager manager) {
        this.taskManager = manager;
    }

    public void updateTask(int index, Task newTask) {
        taskManager.updateTask(index, newTask);
    }
}
