package com.example.bankingdemo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.bankingdemo.Adaptor.TransactionAdaptor;
import com.example.bankingdemo.R;
import com.example.bankingdemo.TransactionDB.TransactionClient;
import com.example.bankingdemo.TransactionDB.TransactionEntity;

import java.util.List;

public class TransactionHistoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);

        recyclerView=findViewById(R.id.txt_tra_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getHistory();

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(TransactionHistoryActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    public void getHistory(){
        class GetHistory extends AsyncTask<Void,Void, List<TransactionEntity>>{

            @Override
            protected List<TransactionEntity> doInBackground(Void... voids) {
                List<TransactionEntity> transactionList = TransactionClient.getInstance(getApplicationContext()).getTransactionDatabase().getTransactionDao()
                        .getAll();

                return transactionList;
            }
            protected void onPostExecute(List<TransactionEntity> t) {
                super.onPostExecute(t);
                TransactionAdaptor adapter = new TransactionAdaptor(TransactionHistoryActivity.this,t);
                recyclerView.setAdapter(adapter);
            }
        }

        GetHistory gh = new GetHistory();
        gh.execute();
    }
}