package com.akp.shagun.PlanDetails;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VirtualAccountPlanAdapter extends RecyclerView.Adapter<VirtualAccountPlanAdapter.VH> {
    Context context;
    List<HashMap<String,String>> arrayList;
    int j=0;
    RecyclerView wallet_histroy;
    ArrayList<HashMap<String, String>> arrayList1 = new ArrayList<>();
    AlertDialog alertDialog;
    private EditText ifsc_et,accoount_no_et,name_et,mobile_et,amount_et;
    String getName,getId,getAccountNo;


    String[] logintype = new String[]{"IMPS", "NEFT", "RTGS"};
    String selectedName="";
    Spinner tvSelectType;



    public VirtualAccountPlanAdapter(Context context, List<HashMap<String,String>> arrayList) {
        this.arrayList=arrayList;
        this.context=context;
    }

    @NonNull
    @Override
    public VirtualAccountPlanAdapter.VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.dynamic_virtual_plan, viewGroup, false);
        VirtualAccountPlanAdapter.VH viewHolder = new VirtualAccountPlanAdapter.VH(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VirtualAccountPlanAdapter.VH vh, int i) {
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
//        vh.status_tv.setText("Status:- "+arrayList.get(i).get("MaturityPaymentStatus"));

        vh.bank_tv.setText(arrayList.get(i).get("bankname"));
        vh.ifsc_tv.setText(arrayList.get(i).get("IfscCode"));
        vh.benf_tv.setText(arrayList.get(i).get("BeneficiaryName"));

        vh.m_deposit_tv.setText(arrayList.get(i).get("deposit")+" \u20B9 ");
        vh.m_withdraw_tv.setText(arrayList.get(i).get("withdrawal")+" \u20B9 ");
        vh.m_balance_tv.setText(arrayList.get(i).get("balance")+" \u20B9 ");
        vh.withdraw_amount_tv.setText(arrayList.get(i).get("balance")+" \u20B9 ");

        getHistory(arrayList.get(i).get("account_no"));

        getName=arrayList.get(i).get("Member_name");
        getId=arrayList.get(i).get("Member_Id");
        getAccountNo=arrayList.get(i).get("account_no");

        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,SavingAccountPlan.class);
                intent.putExtra("acc_no",arrayList.get(i).get("account_no"));
                context.startActivity(intent);
            }
        });

        vh.view_details_ll.setOnClickListener(new View.OnClickListener() {
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

        vh.withdraw_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //before inflating the custom alert dialog layout, we will get the current activity viewgroup
                ViewGroup viewGroup = vh.itemView.findViewById(android.R.id.content);
                //then we will inflate the custom alert dialog xml that we created
                View dialogView = LayoutInflater.from(context).inflate(R.layout.withdrawpopup, viewGroup, false);
                AppCompatButton Submit_btn = (AppCompatButton) dialogView.findViewById(R.id.Submit_btn);
                amount_et=(EditText)dialogView.findViewById(R.id.amount_et);
                ifsc_et=dialogView.findViewById(R.id.ifsc_et);
                accoount_no_et=dialogView.findViewById(R.id.accoount_no_et);
                name_et=dialogView.findViewById(R.id.name_et);
                mobile_et=dialogView.findViewById(R.id.mobile_et);

                tvSelectType=dialogView.findViewById(R.id.tvSelectType);
                final List<String> plantsList = new ArrayList<>(Arrays.asList(logintype));
                final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(context, R.layout.support_simple_spinner_dropdown_item, plantsList);
                spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_value);
                tvSelectType.setAdapter(spinnerArrayAdapter);

                tvSelectType.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        TextView spinnerText = (TextView) tvSelectType.getChildAt(0);
                        spinnerText.setTextColor(Color.BLACK);
//                        Toast.makeText(context,tvSelectType.getSelectedItem().toString(),Toast.LENGTH_LONG).show();
                        Log.d("dsds","sdsds"+tvSelectType.getSelectedItem().toString());
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                } );






                Submit_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String m_szAmount = amount_et.getText().toString();
                        if(m_szAmount.replace(" ","").length() > 0) {
                            int inputAmount = Integer.valueOf(m_szAmount);
                            if (9 >= inputAmount ){
                                amount_et.setError("Please enter the amount between Rs. 10 and Rs. 100000");
                                amount_et.requestFocus();
                            }else{
                                //                ProfitWithdrawService("ecaea59e-ffe2-4a6a-88c5-68f4b40f1364",ifsc_et.getText().toString(),name_et.getText().toString(),accoount_no_et.getText().toString(), "001",rupee_et.getText().toString(),mobile_et.getText().toString());
                                final ProgressDialog progressDialog = new ProgressDialog(context);
                                progressDialog.setMessage("Loading...");
                                progressDialog.show();
                                StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://shagunmicrofinance.com/AccountApi.asmx/PayAmount", new  Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
//                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                                        progressDialog.dismiss();
                                        Intent intent=new Intent(context,VirtualAccountPlan.class);
                                        context.startActivity(intent);
                                        alertDialog.dismiss();
                                        String jsonString = response;
                                        try {
                                            JSONObject jsonObject = new JSONObject(jsonString);
                                            Log.v("withdrawreponse", String.valueOf(jsonObject));
                                            if(jsonObject.getString("Status").equalsIgnoreCase("true")){
                                                Toast.makeText(context,jsonObject.getString("Status")+jsonObject.getString("Msg"),Toast.LENGTH_LONG).show();
                                                Toast.makeText(context,"Success",Toast.LENGTH_LONG).show();
                                                Intent intent1=new Intent(context,VirtualAccountPlan.class);
                                                context.startActivity(intent1);
                                                alertDialog.dismiss();
                                            }
                                            else {
                                                Toast.makeText(context,jsonObject.getString("Msg"),Toast.LENGTH_LONG).show();
                                            }
                                        }
                                        catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        progressDialog.dismiss();
                                        Toast.makeText(context, "Internet connection is slow Or no internet connection", Toast.LENGTH_SHORT).show();
                                    }
                                }) {
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        HashMap<String, String> params = new HashMap<>();
                                        params.put("MemberID",getId);
                                        params.put("AmountPay",amount_et.getText().toString());
                                        params.put("Cifsc",ifsc_et.getText().toString());
                                        params.put("memName",getName);
                                        params.put("Acnumber",accoount_no_et.getText().toString());
                                        params.put("CustomerMobileNo",mobile_et.getText().toString());
                                        params.put("AccNo", getAccountNo);
                                        params.put("Mode", tvSelectType.getSelectedItem().toString());
                                        return params;
                                    }
                                };
                                stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                                RequestQueue requestQueue = Volley.newRequestQueue(context);
                                requestQueue.add(stringRequest);
                                alertDialog.dismiss();
                            }
                        }





                    }
                });
                //Now we need an AlertDialog.Builder object
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                //setting the view of the builder to our custom view that we already inflated
                builder.setView(dialogView);
                //finally creating the alert dialog and displaying it
                alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class VH extends RecyclerView.ViewHolder {
        TextView name_tv,location_tv,account_tv,userid_tv,payable_to_tv,m_date_tv,deposite_tv,
                period_tv,maturity_date_tv,maturity_amount_tv,ifsc_tv,bank_tv,benf_tv,m_deposit_tv,m_withdraw_tv,m_balance_tv,withdraw_amount_tv;
        LinearLayout view_details_ll,show_view_details_ll,show_history_ll;
        RelativeLayout withdraw_ll;



        public VH(@NonNull View itemView) {
            super(itemView);
            name_tv=itemView.findViewById(R.id.name_tv);
            withdraw_ll=itemView.findViewById(R.id.withdraw_ll);

            location_tv=itemView.findViewById(R.id.location_tv);
            account_tv=itemView.findViewById(R.id.account_tv);
            userid_tv=itemView.findViewById(R.id.userid_tv);
//            status_tv=itemView.findViewById(R.id.status_tv);

            payable_to_tv=itemView.findViewById(R.id.payable_to_tv);
            m_date_tv=itemView.findViewById(R.id.m_date_tv);
            deposite_tv=itemView.findViewById(R.id.deposite_tv);

            bank_tv=itemView.findViewById(R.id.bank_tv);
            ifsc_tv=itemView.findViewById(R.id.ifsc_tv);
            benf_tv=itemView.findViewById(R.id.benf_tv);
            period_tv=itemView.findViewById(R.id.period_tv);
            maturity_date_tv=itemView.findViewById(R.id.maturity_date_tv);
            maturity_amount_tv=itemView.findViewById(R.id.maturity_amount_tv);

            m_deposit_tv=itemView.findViewById(R.id.m_deposit_tv);
            m_withdraw_tv=itemView.findViewById(R.id.m_withdraw_tv);
            m_balance_tv=itemView.findViewById(R.id.m_balance_tv);
            withdraw_amount_tv=itemView.findViewById(R.id.withdraw_amount_tv);

            view_details_ll=itemView.findViewById(R.id.view_details_ll);
            show_view_details_ll=itemView.findViewById(R.id.show_view_details_ll);
            show_history_ll=itemView.findViewById(R.id.show_history_ll);

            wallet_histroy=itemView.findViewById(R.id.wallet_histroy);



        }
    }

    public void getHistory(String accno) {
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Api_Urls.BaseURL + "GetVirtualSavingTran", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("fddsfff", response);
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
                        hm.put("Branch", jsonObject1.getString("Branch"));
                        hm.put("Member_Id", jsonObject1.getString("Member_Id"));
                        hm.put("account_no", jsonObject1.getString("account_no"));
                        hm.put("Particulars", jsonObject1.getString("Particulars"));
                        hm.put("Transaction_Date", jsonObject1.getString("Transaction_Date"));
                        hm.put("Withdrawl_AMT", jsonObject1.getString("Withdrawl_AMT"));
                        hm.put("Diposited_AMT", jsonObject1.getString("Diposited_AMT"));
                        hm.put("actualrecieptno", jsonObject1.getString("actualrecieptno"));
                        hm.put("paymode", jsonObject1.getString("paymode"));
                        hm.put("chqueno", jsonObject1.getString("chqueno"));
                        hm.put("Introducer_ID", jsonObject1.getString("Introducer_ID"));
                        arrayList1.add(hm);
                    }
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 1);
                    VirtualPlanTransactionAdapter customerListAdapter = new VirtualPlanTransactionAdapter(context, arrayList1);
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
                params.put("PlanType", "vir");
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



