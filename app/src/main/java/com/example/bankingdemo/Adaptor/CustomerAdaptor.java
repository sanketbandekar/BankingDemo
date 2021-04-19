package com.example.bankingdemo.Adaptor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bankingdemo.CustomerDB.Customer;
import com.example.bankingdemo.R;
import com.example.bankingdemo.Activity.SendMoneyActivity;

import java.util.List;

public class CustomerAdaptor extends RecyclerView.Adapter<CustomerAdaptor.MyViewHolder> {

    private List<Customer> customerList;
    private Context context;

    public CustomerAdaptor(Context context, List<Customer> customerList1){
        this.context=context;
        this.customerList =  customerList1;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Customer customer = customerList.get(position);
        holder.ad_txt_name.setText(customer.getCustomer());
        holder.ad_txt_email.setText(customer.getEmail());
        holder.ad_txt_balance.setText(customer.getBalance());

    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView ad_txt_name, ad_txt_email,ad_txt_balance;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ad_txt_name =itemView.findViewById(R.id.txt_list_name);
            ad_txt_email=itemView.findViewById(R.id.txt_list_email);
            ad_txt_balance=itemView.findViewById(R.id.txt_list_balance);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            Customer customer = customerList.get(getAdapterPosition());
            Intent intent = new Intent(context, SendMoneyActivity.class);
            intent.putExtra("customer_value", customer); //this is used in update task adaptor

            context.startActivity(intent);

        }
    }
}
