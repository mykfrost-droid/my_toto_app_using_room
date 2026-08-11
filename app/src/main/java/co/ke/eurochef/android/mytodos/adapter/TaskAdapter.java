package co.ke.eurochef.android.mytodos.adapter;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import co.ke.eurochef.android.mytodos.R;
import co.ke.eurochef.android.mytodos.data.Task;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private List<Task> taskList = new ArrayList<>();

    private OnTaskActionListener listener;

    public interface OnTaskActionListener {
        void onTaskChecked(Task task);
        void onTaskDeleted(Task task);
    }

    public TaskAdapter(OnTaskActionListener listener) {
        this.listener = listener;
    }

    public void setTasks(List<Task> tasks) {
        this.taskList = tasks;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_task, parent, false);

        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull TaskViewHolder holder,
            int position) {

        Task task = taskList.get(position);

        holder.checkBoxTask.setText(task.getTitle());

        holder.checkBoxTask.setChecked(task.isCompleted());

        if (task.isCompleted()) {
            holder.checkBoxTask.setPaintFlags(
                    holder.checkBoxTask.getPaintFlags()
                            | Paint.STRIKE_THRU_TEXT_FLAG
            );
        } else {
            holder.checkBoxTask.setPaintFlags(
                    holder.checkBoxTask.getPaintFlags()
                            & (~Paint.STRIKE_THRU_TEXT_FLAG)
            );
        }

        holder.checkBoxTask.setOnClickListener(v ->
                listener.onTaskChecked(task)
        );
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    static class TaskViewHolder extends RecyclerView.ViewHolder {

        CheckBox checkBoxTask;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);

            checkBoxTask = itemView.findViewById(
                    R.id.checkBoxTask
            );
        }
    }
}