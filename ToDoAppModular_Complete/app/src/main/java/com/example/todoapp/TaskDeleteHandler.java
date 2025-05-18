package com.example.todoapp;

public class TaskDeleteHandler {

    public boolean deleteTask(Task task) {
        if (task == null) return false;

        TaskManager manager = TaskManager.getInstance();
        boolean existed = manager.getAllTasks().contains(task);

        if (existed) {
            manager.deleteTask(task);
            return true;
        }

        return false;
    }
}