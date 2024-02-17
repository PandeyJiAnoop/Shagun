package com.akp.shagun.PlanDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
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

public class AllPlan extends AppCompatActivity {
    ImageView back_img;
    LinearLayout dynamic_ll;
    String Url = "GetProjectList";
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    RecyclerView wallet_histroy;
    TextView txt_nodata;
    String Id;
    SwipeRefreshLayout srl_refresh;
    private SharedPreferences sharedPreferences;
    String UserId;
    private String get_type;
//    LinearLayout view_details_ll,show_view_details_ll,show_history_ll,dynamic_ll;
//    TextView amount_tv;
//    int i=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_plan);
        sharedPreferences = getSharedPreferences("login_preference", MODE_PRIVATE);
        UserId = sharedPreferences.getString("userid", "");
        get_type=getIntent().getStringExtra("type");
        back_img=findViewById(R.id.back_img);
//        dynamic_ll=findViewById(R.id.dynamic_ll);

        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        wallet_histroy = findViewById(R.id.wallet_histroy);
        txt_nodata = findViewById(R.id.txt_nodata);
        srl_refresh = findViewById(R.id.srl_refresh);

        getHistory();
        srl_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (NetworkConnectionHelper.isOnline(AllPlan.this)) {
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
                    Toast.makeText(AllPlan.this, "Please check your internet connection! try again...", Toast.LENGTH_SHORT).show();
                }
            }
        });

      /*  for (int i=0;i<4;i++){
            GetProjectList();
        }*/
    }



    public void getHistory() {
        final ProgressDialog progressDialog = new ProgressDialog(AllPlan.this);
        progressDialog.setMessage("Loading");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Api_Urls.BaseURL + "GetAccountDetails", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                Log.d("REsponse_Data", response);
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
                        hm.put("Member_name", jsonObject1.getString("Member_name"));
                        hm.put("Purchase_date", jsonObject1.getString("Purchase_date"));
                        hm.put("Plan_code", jsonObject1.getString("Plan_code"));
                        hm.put("Plan_Name", jsonObject1.getString("Plan_Name"));
                        hm.put("PlanDuration", jsonObject1.getString("PlanDuration"));
                        hm.put("PlanType", jsonObject1.getString("PlanType"));
                        hm.put("DepsitAMo", jsonObject1.getString("DepsitAMo"));
                        hm.put("Maturity", jsonObject1.getString("Maturity"));
                        hm.put("MaturityDate", jsonObject1.getString("MaturityDate"));
                        hm.put("MaturityPaymentStatus", jsonObject1.getString("MaturityPaymentStatus"));
                        hm.put("MaturityPaidAmo", jsonObject1.getString("MaturityPaidAmo"));
                        hm.put("MaturityPaymentDate", jsonObject1.getString("MaturityPaymentDate"));
                        hm.put("TotalAmount", jsonObject1.getString("TotalAmount"));
                        arrayList.add(hm);

                    }
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(AllPlan.this, 1);
                    AllPlanAdapter customerListAdapter = new AllPlanAdapter(AllPlan.this, arrayList);
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
                params.put("MemberId", UserId);
                params.put("PlanType", get_type);
                return params;

            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue = Volley.newRequestQueue(AllPlan.this);
        requestQueue.add(stringRequest);
    }
}
      /*  findId();
           for (int i=0;i<1;i++){
            GetProjectList();
        }
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { finish(); }});
    }

    private void findId() {
        back_img=findViewById(R.id.back_img);
        dynamic_ll=findViewById(R.id.dynamic_ll);
    }

    private void GetProjectList() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View convertView = inflater.inflate(R.layout.dynamic_plan, null);
        dynamic_ll.addView(convertView);
        view_details_ll=convertView.findViewById(R.id.view_details_ll);
        amount_tv=convertView.findViewById(R.id.amount_tv);
        show_view_details_ll=convertView.findViewById(R.id.show_view_details_ll);
        show_history_ll=convertView.findViewById(R.id.show_history_ll);
        View view = new View(AllPlan.this);

        view_details_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i == 0) {
                    show_view_details_ll.setVisibility(View.VISIBLE);
                    show_history_ll.setVisibility(View.GONE);
                    i++;
                } else if (i == 1) {
                    show_view_details_ll.setVisibility(View.GONE);
                    show_history_ll.setVisibility(View.GONE);
                    i = 0;
                }
            }});

        amount_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i == 0) {
                    show_history_ll.setVisibility(View.VISIBLE);
                    i++;
                } else if (i == 1) {
                    show_history_ll.setVisibility(View.GONE);
                    i = 0;
                }
            }});

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        LinearLayout.LayoutParams params_view = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 5);
        view.setLayoutParams(params_view);
        dynamic_ll.addView(view);
    }


}*/