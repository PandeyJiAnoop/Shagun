package com.akp.shagun.AssociateDashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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

import java.util.HashMap;
import java.util.Map;

public class PayRenewal extends AppCompatActivity {

    AppCompatButton submit_btn,ren_submit_btn;
    EditText mob_et,dip_et;
    TextView tv,tv1,tv2,tv3,tv4,tv5,tv6,tv9,tv7,tv8,tv10;
    CardView cv1;
RelativeLayout header;
    private SharedPreferences sharedPreferences;
    String UserId;
    String Getaccount_no,GetMember_Id,GetTotalAmt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_renewal);
        sharedPreferences = getSharedPreferences("login_preference", MODE_PRIVATE);
        UserId = sharedPreferences.getString("userid", "");
        mob_et=findViewById(R.id.mob_et);
        header=findViewById(R.id.header);
        submit_btn=findViewById(R.id.submit_btn);
        tv=findViewById(R.id.tv);
        tv1=findViewById(R.id.tv1);
        tv2=findViewById(R.id.tv2);
        tv3=findViewById(R.id.tv3);
        tv4=findViewById(R.id.tv4);
        tv5=findViewById(R.id.tv5);
        tv6=findViewById(R.id.tv6);
        tv7=findViewById(R.id.tv7);
        tv8=findViewById(R.id.tv8);
        tv9=findViewById(R.id.tv9);
        tv10=findViewById(R.id.tv10);
        ren_submit_btn=findViewById(R.id.ren_submit_btn);
        dip_et=findViewById(R.id.dip_et);
        cv1=findViewById(R.id.cv1);
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mob_et.getText().toString().equalsIgnoreCase("")){
                    mob_et.setError("Fields can't be blank!");
                    mob_et.requestFocus();
                }
                else {
                    GetDetails();
                }

            }
        });
        ren_submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dip_et.getText().toString().equalsIgnoreCase("")){
                    dip_et.setError("Fields can't be blank!");
                    dip_et.requestFocus();
                }
                else {
                    SubmitPayRenewal();
                }

            }
        });
        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              finish();
            }});

    }

    public void SubmitPayRenewal() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Api_Urls.BaseURL+"PayRenewal", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                String jsonString = response;
                jsonString = jsonString.replace("<?xml version=\"1.0\" encoding=\"utf-8\"?>", " ");
                jsonString = jsonString.replace("<string xmlns=\"http://tempuri.org/\">", " ");
                jsonString = jsonString.replace("</string>", " ");
                Log.d("GetAccount_Details",response);
                try {
                    JSONObject object = new JSONObject(jsonString);
                    if (object.getString("status").equalsIgnoreCase("false")){
                        Toast.makeText(getApplicationContext(), object.getString("Response"), Toast.LENGTH_SHORT).show();
                    }
                    else {
                       Toast.makeText(getApplicationContext(),"Paid Successfully",Toast.LENGTH_LONG).show();
                       finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //  Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(PayRenewal.this, "Something went wrong:-" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("UserId", UserId);
                params.put("Amount", dip_et.getText().toString());
                params.put("AccountNo", Getaccount_no);
                params.put("MemberId", GetMember_Id);
                Log.d("respay", String.valueOf(params));

                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(PayRenewal.this);
        requestQueue.add(stringRequest);
    }

    public void GetDetails() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Api_Urls.BaseURL+"GetAccount_Details", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                String jsonString = response;
                jsonString = jsonString.replace("<?xml version=\"1.0\" encoding=\"utf-8\"?>", " ");
                jsonString = jsonString.replace("<string xmlns=\"http://tempuri.org/\">", " ");
                jsonString = jsonString.replace("</string>", " ");
                Log.d("GetAccount_Details",response);
                try {
                    JSONObject object = new JSONObject(jsonString);
                    if (object.getString("status").equalsIgnoreCase("false")){
                        cv1.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), object.getString("Msg"), Toast.LENGTH_SHORT).show();
                    }
                    else {
                        cv1.setVisibility(View.VISIBLE);
                        JSONArray jsonArray = object.getJSONArray("Response");
                        Log.d("hh",""+jsonArray);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            Getaccount_no=jsonObject1.getString("account_no");
                            GetMember_Id=jsonObject1.getString("Member_Id");
                            GetTotalAmt=jsonObject1.getString("TotalAmt");
                            tv.setText(jsonObject1.getString("Branch"));
                            tv1.setText(jsonObject1.getString("Member_Id"));
                            tv2.setText(jsonObject1.getString("account_no"));
                            tv3.setText(jsonObject1.getString("Plan_code"));
                            tv4.setText(jsonObject1.getString("IntroducerId"));
                            tv5.setText(jsonObject1.getString("Member_name"));
                            tv6.setText(jsonObject1.getString("mobile"));
                            tv7.setText(jsonObject1.getString("InstallmentAmt"));
                            tv8.setText(jsonObject1.getString("Purchase_date"));
                            tv9.setText(jsonObject1.getString("TotalAmt"));
                            tv10.setText(jsonObject1.getString("DuesBal"));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //  Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(PayRenewal.this, "Something went wrong:-" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("AccountNo", mob_et.getText().toString());
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(PayRenewal.this);
        requestQueue.add(stringRequest);
    }

}