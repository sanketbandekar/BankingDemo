package com.example.bankingdemo.TransactionDB;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {TransactionEntity.class}, version = 1, exportSchema = false)
public abstract class TransactionDatabase extends RoomDatabase {

    public abstract TransactionDao getTransactionDao();
}
