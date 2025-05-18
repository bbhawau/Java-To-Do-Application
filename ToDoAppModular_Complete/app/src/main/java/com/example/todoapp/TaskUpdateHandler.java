package com.example.todoapp;

public class TaskUpdateHandler {

    private TaskManager taskManager;

    public TaskUpdateHandler(TaskManager manager) {
        this.taskManager = manager;
    }

    /**
     * Updates the task at the specified index with the new task.
     * @param index The index of the task to update.
     * @param newTask The new task to replace the old one.
     * @return true if the update was successful; false otherwise.
     */
    public boolean updateTask(int index, Task newTask) {
        if (newTask == null) return false;

        if (index >= 0 && index < taskManager.getAllTasks().size()) {
            taskManager.updateTask(index, newTask);
            return true;
        }
        return false;
    }
}