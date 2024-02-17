package com.akp.shagun.AssociateDashboard;


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

public class TotalBusinessAdapter extends RecyclerView.Adapter<TotalBusinessAdapter.VH> {
    Context context;
    List<HashMap<String,String>> arrayList;
    int j=0;
    RecyclerView wallet_histroy;


    public TotalBusinessAdapter(Context context, List<HashMap<String,String>> arrayList) {
        this.arrayList=arrayList;
        this.context=context;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.dynamic_totalbusines, viewGroup, false);
        VH viewHolder = new VH(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {
        vh.Member_nametv.setText(arrayList.get(i).get("Member_name")+"- "+arrayList.get(i).get("Member_Id"));
        vh.Account_Notv.setText(arrayList.get(i).get("PlanType"));
        vh.Freshtv.setText("Fresh- \u20B9 "+arrayList.get(i).get("Fresh"));
        vh.Renewaltv.setText("Renewal- \u20B9 "+arrayList.get(i).get("Renewal"));
        vh.Member_Idtv.setText(arrayList.get(i).get("Account_No"));




    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class VH extends RecyclerView.ViewHolder {
        TextView Member_nametv,Account_Notv,Freshtv,Renewaltv,Member_Idtv;


        public VH(@NonNull View itemView) {
            super(itemView);
            Member_nametv=itemView.findViewById(R.id.Member_nametv);
            Account_Notv=itemView.findViewById(R.id.Account_Notv);
            Freshtv=itemView.findViewById(R.id.Freshtv);
            Renewaltv=itemView.findViewById(R.id.Renewaltv);
            Member_Idtv=itemView.findViewById(R.id.Member_Idtv);
        }
    }



}

