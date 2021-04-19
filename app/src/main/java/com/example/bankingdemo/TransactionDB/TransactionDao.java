package com.example.bankingdemo.TransactionDB;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TransactionDao {

    @Query(" SELECT * FROM TransactionEntity ")
    List<TransactionEntity> getAll();

    @Insert
    void insert(TransactionEntity transactionEntity);
}
