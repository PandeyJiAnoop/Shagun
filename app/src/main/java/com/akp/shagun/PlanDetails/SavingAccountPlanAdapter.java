package com.akp.shagun.PlanDetails;


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

public class SavingAccountPlanAdapter extends RecyclerView.Adapter<SavingAccountPlanAdapter.VH> {
    Context context;
    List<HashMap<String,String>> arrayList;
    int j=0;
    RecyclerView wallet_histroy;
    ArrayList<HashMap<String, String>> arrayList1 = new ArrayList<>();

    public SavingAccountPlanAdapter(Context context, List<HashMap<String,String>> arrayList) {
        this.arrayList=arrayList;
        this.context=context;
    }

    @NonNull
    @Override
    public SavingAccountPlanAdapter.VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.dynamic_saving_plan, viewGroup, false);
        SavingAccountPlanAdapter.VH viewHolder = new SavingAccountPlanAdapter.VH(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SavingAccountPlanAdapter.VH vh, int i) {

        vh.Branchtv.setText(arrayList.get(i).get("Branch"));
        vh.Member_Idtv.setText(arrayList.get(i).get("Member_Id"));
        vh.account_notv.setText(arrayList.get(i).get("account_no"));
        vh.Particularstv.setText(arrayList.get(i).get("Particulars"));
        vh.Transaction_Datetv.setText(arrayList.get(i).get("Transaction_Date"));
        vh.Withdrawl_AMTtv.setText("\u20B9 "+arrayList.get(i).get("Withdrawl_AMT"));
        vh.Diposited_AMTtv.setText("\u20B9 "+arrayList.get(i).get("Diposited_AMT"));
        vh.actualrecieptnotv.setText(arrayList.get(i).get("actualrecieptno"));
        vh.paymodetv.setText(arrayList.get(i).get("paymode"));
        vh.chquenotv.setText(arrayList.get(i).get("chqueno"));
        vh.Introducer_IDtv.setText(arrayList.get(i).get("Introducer_ID"));
        

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class VH extends RecyclerView.ViewHolder {
        TextView Branchtv,Member_Idtv,account_notv,Particularstv,Transaction_Datetv,Withdrawl_AMTtv,Diposited_AMTtv,actualrecieptnotv,paymodetv,
                chquenotv,Introducer_IDtv;




        public VH(@NonNull View itemView) {
            super(itemView);
            Branchtv=itemView.findViewById(R.id.Branchtv);

            Member_Idtv=itemView.findViewById(R.id.Member_Idtv);
            account_notv=itemView.findViewById(R.id.account_notv);
            Particularstv=itemView.findViewById(R.id.Particularstv);
//            status_tv=itemView.findViewById(R.id.status_tv);

            Transaction_Datetv=itemView.findViewById(R.id.Transaction_Datetv);
            Withdrawl_AMTtv=itemView.findViewById(R.id.Withdrawl_AMTtv);
            Diposited_AMTtv=itemView.findViewById(R.id.Diposited_AMTtv);

            actualrecieptnotv=itemView.findViewById(R.id.actualrecieptnotv);
            paymodetv=itemView.findViewById(R.id.paymodetv);
            chquenotv=itemView.findViewById(R.id.chquenotv);
            Introducer_IDtv=itemView.findViewById(R.id.Introducer_IDtv);





        }
    }
    



}