package com.example.todoapp;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private static TaskManager instance;
    private List<Task> taskList;

    // Private constructor to enforce Singleton pattern
    private TaskManager() {
        taskList = new ArrayList<>();
    }

    // Public method to access the Singleton instance
    public static synchronized TaskManager getInstance() {
        if (instance == null) {
            instance = new TaskManager();
        }
        return instance;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void addSimpleTask(String title, String description) {
        taskList.add(new SimpleTask(title, description));
    }

    public void addPriorityTask(String title, String description, int priority) {
        taskList.add(new PriorityTask(title, description, priority));
    }

    public List<Task> getAllTasks() {
        return taskList;
    }

    public void deleteTask(int index) {
        if (index >= 0 && index < taskList.size()) {
            taskList.remove(index);
        }
    }

    public void deleteTask(Task task) {
        taskList.remove(task);
    }

    public void updateTask(int index, Task updatedTask) {
        if (index >= 0 && index < taskList.size()) {
            taskList.set(index, updatedTask);
        }
    }
}