package com.example.todoapp.tasks;

public class PriorityTask extends BaseTask {
    private int priority;

    public PriorityTask(String title, int priority) {
        super(title);
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String getTaskType() {
        return "Priority";
    }
}