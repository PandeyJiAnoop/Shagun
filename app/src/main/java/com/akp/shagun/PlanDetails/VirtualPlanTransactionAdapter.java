package com.akp.shagun.PlanDetails;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.akp.shagun.R;

import java.util.HashMap;
import java.util.List;

public class VirtualPlanTransactionAdapter extends RecyclerView.Adapter<VirtualPlanTransactionAdapter.VH> {
    Context context;
    List<HashMap<String,String>> arrayList;
    int j=0;
    RecyclerView wallet_histroy;


    public VirtualPlanTransactionAdapter(Context context, List<HashMap<String,String>> arrayList) {
        this.arrayList=arrayList;
        this.context=context;
    }

    @NonNull
    @Override
    public VirtualPlanTransactionAdapter.VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.virtual_list_item_transactions, viewGroup, false);
        VirtualPlanTransactionAdapter.VH viewHolder = new VirtualPlanTransactionAdapter.VH(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VirtualPlanTransactionAdapter.VH vh, int i) {
//        vh.txv_transaction_merchant.setText("("+arrayList.get(i).get("Member_Id")+")");
        vh.txv_transaction_merchant1.setText(arrayList.get(i).get("Particulars"));
        vh.txv_transactions_amount1.setText("\u20B9 "+arrayList.get(i).get("Withdrawl_AMT"));
        vh.txv_transactions_amount.setText("\u20B9 "+arrayList.get(i).get("Diposited_AMT"));
        vh.txv_transaction_date.setText(arrayList.get(i).get("Transaction_Date"));
        vh.txv_transaction_credited_debited.setText(arrayList.get(i).get("paymode"));
        vh.imv_transaction_type.setText("Reciept \n"+arrayList.get(i).get("actualrecieptno"));
        vh.txv_transactions_type.setText(arrayList.get(i).get("Branch"));

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class VH extends RecyclerView.ViewHolder {
        TextView txv_transactions_amount,txv_transactions_amount1,txv_transaction_date,
                txv_transaction_credited_debited,txv_transaction_merchant1,imv_transaction_type,txv_transactions_type;

        public VH(@NonNull View itemView) {
            super(itemView);
//            txv_transaction_merchant=itemView.findViewById(R.id.txv_transaction_merchant);
            txv_transactions_amount1=itemView.findViewById(R.id.txv_transactions_amount1);
            txv_transactions_amount=itemView.findViewById(R.id.txv_transactions_amount);
            txv_transaction_date=itemView.findViewById(R.id.txv_transaction_date);
            txv_transaction_credited_debited=itemView.findViewById(R.id.txv_transaction_credited_debited);
            txv_transaction_merchant1=itemView.findViewById(R.id.txv_transaction_merchant1);
            imv_transaction_type=itemView.findViewById(R.id.imv_transaction_type);
            txv_transactions_type=itemView.findViewById(R.id.txv_transactions_type);
        }
    }



}


