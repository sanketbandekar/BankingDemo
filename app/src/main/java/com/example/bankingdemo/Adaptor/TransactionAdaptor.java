package com.example.bankingdemo.Adaptor;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bankingdemo.R;
import com.example.bankingdemo.TransactionDB.TransactionEntity;

import java.util.List;

public class TransactionAdaptor extends RecyclerView.Adapter<TransactionAdaptor.TransactionHolder> {

    private List<TransactionEntity> transactionEntityList;
    private Context context;

    public TransactionAdaptor(Context context,List<TransactionEntity> transactionEntityList1){
        this.context= context;
        this.transactionEntityList= transactionEntityList1;
    }

    @NonNull
    @Override
    public TransactionAdaptor.TransactionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.transaction_list, parent, false);
        return new TransactionAdaptor.TransactionHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionAdaptor.TransactionHolder holder, int position) {
        TransactionEntity transactionEntity = transactionEntityList.get(position);
        holder.txtSender.setText(transactionEntity.getSenderName());
        holder.txtReceiver.setText(transactionEntity.getReceiverName());
        holder.txtAmount.setText(transactionEntity.getAmount());

    }

    @Override
    public int getItemCount() {
        return transactionEntityList.size();
    }

    public class TransactionHolder extends RecyclerView.ViewHolder{

        TextView txtSender, txtReceiver,txtAmount;
        public TransactionHolder(@NonNull View itemView) {
            super(itemView);
            txtAmount = itemView.findViewById(R.id.txt_tra_amt);
            txtReceiver = itemView.findViewById(R.id.txt_tra_rname);
            txtSender = itemView.findViewById(R.id.txt_tra_sname);
        }
    }
}
