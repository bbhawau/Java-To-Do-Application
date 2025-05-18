package com.example.todoapp;

public class PriorityTask extends Task {
    private int priorityLevel;

    public PriorityTask(String title, String description, int priorityLevel) {
        super(title, description);
        this.priorityLevel = priorityLevel;
    }

    @Override
    public String getTaskType() {
        return "Priority";
    }

    public int getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(int priorityLevel) {
        this.priorityLevel = priorityLevel;
    }
}