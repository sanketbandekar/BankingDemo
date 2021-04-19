package com.example.bankingdemo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.bankingdemo.Adaptor.CustomerAdaptor;
import com.example.bankingdemo.CustomerDB.Customer;
import com.example.bankingdemo.CustomerDB.DatabaseClient;
import com.example.bankingdemo.R;

import java.util.List;

public class CustomerListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);

        recyclerView = findViewById(R.id.txt_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getTasks();
    }
    public void getTasks(){

        class GetTasks extends AsyncTask<Void,Void, List<Customer>> {

            @Override
            protected List<Customer> doInBackground(Void... voids) {
                List<Customer> customerList = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().getDao().getAll();
                return  customerList;
            }
            protected void onPostExecute(List<Customer> cl) {
                super.onPostExecute(cl);
                CustomerAdaptor adapter = new CustomerAdaptor(CustomerListActivity.this,cl);
                recyclerView.setAdapter(adapter);
            }
        }
        GetTasks gt = new GetTasks();
        gt.execute();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(CustomerListActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}