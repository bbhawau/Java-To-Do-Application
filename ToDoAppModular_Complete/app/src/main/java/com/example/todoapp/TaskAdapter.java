package com.example.todoapp;

import android.content.Context;
import android.view.*;
import android.widget.*;

import java.util.List;

public class TaskAdapter extends BaseAdapter {
    private Context context;
    private List<Task> taskList;

    public TaskAdapter(Context context, List<Task> tasks) {
        this.context = context;
        this.taskList = tasks;
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

    static class ViewHolder {
        TextView title;
        TextView description;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Task task = taskList.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.task_item, parent, false);
            holder = new ViewHolder();
            holder.title = convertView.findViewById(R.id.taskTitle);
            holder.description = convertView.findViewById(R.id.taskDescription);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.title.setText(task.getTitle());

        if (task instanceof PriorityTask) {
            PriorityTask pt = (PriorityTask) task;
            holder.description.setText(task.getDescription() + " (Priority: " + pt.getPriorityLevel() + ")");
        } else {
            holder.description.setText(task.getDescription());
        }

        return convertView;
    }
}
