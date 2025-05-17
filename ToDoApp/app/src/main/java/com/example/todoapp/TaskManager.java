package com.example.todoapp;

import java.util.ArrayList;
import java.util.List;
import com.example.todoapp.tasks.BaseTask;

public class TaskManager {
    private List<BaseTask> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public void addTask(BaseTask task) {
        tasks.add(task);
    }

    public void removeTask(BaseTask task) {
        tasks.remove(task);
    }

    public List<BaseTask> getTasks() {
        return tasks;
    }
}