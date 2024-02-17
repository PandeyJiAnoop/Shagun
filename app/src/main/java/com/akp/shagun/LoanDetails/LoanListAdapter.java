package com.akp.shagun.LoanDetails;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.akp.shagun.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoanListAdapter extends RecyclerView.Adapter<LoanListAdapter.VH> {
    Context context;
    List<HashMap<String,String>> arrayList;
    int j=0;
    RecyclerView wallet_histroy;
    ArrayList<HashMap<String, String>> arrayList1 = new ArrayList<>();

    public LoanListAdapter(Context context, List<HashMap<String,String>> arrayList) {
        this.arrayList=arrayList;
        this.context=context;
    }

    @NonNull
    @Override
    public LoanListAdapter.VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.dynamic_loan_list, viewGroup, false);
        LoanListAdapter.VH viewHolder = new LoanListAdapter.VH(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LoanListAdapter.VH vh, int i) {
//        vh.sno.setText("1");
        vh.LoanIDtv.setText(arrayList.get(i).get("LoanID"));
        vh.MemberIdtv.setText(arrayList.get(i).get("MemberId"));
        vh.MemberNametv.setText(arrayList.get(i).get("MemberName"));
        vh.InstalNotv.setText(arrayList.get(i).get("InstalNo"));
        vh.InsDatetv.setText(arrayList.get(i).get("InsDate"));
        vh.InstAmounttv.setText("\u20B9 "+ arrayList.get(i).get("InstAmount"));
        vh.statustv.setText(arrayList.get(i).get("status"));
        vh.paidtv.setText("\u20B9 "+arrayList.get(i).get("paid"));
        vh.PaidDatetv.setText(arrayList.get(i).get("PaidDate"));

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class VH extends RecyclerView.ViewHolder {
        TextView sno,LoanIDtv,MemberIdtv,MemberNametv,InstalNotv,InsDatetv,InstAmounttv,
                paidtv,PaidDatetv,statustv;



        public VH(@NonNull View itemView) {
            super(itemView);
//            sno=itemView.findViewById(R.id.sno);
            LoanIDtv=itemView.findViewById(R.id.LoanIDtv);
            MemberIdtv=itemView.findViewById(R.id.MemberIdtv);
            MemberNametv=itemView.findViewById(R.id.MemberNametv);
            InstalNotv=itemView.findViewById(R.id.InstalNotv);

            InsDatetv=itemView.findViewById(R.id.InsDatetv);
            InstAmounttv=itemView.findViewById(R.id.InstAmounttv);
            paidtv=itemView.findViewById(R.id.paidtv);

            PaidDatetv=itemView.findViewById(R.id.PaidDatetv);
            statustv=itemView.findViewById(R.id.statustv);



        }
    }




}


