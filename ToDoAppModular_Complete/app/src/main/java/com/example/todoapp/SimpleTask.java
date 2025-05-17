package com.example.todoapp;

public class SimpleTask extends Task {

    public SimpleTask(String title, String description) {
        super(title, description);
    }

    @Override
    public String getTaskType() {
        return "Simple";
    }
}
