package com.example.todoapp;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
        implements TaskAdapter.OnTaskActionListener {          // ← implements listener

    /* ------------------- FIELDS ------------------- */
    private TaskManager taskManager;
    private TaskAdapter taskAdapter;

    private ListView listView;
    private EditText inputTitle, inputDescription, inputPriority;
    private CheckBox priorityCheck;

    /* ------------------- LIFECYCLE ------------------- */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskManager = TaskManager.getInstance();

        listView         = findViewById(R.id.taskListView);
        inputTitle       = findViewById(R.id.editTextTitle);
        inputDescription = findViewById(R.id.editTextDescription);
        inputPriority    = findViewById(R.id.editTextPriority);
        priorityCheck    = findViewById(R.id.checkBoxPriority);

        /* 3-parameter constructor: context, data, listener */
        taskAdapter = new TaskAdapter(this, taskManager.getAllTasks(), this);
        listView.setAdapter(taskAdapter);
    }

    /* ------------------- ADD TASK ------------------- */
    public void onAddTask(View view) {
        String title = inputTitle.getText().toString().trim();
        String desc  = inputDescription.getText().toString().trim();

        if (title.isEmpty()) {
            Toast.makeText(this, "Please enter a title", Toast.LENGTH_SHORT).show();
            return;
        }

        if (priorityCheck.isChecked()) {
            int priority;
            try {
                priority = Integer.parseInt(inputPriority.getText().toString().trim());
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Priority must be a number", Toast.LENGTH_SHORT).show();
                return;
            }
            taskManager.addPriorityTask(title, desc, priority);
        } else {
            taskManager.addSimpleTask(title, desc);
        }

        taskAdapter.notifyDataSetChanged();
        clearInputs();
    }

    private void clearInputs() {
        inputTitle.setText("");
        inputDescription.setText("");
        inputPriority.setText("");
        priorityCheck.setChecked(false);
    }

    /* ------------------- LISTENER CALLBACKS ------------------- */
    @Override
    public void onDeleteTask(Task task) {
        taskManager.deleteTask(task);
        taskAdapter.notifyDataSetChanged();
        Toast.makeText(this, "Task deleted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpdateTask(int position, Task task) {
        showUpdateDialog(position, task);
    }

    /* ------------------- UPDATE DIALOG ------------------- */
    private void showUpdateDialog(int position, Task task) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Update Task");

        View dialogView = getLayoutInflater().inflate(R.layout.dialog_update_task, null);
        EditText editTitle       = dialogView.findViewById(R.id.editTextUpdateTitle);
        EditText editDescription = dialogView.findViewById(R.id.editTextUpdateDescription);
        EditText editPriority    = dialogView.findViewById(R.id.editTextUpdatePriority);
        CheckBox checkPriority   = dialogView.findViewById(R.id.checkBoxUpdatePriority);

        /* pre-fill */
        editTitle.setText(task.getTitle());
        editDescription.setText(task.getDescription());

        if (task instanceof PriorityTask) {
            checkPriority.setChecked(true);
            editPriority.setText(String.valueOf(((PriorityTask) task).getPriorityLevel()));
            editPriority.setEnabled(true);
        } else {
            checkPriority.setChecked(false);
            editPriority.setEnabled(false);
        }

        /* enable / disable priority field */
        checkPriority.setOnCheckedChangeListener((b, isChecked) ->
                editPriority.setEnabled(isChecked));

        builder.setView(dialogView);

        builder.setPositiveButton("Update", (d, which) -> {
            String newTitle = editTitle.getText().toString().trim();
            String newDesc  = editDescription.getText().toString().trim();

            if (newTitle.isEmpty() || newDesc.isEmpty()) {
                Toast.makeText(this, "Title and description cannot be empty", Toast.LENGTH_SHORT).show();
                return;
            }

            if (checkPriority.isChecked()) {
                int pr;
                try {
                    pr = Integer.parseInt(editPriority.getText().toString().trim());
                } catch (NumberFormatException e) {
                    Toast.makeText(this, "Priority must be a number", Toast.LENGTH_SHORT).show();
                    return;
                }
                taskManager.updateTask(position, new PriorityTask(newTitle, newDesc, pr));
            } else {
                taskManager.updateTask(position, new SimpleTask(newTitle, newDesc));
            }

            taskAdapter.notifyDataSetChanged();
            Toast.makeText(this, "Task updated", Toast.LENGTH_SHORT).show();
        });

        builder.setNegativeButton("Cancel", (d, which) -> d.dismiss());
        builder.show();
    }
    public void onToggleComplete(Task task) {
        task.setCompleted(!task.isCompleted());         // flip state
        taskAdapter.notifyDataSetChanged();
        Toast.makeText(
                this,
                task.isCompleted() ? "Marked complete" : "Marked incomplete",
                Toast.LENGTH_SHORT
        ).show();
    }
}