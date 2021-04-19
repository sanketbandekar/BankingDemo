package com.example.bankingdemo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.bankingdemo.CustomerDB.Customer;
import com.example.bankingdemo.CustomerDB.DatabaseClient;
import com.example.bankingdemo.R;

public class MainActivity extends AppCompatActivity {

    Button bt_cl,bt_history,bt_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_cl= findViewById(R.id.bt_customer_list);
        bt_history= findViewById(R.id.bt_customer_history);
        bt_add = findViewById(R.id.add);

        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCustomer("Aakav","aakav@rocketmail.com","10000");
                saveCustomer("Mayan","mayan34@rocketmail.com","25000");
                saveCustomer("Meet","meet@rocketmail.com","25500");
                saveCustomer("Ronit","ronit4@rocketmail.com","20000");
                saveCustomer("Advik","advik@rocketmail.com","35000");
                saveCustomer("Yug","yug@rocketmail.com","5000");
                saveCustomer("Darsh","darsh99@rocketmail.com","5500");
                saveCustomer("Pranjal","pranjal@rocketmail.com","5050");
                saveCustomer("Rayaan","rayaan5@rocketmail.com","20050");
                saveCustomer("Ekansh","ekansh@rocketmail.com","25600");
                Toast.makeText(getApplicationContext(),"Customers added to data",Toast.LENGTH_SHORT).show();
            }
        });




        bt_cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CustomerListActivity.class));
            }
        });

        bt_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TransactionHistoryActivity.class));
            }
        });


    }

    public void saveCustomer(String n, String e, String b){

        class SaveTask extends AsyncTask<Void,Void,Void>{

            @Override
            protected Void doInBackground(Void... voids) {
                Customer customer = new Customer();
                customer.setCustomer(n);
                customer.setEmail(e);
                customer.setBalance(b);

                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().getDao().insert(customer);
                return null;
            }
        }
        SaveTask st = new SaveTask();
        st.execute();
    }


}