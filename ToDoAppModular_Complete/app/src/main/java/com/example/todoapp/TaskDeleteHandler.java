package com.example.todoapp;

public class TaskDeleteHandler {

    public boolean deleteTask(Task task) {
        if (task == null) return false;

        TaskManager.getInstance().deleteTask(task);
        return true;
    }
}