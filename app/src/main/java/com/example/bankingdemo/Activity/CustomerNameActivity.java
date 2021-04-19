package com.example.bankingdemo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.example.bankingdemo.Adaptor.CustomerNameAdaptor;
import com.example.bankingdemo.CustomerDB.Customer;
import com.example.bankingdemo.CustomerDB.DatabaseClient;
import com.example.bankingdemo.R;

import java.util.List;

public class CustomerNameActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_name);

        recyclerView=findViewById(R.id.txt_name_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        String temp_message = intent.getStringExtra("temp_custName");
        Toast.makeText(getApplicationContext(), "Sender Name is : "+temp_message, Toast.LENGTH_SHORT).show();
        getNames(temp_message);
    }

    public void getNames(String d){

        class  GetNames extends AsyncTask<Void,Void, List<Customer>>{

            @Override
            protected List<Customer> doInBackground(Void... voids) {
                List<Customer> customerName = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().getDao().getCustomerName(d);
                return  customerName;
            }
            protected void onPostExecute(List<Customer> cl) {
                super.onPostExecute(cl);
                CustomerNameAdaptor adapter = new CustomerNameAdaptor(CustomerNameActivity.this,cl);
                recyclerView.setAdapter(adapter);
            }
        }
         GetNames gn = new GetNames();
        gn.execute();
    }
}