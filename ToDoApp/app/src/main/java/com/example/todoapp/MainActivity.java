package com.example.todoapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.todoapp.tasks.SimpleTask;
import com.example.todoapp.tasks.PriorityTask;

public class MainActivity extends AppCompatActivity {
    private TaskManager taskManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskManager = new TaskManager();
        taskManager.addTask(new SimpleTask("Buy groceries"));
        taskManager.addTask(new PriorityTask("Finish project", 1));
    }
}