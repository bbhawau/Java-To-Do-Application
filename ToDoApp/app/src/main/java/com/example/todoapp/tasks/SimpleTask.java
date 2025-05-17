package com.example.todoapp.tasks;

public class SimpleTask extends BaseTask {
    public SimpleTask(String title) {
        super(title);
    }

    @Override
    public String getTaskType() {
        return "Simple";
    }
}