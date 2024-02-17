package com.akp.shagun.AssociateDashboard;

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

public class AssociateMemberDetailsAdapter extends RecyclerView.Adapter<AssociateMemberDetailsAdapter.VH> {
    Context context;
    List<HashMap<String,String>> arrayList;
    int j=0;
    RecyclerView wallet_histroy;
    ArrayList<HashMap<String, String>> arrayList1 = new ArrayList<>();
    public AssociateMemberDetailsAdapter(Context context, List<HashMap<String,String>> arrayList) {
        this.arrayList=arrayList;
        this.context=context;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.dynamic_associate_memberdetailshistory, viewGroup, false);
        VH viewHolder = new VH(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {
        vh.Member_Idtv.setText(arrayList.get(i).get("Member_Id"));
        vh.Member_nametv.setText(arrayList.get(i).get("Member_name"));
        vh.LoanRequiredtv.setText(arrayList.get(i).get("LoanRequired"));
        vh.account_notv.setText(arrayList.get(i).get("account_no"));
        vh.PlanTypetv.setText(arrayList.get(i).get("PlanType"));
        vh.DepsitAMotv.setText("\u20B9 "+arrayList.get(i).get("DepsitAMo"));
        vh.Purchase_datetv.setText(arrayList.get(i).get("Purchase_date"));
        vh.Maturitytv.setText("\u20B9 "+arrayList.get(i).get("Maturity"));
        vh.MaturityDatetv.setText(arrayList.get(i).get("MaturityDate"));


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class VH extends RecyclerView.ViewHolder {
        TextView Member_Idtv,Member_nametv,LoanRequiredtv,account_notv,PlanTypetv,DepsitAMotv,
                Purchase_datetv,Maturitytv,MaturityDatetv;

        public VH(@NonNull View itemView) {
            super(itemView);
            Member_Idtv=itemView.findViewById(R.id.Member_Idtv);
            Member_nametv=itemView.findViewById(R.id.Member_nametv);
            LoanRequiredtv=itemView.findViewById(R.id.LoanRequiredtv);
            account_notv=itemView.findViewById(R.id.account_notv);

            PlanTypetv=itemView.findViewById(R.id.PlanTypetv);
            DepsitAMotv=itemView.findViewById(R.id.DepsitAMotv);
            Purchase_datetv=itemView.findViewById(R.id.Purchase_datetv);

            Maturitytv=itemView.findViewById(R.id.Maturitytv);
            MaturityDatetv=itemView.findViewById(R.id.MaturityDatetv);
        }
    }



}


