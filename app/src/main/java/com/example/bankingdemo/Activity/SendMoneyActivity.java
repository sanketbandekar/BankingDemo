package com.example.bankingdemo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.bankingdemo.CustomerDB.Customer;
import com.example.bankingdemo.R;

public class SendMoneyActivity extends AppCompatActivity {

    TextView sm_name,sm_email,sm_balance;
    Button  sTo_button;
    public String temp_name;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKey";
    public static final String Balance = "balanceKey";
    public static final String Email = "emailKey";

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_money);

        sm_name= findViewById(R.id.txt_list_name);
        sm_email = findViewById(R.id.txt_list_email);
        sm_balance= findViewById(R.id.txt_list_balance);
        sTo_button = findViewById(R.id.sendTo);

        final Customer customer = (Customer) getIntent().getSerializableExtra("customer_value");
        loadTask(customer);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putString(Name, customer.getCustomer());
        editor.putString(Email, customer.getEmail());
        editor.putString(Balance, customer.getBalance());
        editor.apply();

        sTo_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                temp_name = customer.getCustomer();
                Intent intent = new Intent(SendMoneyActivity.this, CustomerNameActivity.class);
                intent.putExtra("temp_custName",temp_name);
                startActivity(intent);

            }
        });

    }


    private  void loadTask(Customer customer){
        sm_name.setText(customer.getCustomer());
        sm_email.setText(customer.getEmail());
        sm_balance.setText(customer.getBalance());
    }

}