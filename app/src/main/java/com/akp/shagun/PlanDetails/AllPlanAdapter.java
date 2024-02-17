package com.akp.shagun.PlanDetails;


import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.akp.shagun.Api_Urls;
import com.akp.shagun.R;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllPlanAdapter extends RecyclerView.Adapter<AllPlanAdapter.VH> {
    Context context;
    List<HashMap<String,String>> arrayList;
    int j=0;
    RecyclerView wallet_histroy;
    ArrayList<HashMap<String, String>> arrayList1 = new ArrayList<>();
    double aresult;

    public AllPlanAdapter(Context context, List<HashMap<String,String>> arrayList) {
        this.arrayList=arrayList;
        this.context=context;
    }

    @NonNull
    @Override
    public AllPlanAdapter.VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.dynamic_plan, viewGroup, false);
        AllPlanAdapter.VH viewHolder = new AllPlanAdapter.VH(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AllPlanAdapter.VH vh, int i) {
        vh.name_tv.setText(arrayList.get(i).get("Member_name"));
        vh.location_tv.setText(arrayList.get(i).get("Branch"));
        vh.account_tv.setText(arrayList.get(i).get("account_no"));
        vh.userid_tv.setText(arrayList.get(i).get("Member_Id"));
        vh.payable_to_tv.setText(arrayList.get(i).get("Plan_Name"));
        vh.m_date_tv.setText(arrayList.get(i).get("Purchase_date"));
        vh.deposite_tv.setText("\u20B9 "+arrayList.get(i).get("DepsitAMo"));
        vh.period_tv.setText(arrayList.get(i).get("PlanDuration"));
        vh.maturity_date_tv.setText(arrayList.get(i).get("MaturityDate"));
        vh.maturity_amount_tv.setText(arrayList.get(i).get("Maturity")+" \u20B9 ");

        vh.sr_tv.setText(arrayList.get(i).get("Member_Id"));
        vh.status_tv.setText(arrayList.get(i).get("MaturityPaymentStatus"));
        vh.transaction_tv.setText("Transaction History("+arrayList.get(i).get("TotalAmount")+"\u20B9 )");

        getHistory(arrayList.get(i).get("account_no"));


//        aresult=Double.parseDouble(arrayList.get(i).get("DepsitAMo"));


        vh.view_details_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (j == 0) {
                    vh.show_view_details_ll.setVisibility(View.VISIBLE);
                    vh.show_history_ll.setVisibility(View.GONE);
                    j++;
                } else if (j == 1) {
                    vh.show_view_details_ll.setVisibility(View.GONE);
                    vh.show_history_ll.setVisibility(View.GONE);
                    j = 0;
                }
            }});

        vh.maturity_amount_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (j == 0) {
                    vh.show_history_ll.setVisibility(View.VISIBLE);
                    j++;
                } else if (j == 1) {
                    vh.show_history_ll.setVisibility(View.GONE);
                    j = 0;
                }
            }});

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class VH extends RecyclerView.ViewHolder {
        TextView name_tv,location_tv,account_tv,userid_tv,payable_to_tv,m_date_tv,deposite_tv,
                period_tv,maturity_date_tv,maturity_amount_tv,status_tv,sr_tv;
        LinearLayout view_details_ll,show_view_details_ll,show_history_ll;
        TextView transaction_tv;



        public VH(@NonNull View itemView) {
            super(itemView);
            name_tv=itemView.findViewById(R.id.name_tv);
            sr_tv=itemView.findViewById(R.id.sr_tv);
            location_tv=itemView.findViewById(R.id.location_tv);
            account_tv=itemView.findViewById(R.id.account_tv);
            userid_tv=itemView.findViewById(R.id.userid_tv);
            status_tv=itemView.findViewById(R.id.status_tv);

            payable_to_tv=itemView.findViewById(R.id.payable_to_tv);
            m_date_tv=itemView.findViewById(R.id.m_date_tv);
            deposite_tv=itemView.findViewById(R.id.deposite_tv);

            period_tv=itemView.findViewById(R.id.period_tv);
            maturity_date_tv=itemView.findViewById(R.id.maturity_date_tv);
            maturity_amount_tv=itemView.findViewById(R.id.maturity_amount_tv);

            view_details_ll=itemView.findViewById(R.id.view_details_ll);
            show_view_details_ll=itemView.findViewById(R.id.show_view_details_ll);
            show_history_ll=itemView.findViewById(R.id.show_history_ll);

            wallet_histroy=itemView.findViewById(R.id.wallet_histroy);

            transaction_tv=itemView.findViewById(R.id.transaction_tv);

        }
    }

    public void getHistory(String accno) {
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Api_Urls.BaseURL + "FreshRenewalReport", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("REsponse_Data", response);
                progressDialog.dismiss();
                String jsonString = response;
                jsonString = jsonString.replace("<?xml version=\"1.0\" encoding=\"utf-8\"?>"," ");
                jsonString = jsonString.replace("<string xmlns=\"http://tempuri.org/\">"," ");
                jsonString = jsonString.replace("</string>"," ");
                try {
                    JSONObject jsonObject = new JSONObject(jsonString);
                    JSONArray jsonArrayr = jsonObject.getJSONArray("Response");
                    for (int i = 0; i < jsonArrayr.length(); i++) {
                        JSONObject jsonObject1 = jsonArrayr.getJSONObject(i);
                        HashMap<String, String> hm = new HashMap<>();
                        hm.put("MemberId", jsonObject1.getString("MemberId"));
                        hm.put("MemberName", jsonObject1.getString("MemberName"));
                        hm.put("AccountNo", jsonObject1.getString("AccountNo"));
                        hm.put("IntroducerName", jsonObject1.getString("IntroducerName"));
                        hm.put("IntroducerId", jsonObject1.getString("IntroducerId"));
                        hm.put("InstallmentDate", jsonObject1.getString("InstallmentDate"));
                        hm.put("PlanCode", jsonObject1.getString("PlanCode"));
                        hm.put("Fresh", jsonObject1.getString("Fresh"));
                        hm.put("Renewal", jsonObject1.getString("Renewal"));
                        hm.put("PayMode", jsonObject1.getString("PayMode"));
                        hm.put("Branch", jsonObject1.getString("Branch"));
                        arrayList1.add(hm);

//                        int alength= jsonArrayr.length();
//                        double total_res=aresult*alength;
//                        Log.d("vbbvcbcb","sd"+total_res);

//                        transaction_tv.setText("Transaction History("+total_res+"\u20B9)");


                    }
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 1);
                    AllPlanTransactionAdapter customerListAdapter = new AllPlanTransactionAdapter(context, arrayList1);
                    wallet_histroy.setLayoutManager(gridLayoutManager);
                    wallet_histroy.setAdapter(customerListAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"Something went wrong!",Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("AccountNo", accno);
                return params;

            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }



}


