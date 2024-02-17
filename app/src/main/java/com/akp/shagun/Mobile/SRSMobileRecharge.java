package com.akp.shagun.Mobile;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.akp.shagun.Api_Urls;
import com.akp.shagun.R;
import com.akp.shagun.rechargebills.ProviderList;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SRSMobileRecharge extends AppCompatActivity {

    TextView provoider_et;
    String getServicename,getProviderId;
    EditText amount_et,mob_et;
    Button btnSubmit;
    private SharedPreferences sharedPreferences;
    String UserId;
    String APIResponse;
    private AlertDialog alertDialog;

    ImageView ivBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_r_s_mobile_recharge);
        sharedPreferences = getSharedPreferences("login_preference", MODE_PRIVATE);
        UserId = sharedPreferences.getString("userid", "");
        getServicename=getIntent().getStringExtra("servicename");
        getProviderId=getIntent().getStringExtra("providerid");

//        Toast.makeText(getApplicationContext(),getProviderId,Toast.LENGTH_LONG).show();

        ivBack=findViewById(R.id.ivBack);


        btnSubmit=findViewById(R.id.btnSubmit);
        amount_et=findViewById(R.id.amount_et);
        mob_et=findViewById(R.id.mob_et);
        provoider_et=findViewById(R.id.provoider_et);

        if (getServicename == null){
            provoider_et.setText("Select Provider");
        }
        else {
            provoider_et.setText(getServicename);

        }


        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        provoider_et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), ProviderList.class);
                startActivity(intent);
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (provoider_et.getText().toString().equalsIgnoreCase("")){
                    provoider_et.setError("Fields can't be blank!");
                    provoider_et.requestFocus();
                }
                else if (mob_et.getText().toString().equalsIgnoreCase("")){
                    mob_et.setError("Fields can't be blank!");
                    mob_et.requestFocus();
                }
                else  if (amount_et.getText().toString().equalsIgnoreCase("")){
                    amount_et.setError("Fields can't be blank!");
                    amount_et.requestFocus();
                }
                else {
                    PayRechargeAPI();
                }
            }
        });





    }


    public void PayRechargeAPI() {
        final ProgressDialog progressDialog = new ProgressDialog(SRSMobileRecharge.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Api_Urls.BaseURL + "PostRecharge", new Response.Listener<String>() {
            //        StringRequest stringRequest = new StringRequest(Request.Method.POST, Api_Urls.Signature_BASE_URL + url, new  Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                String jsonString = response;
                jsonString = jsonString.replace("<?xml version=\"1.0\" encoding=\"utf-8\"?>", " ");
                jsonString = jsonString.replace("<string xmlns=\"http://tempuri.org/\">", " ");
                jsonString = jsonString.replace("</string>", " ");
                try {
                    JSONObject object = new JSONObject(jsonString);
                    APIResponse=object.getString("Response");

                   Toast.makeText(getApplicationContext(),object.getString("Response"),Toast.LENGTH_LONG).show();
                    showpopupwindow();


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(SRSMobileRecharge.this, "Something went wrong:-" + error, Toast.LENGTH_SHORT).show();
            }})
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("UserId",UserId);
                params.put("Mobile",mob_et.getText().toString());
                params.put("optr", getProviderId);
                params.put("circle",getServicename);
                params.put("Amount", amount_et.getText().toString());
                return params;
            } };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(SRSMobileRecharge.this);
        requestQueue.add(stringRequest);
    }


    private void showpopupwindow() {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = findViewById(android.R.id.content);
        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(SRSMobileRecharge.this).inflate(R.layout.recharge, viewGroup, false);
        Button ok = (Button) dialogView.findViewById(R.id.btnDialog);
        TextView txt_msg=dialogView.findViewById(R.id.txt_msg);
        txt_msg.setText("Response- "+APIResponse);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);
        //finally creating the alert dialog and displaying it
        alertDialog = builder.create();
        alertDialog.show();
    }
}