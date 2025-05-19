package com.example.todoapp;

public abstract class Task {

        protected String title;
        protected String description;
        private boolean completed;  // NEW

        public Task(String title, String description) {
            this.title = title;
            this.description = description;
            this.completed = false; // default false
        }

        public boolean isCompleted() {
            return completed;
        }

        public void setCompleted(boolean completed) {
            this.completed = completed;
        }

        // ... existing getters/setters ...

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
