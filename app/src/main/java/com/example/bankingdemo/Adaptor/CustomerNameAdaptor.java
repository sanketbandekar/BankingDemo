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
import com.example.bankingdemo.Activity.TransactionActivity;

import java.util.List;

public class CustomerNameAdaptor extends RecyclerView.Adapter<CustomerNameAdaptor.MyCustomerNames> {
    private List<Customer> customerList;
    private Context context;

    public CustomerNameAdaptor(Context context, List<Customer> customerList1){
        this.context=context;
        this.customerList =  customerList1;
    }
    @NonNull
    @Override
    public CustomerNameAdaptor.MyCustomerNames onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_name,parent,false);
        return new MyCustomerNames(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerNameAdaptor.MyCustomerNames holder, int position) {

        Customer customer = customerList.get(position);
        holder.listNames.setText(customer.getCustomer());

    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }

    public class MyCustomerNames extends  RecyclerView.ViewHolder implements View.OnClickListener {

        TextView listNames;
        public MyCustomerNames(@NonNull View itemView) {
            super(itemView);
            listNames = itemView.findViewById(R.id.txt_list_names);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {


            Customer customer = customerList.get(getAdapterPosition());
            Intent intent = new Intent(context, TransactionActivity.class);
            intent.putExtra("customer_name", customer);
            context.startActivity(intent);

        }
    }
}
