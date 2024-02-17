package com.akp.shagun.Banking;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.akp.shagun.R;

import java.util.HashMap;
import java.util.List;

public class Das_ViewReportAdapter extends RecyclerView.Adapter<Das_ViewReportAdapter.VH> {
    Context context;
    List<HashMap<String, String>> arrayList;

    public Das_ViewReportAdapter(Context context, List<HashMap<String, String>> arrayList) {
        this.arrayList=arrayList;
        this.context=context;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.dynamic_das_viewreport, viewGroup, false);
        VH viewHolder = new VH(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {
        vh.tv.setText(arrayList.get(i).get("Name"));
        vh.tv1.setText(arrayList.get(i).get("Message")+"("+arrayList.get(i).get("Mobile")+")");
        vh.tv2.setText(arrayList.get(i).get("RechargeDate"));
        vh.tv4.setText("TransactionType:- "+arrayList.get(i).get("TransactionType"));

        if (arrayList.get(i).get("TransactionType").equalsIgnoreCase("Credit")){
            vh.tv5.setText("+ \u20B9 "+arrayList.get(i).get("Amount"));
            vh.tv5.setTextColor(Color.GREEN);
        }
        else {
            vh.tv5.setText("- \u20B9 "+arrayList.get(i).get("Amount"));
            vh.tv5.setTextColor(Color.RED);

        }

        if (arrayList.get(i).get("Status").equalsIgnoreCase("Success")){
            vh.tv3.setText("Status:- "+arrayList.get(i).get("Status"));
            vh.tv3.setTextColor(Color.GREEN);
        }
        else {
            vh.tv3.setText("Status:- "+arrayList.get(i).get("Status"));
            vh.tv3.setTextColor(Color.RED);

        }


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class VH extends RecyclerView.ViewHolder {
        TextView tv,tv1,tv2,tv3,tv4,tv5;
        public VH(@NonNull View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.tv1);
            tv2 = itemView.findViewById(R.id.tv2);
            tv3 = itemView.findViewById(R.id.tv3);
            tv4 = itemView.findViewById(R.id.tv4);
            tv5=itemView.findViewById(R.id.tv5);
            tv=itemView.findViewById(R.id.tv);
        }
    }
}




