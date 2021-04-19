package com.example.bankingdemo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.bankingdemo.R;
import com.example.bankingdemo.TransactionDB.TransactionClient;
import com.example.bankingdemo.TransactionDB.TransactionEntity;

public class TransactionCompleteActivity extends AppCompatActivity {

    Button buttonOK;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKey";
    public static final String Rname = "r_nameKey";
    public static final String moneySent = "ms_key";

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_complete);

        buttonOK = findViewById(R.id.ok_bt);

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String senderName = sharedPreferences.getString(Name, "defaultValue");
        String receiverName = sharedPreferences.getString(Rname,"defaultValue");
        String amount = sharedPreferences.getString(moneySent,"defaultValue");
        saveTransaction(senderName,receiverName,amount);

        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TransactionCompleteActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(TransactionCompleteActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void saveTransaction(String a, String b, String c){

        class SaveTransaction extends AsyncTask<Void,Void,Void>{

            @Override
            protected Void doInBackground(Void... voids) {
                TransactionEntity transactionEntity = new TransactionEntity();
                transactionEntity.setSenderName(a);
                transactionEntity.setReceiverName(b);
                transactionEntity.setAmount(c);

                TransactionClient.getInstance(getApplicationContext()).getTransactionDatabase().getTransactionDao().insert(transactionEntity);
                return null;
            }
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(),"Transaction Complete",Toast.LENGTH_SHORT).show();
            }
        }

        SaveTransaction saveTransaction = new SaveTransaction();
        saveTransaction.execute();
    }
}