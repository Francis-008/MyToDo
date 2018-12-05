package com.example.cybrus.mytodo;


import android.arch.persistence.room.Room;
import android.content.Context;

public class DatabaseClient {

    private Context ctx;
    private static DatabaseClient mInstance;

    //Our Database object//
    private AppDatabase appDatabase;





    private DatabaseClient(Context ctx){
        this.ctx=ctx;

        //creating the app database with Room database builder
        //MyToDos is the name of the database
        appDatabase=Room.databaseBuilder(ctx,AppDatabase.class,"MyToDos").build();
    }

    public static synchronized DatabaseClient getInstance(Context ctx){
        if (mInstance==null){
            mInstance=new DatabaseClient(ctx);
        }
        return mInstance;
    }

    public AppDatabase getAppDatabase(){
        return appDatabase;
    }

}
