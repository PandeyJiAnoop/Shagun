package com.akp.shagun.APIServices;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.akp.shagun.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BillPaymentAdapter extends RecyclerView.Adapter<BillPaymentAdapter.VH> {
    Context context;
    List<HashMap<String, String>> arrayList;
    int j=0;
    RecyclerView wallet_histroy;
    ArrayList<HashMap<String, String>> arrayList1 = new ArrayList<>();
    double aresult;
    private String provider;

    public BillPaymentAdapter(Context context, List<HashMap<String, String>> arrayList) {
        this.arrayList=arrayList;
        this.context=context;
    }

    @NonNull
    @Override
    public BillPaymentAdapter.VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.bill_provider_list, viewGroup, false);
        BillPaymentAdapter.VH viewHolder = new BillPaymentAdapter.VH(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BillPaymentAdapter.VH vh, int i) {
        vh.title_tv.setText(arrayList.get(i).get("ServiceType"));
        String img_url="https://shagunmicrofinance.com"+arrayList.get(i).get("ServiceIcon");
        String newUrl = img_url.replaceAll(" ", "");
      /*  if (arrayList.get(i).get("ServiceIcon").equalsIgnoreCase("")){
            Glide.with(context).load(R.drawable.a_bill).into(vh.img);
        }
        else {
            Glide.with(context).load(newUrl).into(vh.img);
        }*/
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(context, SRSMobileRecharge.class);
//                intent.putExtra("servicename",arrayList.get(i).get("Name"));
//                intent.putExtra("providerid",arrayList.get(i).get("Code"));
//                context.startActivity(intent);
            }
        });




    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class VH extends RecyclerView.ViewHolder {
        TextView title_tv;

        ImageView img;



        public VH(@NonNull View itemView) {
            super(itemView);
            title_tv=itemView.findViewById(R.id.title_tv);
            img=itemView.findViewById(R.id.img);

        }
    }



}
