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

public class CategoryServiceListAdapter extends RecyclerView.Adapter<CategoryServiceListAdapter.VH> {
    Context context;
    List<HashMap<String,String>> arrayList;
    public CategoryServiceListAdapter(Context context, List<HashMap<String,String>> arrayList) {
        this.arrayList=arrayList;
        this.context=context;
    }

    @NonNull
    @Override
    public CategoryServiceListAdapter.VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.dynamic_recharge_category, viewGroup, false);
        CategoryServiceListAdapter.VH viewHolder = new CategoryServiceListAdapter.VH(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryServiceListAdapter.VH vh, int i) {
//        vh.title_tv.setText(arrayList.get(i).get("service_name"));
        vh.provider_name_tv.setText(arrayList.get(i).get("provider_Name"));


        if (arrayList.get(i).get("provider_icon").equalsIgnoreCase("")){
            Toast.makeText(context,"Image not found!", Toast.LENGTH_LONG).show();
        }
        else {
            Glide.with(context).load(arrayList.get(i).get("provider_icon")).error(R.drawable.report).into(vh.provider_img);
        }

        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, MobileRecharge.class);
                intent.putExtra("servicename",arrayList.get(i).get("provider_Name")+"("+arrayList.get(i).get("service_name")+")");
                intent.putExtra("provider_id",arrayList.get(i).get("provider_id"));
                intent.putExtra("onlyservice",arrayList.get(i).get("service_name"));
                context.startActivity(intent);
            }
        });





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


