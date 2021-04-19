package com.example.bankingdemo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bankingdemo.CustomerDB.Customer;
import com.example.bankingdemo.CustomerDB.DatabaseClient;
import com.example.bankingdemo.R;

public class TransactionActivity extends AppCompatActivity {

    TextView t_sen,t_email,t_balance, t_rec;
    Button sendMoney;
    EditText amount;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKey";
    public static final String Rname = "r_nameKey";
    public static final String Balance = "balanceKey";
    public static final String Email = "emailKey";
    public static final String moneySent = "ms_key";

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        t_rec= findViewById(R.id.txt_treceiver_name);
        t_email= findViewById(R.id.txt_tsender_email);
        t_balance=findViewById(R.id.txt_tsender_balance);
        t_sen= findViewById(R.id.txt_tsender_name);
        amount=findViewById(R.id.txt_enter_amount);
        sendMoney = findViewById(R.id.bt_se_money);



        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String name = sharedPreferences.getString(Name, "defaultValue");
        String email = sharedPreferences.getString(Email, "defaultValue");
        String balance = sharedPreferences.getString(Balance, "defaultValue");

        t_sen.setText(name);
        t_email.setText(email);
        t_balance.setText(balance);

        final Customer customer1 = (Customer) getIntent().getSerializableExtra("customer_name");
        t_rec.setText(customer1.getCustomer());

        String senderName = name;
        String receiverName = customer1.getCustomer();



        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Rname,receiverName);
        editor.apply();




        sendMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer amt = Integer.parseInt(amount.getText().toString());
                editor.putString(moneySent,amt.toString());
                editor.apply();

                Integer ramt = Integer.parseInt(customer1.getBalance());
                Integer samt = Integer.parseInt(balance);
                Integer r_bal = 0;
                Integer s_bal= 0;

                if(amt>=samt){
                    Toast.makeText(getApplicationContext(), "Insufficient balance in your Account", Toast.LENGTH_LONG).show();
                    editor.clear();
                    startActivity(new Intent(TransactionActivity.this, CustomerListActivity.class));
                    finish();
                }else{
                     r_bal = ramt+amt;
                     s_bal = samt-amt;
                }


                String amt1 = r_bal.toString();
                String amt2 = s_bal.toString();


                updateTask(amt1,receiverName);
                updateTask(amt2,senderName);

                Intent intent = new Intent(TransactionActivity.this, TransactionCompleteActivity.class);
                startActivity(intent);
                finish();

            }
        });


    }
    private  void updateTask(String m, String n){

        class UpdateTask extends AsyncTask<Void,Void,Void>{

            @Override
            protected Void doInBackground(Void... voids) {

                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().getDao().valueUpdate(m,n);
                return  null;
            }
        }

        UpdateTask up = new UpdateTask();
        up.execute();

    }
}