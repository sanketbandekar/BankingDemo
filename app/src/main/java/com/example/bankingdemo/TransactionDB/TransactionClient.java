package com.example.bankingdemo.TransactionDB;

import android.content.Context;

import androidx.room.Room;

public class TransactionClient {

    private Context context;
    private static TransactionClient transactionClient;

    private TransactionDatabase transactionDatabase;

    private TransactionClient(Context context){
        this.context = context;

        transactionDatabase = Room.databaseBuilder(context,TransactionDatabase.class,"transactionDetails").build();
    }

    public static synchronized TransactionClient getInstance(Context context) {
        if (transactionClient == null) {
            transactionClient = new TransactionClient(context);
        }
        return transactionClient;
    }
    public TransactionDatabase getTransactionDatabase(){
        return transactionDatabase;
    }
}
