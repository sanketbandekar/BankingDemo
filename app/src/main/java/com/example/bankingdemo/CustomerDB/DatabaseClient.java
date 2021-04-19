package com.example.bankingdemo.CustomerDB;

import android.content.Context;

import androidx.room.Room;

public class DatabaseClient {

    private Context context;
    private static DatabaseClient mInstance;

    //our app database object
    private CustomerDatabase customerDatabase;

    private DatabaseClient(Context context) {
        this.context = context;

        //creating the app database with Room database builder
        //MyToDos is the name of the database
        customerDatabase = Room.databaseBuilder(context, CustomerDatabase.class, "bank").build();
    }

    public static synchronized DatabaseClient getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DatabaseClient(context);
        }
        return mInstance;
    }

    public CustomerDatabase getAppDatabase() {
        return customerDatabase;
    }
}
