
package com.example.todoapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> tasks;
    private ArrayAdapter<String> adapter;
    private ListView listView;
    private EditText inputTask;
    private Button addTaskButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tasks = new ArrayList<>();
        listView = findViewById(R.id.listViewTasks);
        inputTask = findViewById(R.id.editTextTask);
        addTaskButton = findViewById(R.id.buttonAddTask);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tasks);
        listView.setAdapter(adapter);

        addTaskButton.setOnClickListener(v -> {
            String task = inputTask.getText().toString().trim();
            if (!task.isEmpty()) {
                tasks.add(task);
                adapter.notifyDataSetChanged();
                inputTask.setText("");
            }
        });

        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            tasks.remove(position);
            adapter.notifyDataSetChanged();
            return true;
        });
    }
}
