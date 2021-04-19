package com.example.bankingdemo.CustomerDB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Room;
import androidx.room.RoomWarnings;

import java.io.Serializable;

@Entity
public class Customer implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "customerName")
    private String customer;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "balance")
    private String balance;


    public int getId() {
        return id;
    }

    public String getCustomer() {
        return customer;
    }

    public String getEmail() {
        return email;
    }

    public String getBalance() {
        return balance;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
