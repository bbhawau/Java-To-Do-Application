package com.example.todoapp;

public class TaskAddHandler {

    private TaskManager taskManager;

    public TaskAddHandler(TaskManager manager) {
        this.taskManager = manager;
    }

    public void addSimple(String title, String description) {
        taskManager.addSimpleTask(title, description);
    }

    public void addPriority(String title, String description, int priority) {
        taskManager.addPriorityTask(title, description, priority);
    }
}
