package com.example.todoapp;

import android.content.Context;
import android.view.*;
import android.widget.*;

import java.util.List;

public class TaskAdapter extends BaseAdapter {
    private Context context;
    private List<Task> taskList;
    private OnTaskActionListener listener;

    public interface OnTaskActionListener {
        void onToggleComplete(Task task);
        void onDeleteTask(Task task);
        void onUpdateTask(int position, Task task);
    }

    public TaskAdapter(Context context, List<Task> tasks, OnTaskActionListener listener) {
        this.context = context;
        this.taskList = tasks;
        this.listener = listener;
    }
    static class ViewHolder {
        TextView title, description;
        Button toggleCompleteButton;
        Button deleteButton;
        Button updateButton;
    }

    @Override
    public int getCount() {
        return taskList.size();
    }

    @Override
    public Object getItem(int i) {
        return taskList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        final Task task = taskList.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.task_item, parent, false);
            holder = new ViewHolder();
            holder.title = convertView.findViewById(R.id.taskTitle);
            holder.description = convertView.findViewById(R.id.taskDescription);
            holder.deleteButton = convertView.findViewById(R.id.buttonDelete);
            holder.updateButton = convertView.findViewById(R.id.buttonUpdate);
            holder.toggleCompleteButton = convertView.findViewById(R.id.buttonToggleComplete);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.title.setText(task.getTitle() != null ? task.getTitle() : "No Title");

        if (task instanceof PriorityTask) {
            PriorityTask pt = (PriorityTask) task;
            holder.description.setText(task.getDescription() + " (Priority: " + pt.getPriorityLevel() + ")");
        } else {
            holder.description.setText(task.getDescription());
        }

        // Delete button listener
        holder.deleteButton.setOnClickListener(v -> {
            if (listener != null) {
                listener.onDeleteTask(task);
            }
        });

        // Update button listener
        holder.updateButton.setOnClickListener(v -> {
            if (listener != null) {
                listener.onUpdateTask(position, task);
            }
        });
        holder.toggleCompleteButton = convertView.findViewById(R.id.buttonToggleComplete);
        // Optional: fade completed tasks
        convertView.setAlpha(task.isCompleted() ? 0.5f : 1f);

        holder.toggleCompleteButton.setOnClickListener(v -> {
            if (listener != null) {
                listener.onToggleComplete(task);
            }
        });

        return convertView;
    }
}