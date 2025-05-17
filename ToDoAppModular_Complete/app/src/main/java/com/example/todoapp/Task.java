package com.example.todoapp;

public abstract class Task {
    protected String title;
    protected String description;

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public abstract String getTaskType();

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
