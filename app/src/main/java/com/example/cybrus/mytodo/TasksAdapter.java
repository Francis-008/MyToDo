package com.example.cybrus.mytodo;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TasksViewHolder> {
    private Context mctx;
    private List<Task> tasksList;

    public TasksAdapter(Context mctx, List<Task> tasksList){
        this.mctx=mctx;
        this.tasksList=tasksList;
    }



    @NonNull
    @Override
    public TasksViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mctx).inflate(R.layout.recyclerview_tasks,viewGroup,false);

        return new TasksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TasksViewHolder tasksViewHolder, int i) {

        Task  t= tasksList.get(i);
        tasksViewHolder.textViewTask.setText(t.getTask());
        tasksViewHolder.textViewDesc.setText(t.getDesc());
        tasksViewHolder.textViewFinishBy.setText(t.getFinishBy());

        if (t.isFinished()){
            tasksViewHolder.textViewStatus.setText("Completed");
        }else{
            tasksViewHolder.textViewStatus.setText("Not Completed");
        }

    }

    @Override
    public int getItemCount() {
        return tasksList.size();
    }








    public class TasksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewStatus, textViewTask, textViewDesc, textViewFinishBy;

        public TasksViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewStatus=itemView.findViewById(R.id.textViewStatus);
            textViewTask=itemView.findViewById(R.id.textViewTask);
            textViewDesc=itemView.findViewById(R.id.textViewDesc);
            textViewFinishBy=itemView.findViewById(R.id.textViewFinishBy);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {

            Task task= tasksList.get(getAdapterPosition());
            Intent intent=new Intent(mctx, UpdateTaskActivity.class);
            intent.putExtra("task", task);
            mctx.startActivity(intent);
        }
    }






}
