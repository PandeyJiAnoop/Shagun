package com.akp.shagun.Mobile;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.akp.shagun.R;
import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DasRechargeListAdapter extends RecyclerView.Adapter<DasRechargeListAdapter.VH> {
    Context context;
    List<HashMap<String,String>> arrayList;
    public DasRechargeListAdapter(Context context, List<HashMap<String,String>> arrayList) {
        this.arrayList=arrayList;
        this.context=context;
    }

    @NonNull
    @Override
    public DasRechargeListAdapter.VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.dynamic_dashboard, viewGroup, false);
        DasRechargeListAdapter.VH viewHolder = new DasRechargeListAdapter.VH(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DasRechargeListAdapter.VH vh, int i) {
//        vh.title_tv.setText(arrayList.get(i).get("service_name"));
        vh.provider_name_tv.setText(arrayList.get(i).get("service_name"));


        if (arrayList.get(i).get("service_icon").equalsIgnoreCase("")){
            Toast.makeText(context,"Image not found!",Toast.LENGTH_LONG).show();
        }
        else {
            Glide.with(context).load("https://shagunmicrofinance.com/"+arrayList.get(i).get("service_icon")).error(R.drawable.report).into(vh.provider_img);
        }



        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, GetCategoryDetails.class);
                intent.putExtra("service_name",arrayList.get(i).get("service_name"));
                context.startActivity(intent);
            }
        });





     /*   if (arrayList.get(i).get("service_name").equalsIgnoreCase("MobileRechage")){
            vh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, MobileRecharge.class);
                    intent.putExtra("servicename",arrayList.get(i).get("provider_name")+"("+arrayList.get(i).get("service_name")+")");
                    intent.putExtra("providerid",arrayList.get(i).get("provider_id"));
                    context.startActivity(intent);
                }
            });
        }
        else {
            Toast.makeText(context,"Only Mobile Recharge Functionality!",Toast.LENGTH_LONG).show();
        }*/



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public class VH extends RecyclerView.ViewHolder {
        TextView provider_name_tv;
        CircleImageView provider_img;

        public VH(@NonNull View itemView) {
            super(itemView);
            provider_name_tv = itemView.findViewById(R.id.provider_name_tv);
            provider_img = itemView.findViewById(R.id.provider_img);
        }
    }
}
