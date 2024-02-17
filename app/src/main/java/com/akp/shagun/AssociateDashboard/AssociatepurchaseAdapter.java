package com.akp.shagun.AssociateDashboard;


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

public class AssociatepurchaseAdapter extends RecyclerView.Adapter<AssociatepurchaseAdapter.VH> {
    Context context;
    List<HashMap<String,String>> arrayList;
    int j=0;
    RecyclerView wallet_histroy;
    ArrayList<HashMap<String, String>> arrayList1 = new ArrayList<>();
    public AssociatepurchaseAdapter(Context context, List<HashMap<String,String>> arrayList) {
        this.arrayList=arrayList;
        this.context=context;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.dynamic_associate_purchase, viewGroup, false);
        VH viewHolder = new VH(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {
        vh.Member_Idtv.setText("Member Id- "+arrayList.get(i).get("Member_Id"));
        vh.Member_nametv.setText(arrayList.get(i).get("Member_name"));
        vh.Branchtv.setText(arrayList.get(i).get("Branch"));
        vh.father_nametv.setText(arrayList.get(i).get("father_name"));
        vh.mobiletv.setText(arrayList.get(i).get("mobile"));
        vh.gendertv.setText(arrayList.get(i).get("gender"));
        vh.RegDatetv.setText(arrayList.get(i).get("RegDate"));

        vh.view_details_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context,AssociateMemberDetails.class);
                intent.putExtra("memberid",arrayList.get(i).get("Member_Id"));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class VH extends RecyclerView.ViewHolder {
        TextView Member_Idtv,Member_nametv,Branchtv,father_nametv,mobiletv,gendertv,RegDatetv;
        LinearLayout view_details_ll;

        public VH(@NonNull View itemView) {
            super(itemView);
            Member_Idtv=itemView.findViewById(R.id.Member_Idtv);
            Member_nametv=itemView.findViewById(R.id.Member_nametv);
            Branchtv=itemView.findViewById(R.id.Branchtv);
            father_nametv=itemView.findViewById(R.id.father_nametv);

            mobiletv=itemView.findViewById(R.id.mobiletv);
            gendertv=itemView.findViewById(R.id.gendertv);
            RegDatetv=itemView.findViewById(R.id.RegDatetv);

            view_details_ll=itemView.findViewById(R.id.view_details_ll);

        }
    }



}


