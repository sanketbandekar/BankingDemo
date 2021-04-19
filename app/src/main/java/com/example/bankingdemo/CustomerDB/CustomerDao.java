package com.example.bankingdemo.CustomerDB;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RoomWarnings;

import com.example.bankingdemo.CustomerDB.Customer;

import java.util.List;

@Dao
public interface CustomerDao {
    @Query(" SELECT * FROM Customer ")
    List<Customer> getAll();

    @Query(" SELECT id,customerName,balance FROM Customer WHERE customerName <>:d")
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    List<Customer> getCustomerName(String d);

    @Query(" UPDATE Customer SET balance =:s WHERE customerName =:n ")
    void valueUpdate(String s, String n);

    @Insert
    void insert(Customer c);

}
