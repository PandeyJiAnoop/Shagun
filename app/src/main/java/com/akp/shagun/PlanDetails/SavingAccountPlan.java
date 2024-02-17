package com.akp.shagun.PlanDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.akp.shagun.Api_Urls;
import com.akp.shagun.NetworkConnectionHelper;
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
import java.util.Map;

public class SavingAccountPlan extends AppCompatActivity {
    private ImageView back_img;
    private SharedPreferences sharedPreferences;
    String UserId;
    private SwipeRefreshLayout srl_refresh;
    RecyclerView wallet_histroy;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    String getAccount_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saving_account_plan);
        wallet_histroy=findViewById(R.id.wallet_histroy);

        sharedPreferences = getSharedPreferences("login_preference", MODE_PRIVATE);
        UserId = sharedPreferences.getString("userid", "");
        getAccount_no=getIntent().getStringExtra("acc_no");
        Log.d("sdjjhag","sakdhgajsdgaf"+getAccount_no);

        back_img=findViewById(R.id.back_img);
        srl_refresh = findViewById(R.id.srl_refresh);

        if (getAccount_no == null){
        }
        else {
            getHistory(getAccount_no);
        }

        srl_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (NetworkConnectionHelper.isOnline(SavingAccountPlan.this)) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                            overridePendingTransition(0, 0);
                            startActivity(getIntent());
                            overridePendingTransition(0, 0);
                            srl_refresh.setRefreshing(false);
                        }
                    }, 2000);
                } else {
                    Toast.makeText(SavingAccountPlan.this, "Please check your internet connection! try again...", Toast.LENGTH_SHORT).show();
                }
            }
        });
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }


    public void getHistory(String account_no) {
        final ProgressDialog progressDialog = new ProgressDialog(SavingAccountPlan.this);
        progressDialog.setMessage("Loading");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Api_Urls.BaseURL+"GetVirtualSavingTran", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("fdfdfdfd",response);
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

                        arrayList.add(hm);
                    }
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(SavingAccountPlan.this, 1);
                    SavingAccountPlanAdapter customerListAdapter = new SavingAccountPlanAdapter(SavingAccountPlan.this, arrayList);
                    wallet_histroy.setLayoutManager(gridLayoutManager);
                    wallet_histroy.setAdapter(customerListAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Something went wrong!",Toast.LENGTH_LONG).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("AccountNo", account_no);
                params.put("PlanType", "vr");
                return params;

            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue = Volley.newRequestQueue(SavingAccountPlan.this);
        requestQueue.add(stringRequest);
    }

}