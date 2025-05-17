package com.example.todoapp;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> taskList;

    public TaskManager() {
        taskList = new ArrayList<>();
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

    public void updateTask(int index, Task updatedTask) {
        if (index >= 0 && index < taskList.size()) {
            taskList.set(index, updatedTask);
        }
    }
}
