package com.example.cybrus.mytodo;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddTaskActivity extends AppCompatActivity {

    private EditText input_task, input_desc, input_finshBy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);


        input_task=findViewById(R.id.input_task);
        input_desc=findViewById(R.id.input_desc);
        input_finshBy=findViewById(R.id.input_finishBy);

        findViewById(R.id.btn_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTask();
            }
        });
    }




    private void saveTask() {

        final String sTask = input_task.getText().toString();
        final String sDesc = input_desc.getText().toString();
        final String sFinishBy= input_finshBy.getText().toString();

        if (sTask.isEmpty()){
            input_task.setError("Task Required");
            input_task.requestFocus();
            return;
        }


        if (sDesc.isEmpty()){
            input_desc.setError("Description Required");
            input_desc.requestFocus();
            return;
        }


        if (sFinishBy.isEmpty()){
            input_finshBy.setError("FinishBy Required");
            input_finshBy.requestFocus();
            return;
        }


        class SaveTask extends AsyncTask<Void,Void,Void>{

            @Override
            protected Void doInBackground(Void... voids) {


                //Creating a Task//
                Task task= new Task();
                task.setTask(sTask);
                task.setDesc(sDesc);
                task.setFinishBy(sFinishBy);
                task.setFinished(false);

                //adding to Database
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().taskDao()
                        .insert(task);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                finish();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                Toast.makeText(getApplicationContext(),"Saved", Toast.LENGTH_LONG).show();
            }
        }


        SaveTask st= new SaveTask();
        st.execute();

    }





}
