package com.example.assessmentapphpl.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.assessmentapphpl.R;
import com.example.assessmentapphpl.model.TaskDataResponseModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TaskDataItemAdapter extends RecyclerView.Adapter<TaskDataItemAdapter.ViewHolder> {

    private final List<TaskDataResponseModel> taskDataList;

    public TaskDataItemAdapter(List<TaskDataResponseModel> dataList) {
        this.taskDataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_data_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TaskDataResponseModel taskDataItem = taskDataList.get(holder.getAdapterPosition());

        holder.title.setText(taskDataItem.getTaskTitle());
        holder.details.setText(taskDataItem.getTaskDetails());
        holder.date.setText(taskDataItem.getTaskEntryDate());
    }

    @Override
    public int getItemCount() {
        return taskDataList.size();
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(taskDataList.get(position).getTaskId());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, details, date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            details = itemView.findViewById(R.id.details);
            date = itemView.findViewById(R.id.date);
        }
    }
}
