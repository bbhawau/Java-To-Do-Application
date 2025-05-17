package com.example.todoapp;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TaskManager taskManager;
    private TaskAdapter taskAdapter;
    private ListView listView;
    private EditText inputTitle, inputDescription, inputPriority;
    private CheckBox priorityCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        taskManager = new TaskManager();

        listView = findViewById(R.id.taskListView);
        inputTitle = findViewById(R.id.editTextTitle);
        inputDescription = findViewById(R.id.editTextDescription);
        inputPriority = findViewById(R.id.editTextPriority);
        priorityCheck = findViewById(R.id.checkBoxPriority);

        taskAdapter = new TaskAdapter(this, taskManager.getAllTasks());
        listView.setAdapter(taskAdapter);
    }

    public void onAddTask(View view) {
        String title = inputTitle.getText().toString();
        String desc = inputDescription.getText().toString();

        if (priorityCheck.isChecked()) {
            int priority = Integer.parseInt(inputPriority.getText().toString());
            taskManager.addPriorityTask(title, desc, priority);
        } else {
            taskManager.addSimpleTask(title, desc);
        }

        taskAdapter.notifyDataSetChanged();
        inputTitle.setText("");
        inputDescription.setText("");
        inputPriority.setText("");
    }
}
