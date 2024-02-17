package com.akp.shagun.PlanDetails;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.akp.shagun.R;

import java.util.HashMap;
import java.util.List;

public class AllPlanTransactionAdapter extends RecyclerView.Adapter<AllPlanTransactionAdapter.VH> {
    Context context;
    List<HashMap<String,String>> arrayList;

    public AllPlanTransactionAdapter(Context context, List<HashMap<String,String>> arrayList) {
        this.arrayList=arrayList;
        this.context=context;
    }

    @NonNull
    @Override
    public AllPlanTransactionAdapter.VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_transactions, viewGroup, false);
        AllPlanTransactionAdapter.VH viewHolder = new AllPlanTransactionAdapter.VH(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AllPlanTransactionAdapter.VH vh, int i) {
        vh.txv_transaction_merchant.setText(arrayList.get(i).get("MemberName")+"- "+arrayList.get(i).get("MemberId"));
        if(arrayList.get(i).get("Fresh").equalsIgnoreCase("0.00")){
            vh.txv_transactions_amount.setVisibility(View.GONE);
        }
        else {
            vh.txv_transactions_amount.setText("Fresh:- "+arrayList.get(i).get("Fresh"));
        }
        if(arrayList.get(i).get("Renewal").equalsIgnoreCase("0.00")){
            vh.txv_transactions_amount1.setVisibility(View.GONE);
        }
        else {
            vh.txv_transactions_amount1.setText("Renewal:-"+arrayList.get(i).get("Renewal"));
        }

        vh.txv_transaction_date.setText(arrayList.get(i).get("InstallmentDate"));
        vh.txv_transaction_credited_debited.setText(arrayList.get(i).get("Branch"));

        double price1 = Double.valueOf(arrayList.get(i).get("Renewal"));
            Log.d("dfdfdfdfdfdfdf", String.valueOf(price1));}



    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class VH extends RecyclerView.ViewHolder {
        TextView txv_transaction_merchant,txv_transactions_amount,txv_transactions_amount1,txv_transaction_date,txv_transaction_credited_debited;

        public VH(@NonNull View itemView) {
            super(itemView);
            txv_transaction_merchant=itemView.findViewById(R.id.txv_transaction_merchant);
            txv_transactions_amount1=itemView.findViewById(R.id.txv_transactions_amount1);
            txv_transactions_amount=itemView.findViewById(R.id.txv_transactions_amount);
            txv_transaction_date=itemView.findViewById(R.id.txv_transaction_date);
            txv_transaction_credited_debited=itemView.findViewById(R.id.txv_transaction_credited_debited);
        }
    }
    


}


