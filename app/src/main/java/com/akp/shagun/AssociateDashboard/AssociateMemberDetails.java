package com.akp.shagun.AssociateDashboard;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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

public class AssociateMemberDetails extends AppCompatActivity {
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
    String member_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_associate_member_details);
        sharedPreferences = getSharedPreferences("login_preference", MODE_PRIVATE);
        UserId = sharedPreferences.getString("userid", "");
        member_id=getIntent().getStringExtra("memberid");
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
                if (NetworkConnectionHelper.isOnline(AssociateMemberDetails.this)) {
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
                    Toast.makeText(AssociateMemberDetails.this, "Please check your internet connection! try again...", Toast.LENGTH_SHORT).show();
                }
            }
        });

      /*  for (int i=0;i<4;i++){
            GetProjectList();
        }*/
    }



    public void getHistory() {
        final ProgressDialog progressDialog = new ProgressDialog(AssociateMemberDetails.this);
        progressDialog.setMessage("Loading");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Api_Urls.BaseURL + "MemberPlanDetails", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Log.d("REsponse_Data", Constants.getSavedPreferences(getApplicationContext(), LOGINKEY, ""));
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
                        hm.put("Member_Id", jsonObject1.getString("Member_Id"));
                        hm.put("Member_name", jsonObject1.getString("Member_name"));
                        hm.put("LoanRequired", jsonObject1.getString("LoanRequired"));
                        hm.put("account_no", jsonObject1.getString("account_no"));
                        hm.put("PlanType", jsonObject1.getString("PlanType"));
                        hm.put("DepsitAMo", jsonObject1.getString("DepsitAMo"));
                        hm.put("Purchase_date", jsonObject1.getString("Purchase_date"));
                        hm.put("Maturity", jsonObject1.getString("Maturity"));
                        hm.put("MaturityDate", jsonObject1.getString("MaturityDate"));
                        arrayList.add(hm);
                    }
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(AssociateMemberDetails.this, 1);
                    AssociateMemberDetailsAdapter customerListAdapter = new AssociateMemberDetailsAdapter(AssociateMemberDetails.this, arrayList);
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
                params.put("Member_Id", member_id);
                return params;

            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue = Volley.newRequestQueue(AssociateMemberDetails.this);
        requestQueue.add(stringRequest);
    }
}