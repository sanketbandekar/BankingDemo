package com.example.bankingdemo.CustomerDB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.bankingdemo.CustomerDB.Customer;
import com.example.bankingdemo.CustomerDB.CustomerDao;

@Database(entities = {Customer.class}, version = 1, exportSchema = false)
public abstract class CustomerDatabase extends RoomDatabase {

    public abstract CustomerDao getDao();
}
