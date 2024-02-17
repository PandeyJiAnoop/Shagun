package com.akp.shagun.LoanDetails;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.akp.shagun.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoanDetailsAdapter extends RecyclerView.Adapter<LoanDetailsAdapter.VH> {
    Context context;
    List<HashMap<String,String>> arrayList;
    int j=0;
    RecyclerView wallet_histroy;
    ArrayList<HashMap<String, String>> arrayList1 = new ArrayList<>();

    public LoanDetailsAdapter(Context context, List<HashMap<String,String>> arrayList) {
        this.arrayList=arrayList;
        this.context=context;
    }

    @NonNull
    @Override
    public LoanDetailsAdapter.VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.dynamic_loan, viewGroup, false);
        LoanDetailsAdapter.VH viewHolder = new LoanDetailsAdapter.VH(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LoanDetailsAdapter.VH vh, int i) {
//        vh.sno.setText("1");
        vh.LoanIDtv.setText(arrayList.get(i).get("LoanID"));
        vh.Branchtv.setText(arrayList.get(i).get("Branch"));
        vh.AccountNotv.setText(arrayList.get(i).get("AccountNo"));
        vh.Memberidtv.setText(arrayList.get(i).get("Memberid"));
        vh.MemberNametv.setText(arrayList.get(i).get("MemberName"));
        vh.PlanTypetv.setText(arrayList.get(i).get("PlanType"));
        vh.LoanAmounttv.setText("\u20B9 "+arrayList.get(i).get("LoanAmount"));
        vh.insurance_tv.setText("\u20B9 "+arrayList.get(i).get("Insurancee"));
        vh.Processing_chtv.setText("\u20B9 "+ arrayList.get(i).get("Processing_ch"));
        vh.GrandTotaltv.setText("\u20B9 "+arrayList.get(i).get("GrandTotal"));
        vh.LoanDatetv.setText(arrayList.get(i).get("LoanDate"));
        vh.Installmentstv.setText(arrayList.get(i).get("Installments")+" Days ");
        vh.InstAmounttv.setText("\u20B9 "+arrayList.get(i).get("InstAmount"));

        vh.view_details_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,LoanRecovery.class);
                intent.putExtra("loan_id",arrayList.get(i).get("LoanID"));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class VH extends RecyclerView.ViewHolder {
        TextView sno,LoanIDtv,Branchtv,AccountNotv,Memberidtv,MemberNametv,PlanTypetv,
                LoanAmounttv,insurance_tv,Processing_chtv,GrandTotaltv,LoanDatetv,Installmentstv,InstAmounttv;
        LinearLayout view_details_ll;

        public VH(@NonNull View itemView) {
            super(itemView);
            sno=itemView.findViewById(R.id.sno);
            view_details_ll=itemView.findViewById(R.id.view_details_ll);
            LoanIDtv=itemView.findViewById(R.id.LoanIDtv);
            Branchtv=itemView.findViewById(R.id.Branchtv);
            AccountNotv=itemView.findViewById(R.id.AccountNotv);
            Memberidtv=itemView.findViewById(R.id.Memberidtv);
            MemberNametv=itemView.findViewById(R.id.MemberNametv);
            PlanTypetv=itemView.findViewById(R.id.PlanTypetv);
            LoanAmounttv=itemView.findViewById(R.id.LoanAmounttv);
            insurance_tv=itemView.findViewById(R.id.insurance_tv);
            Processing_chtv=itemView.findViewById(R.id.Processing_chtv);
            GrandTotaltv=itemView.findViewById(R.id.GrandTotaltv);
            LoanDatetv=itemView.findViewById(R.id.LoanDatetv);
            Installmentstv=itemView.findViewById(R.id.Installmentstv);
            InstAmounttv=itemView.findViewById(R.id.InstAmounttv);
        }
    }




}


