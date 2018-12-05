package com.example.cybrus.mytodo;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton btnAddtask;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.tasks_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnAddtask=findViewById(R.id.float_btn_add);
        btnAddtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, AddTaskActivity.class);
                startActivity(intent);

            }
        });
                getTasks();
    }

    private void getTasks() {

        //this is database operation in background thread//
        class GetTasks extends AsyncTask<Void,Void,List<Task>>{

            @Override
            protected List<Task> doInBackground(Void... voids) {
                List<Task> tasklist= DatabaseClient.getInstance(getApplicationContext())
                                    .getAppDatabase()
                                    .taskDao().getAll();
                return tasklist;
            }

            @Override
            protected void onPostExecute(List<Task> tasks) {
                super.onPostExecute(tasks);
                TasksAdapter adapter= new TasksAdapter(MainActivity.this,tasks);
                recyclerView.setAdapter(adapter);

            }
        }

        GetTasks gt=new GetTasks();
        gt.execute();

    }


}
