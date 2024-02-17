package com.akp.shagun.AssociateDashboard;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

public class AssociateProfile extends AppCompatActivity {
    private ImageView back_img;
    Button change_pass_ll;
    TextView name_tv,branch_tv;
    EditText email_tv,mobile_tv,link_tv,bank_tv,bank_tv1,bank_tv2,bank_tv3,bank_tv4;
    private SharedPreferences sharedPreferences;
    String UserId;
    Button update_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_associate_profile);
        sharedPreferences = getSharedPreferences("login_preference", MODE_PRIVATE);
        UserId = sharedPreferences.getString("userid", "");
//        Toast.makeText(getApplicationContext(),UserId,Toast.LENGTH_LONG).show();
        Log.d("resid",UserId);


        back_img=findViewById(R.id.back_img);
        change_pass_ll=findViewById(R.id.change_pass_ll);
        update_button=findViewById(R.id.update_button);

        name_tv=findViewById(R.id.name_tv);
        branch_tv=findViewById(R.id.branch_tv);

        email_tv=findViewById(R.id.email_tv);
        mobile_tv=findViewById(R.id.mobile_tv);
        link_tv=findViewById(R.id.link_tv);
        bank_tv=findViewById(R.id.bank_tv);
        bank_tv1=findViewById(R.id.bank_tv1);
        bank_tv2=findViewById(R.id.bank_tv2);
        bank_tv3=findViewById(R.id.bank_tv3);
        bank_tv4=findViewById(R.id.bank_tv4);

        ProfileAPI();

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),"Coming soon",Toast.LENGTH_LONG).show();
                UpdateProfileAPI();

            }
        });


        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        change_pass_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), AssociateChangePassword.class);
                startActivity(intent);
            }
        });

    }


    public void ProfileAPI() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Api_Urls.BaseURL+"GetProfileDetails", new Response.Listener<String>() {
            //        StringRequest stringRequest = new StringRequest(Request.Method.POST, Api_Urls.Signature_BASE_URL + url, new  Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
                String jsonString = response;
                jsonString = jsonString.replace("<?xml version=\"1.0\" encoding=\"utf-8\"?>", " ");
                jsonString = jsonString.replace("<string xmlns=\"http://tempuri.org/\">", " ");
                jsonString = jsonString.replace("</string>", " ");
                try {
                    JSONObject object = new JSONObject(jsonString);
                    JSONArray Jarray = object.getJSONArray("Response");
                    for (int i = 0; i < Jarray.length(); i++) {
                        JSONObject jsonobject = Jarray.getJSONObject(i);
                        if (object.getString("status").equalsIgnoreCase("false")) {
                            Toast.makeText(AssociateProfile.this, "UserId not Found!", Toast.LENGTH_SHORT).show();
                        } else {
                            String branch=jsonobject.getString("Branch");
                            String position=jsonobject.getString("position");
                            String fo_id=jsonobject.getString("FO_ID");
                            String name=jsonobject.getString("Name");
                            String mobileNo=jsonobject.getString("MobileNo");
                            String introId=jsonobject.getString("IntroId");
                            String address=jsonobject.getString("address");
                            String accountNo=jsonobject.getString("AccountNo");
                            String accountHolder=jsonobject.getString("AccountHolder");
                            String bankName=jsonobject.getString("BankName");
                            String bankBranch=jsonobject.getString("BankBranch");
                            String ifscCode=jsonobject.getString("IfscCode");
                            name_tv.setText(jsonobject.getString("Name")+"("+jsonobject.getString("IntroId")+")");
                            branch_tv.setText(jsonobject.getString("Branch")+"("+jsonobject.getString("position")+")");


                            email_tv.setText(name);
                            mobile_tv.setText(mobileNo);
                            link_tv.setText(address);
                            bank_tv.setText(accountNo);
                            bank_tv1.setText(accountHolder);
                            bank_tv2.setText(bankName);
                            bank_tv3.setText(bankBranch);
                            bank_tv4.setText(ifscCode);


                        } }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(AssociateProfile.this, "Something went wrong:-" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("Fo_ID", UserId);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(AssociateProfile.this);
        requestQueue.add(stringRequest);
    }



    public void UpdateProfileAPI() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Api_Urls.BaseURL+"UpdateprofileAssociate", new Response.Listener<String>() {
            //        StringRequest stringRequest = new StringRequest(Request.Method.POST, Api_Urls.Signature_BASE_URL + url, new  Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
                String jsonString = response;
                jsonString = jsonString.replace("<?xml version=\"1.0\" encoding=\"utf-8\"?>", " ");
                jsonString = jsonString.replace("<string xmlns=\"http://tempuri.org/\">", " ");
                jsonString = jsonString.replace("</string>", " ");
                try {
                    JSONObject object = new JSONObject(jsonString);
                        if (object.getString("status").equalsIgnoreCase("false")) {
                            Toast.makeText(AssociateProfile.this, "UserId not Found!", Toast.LENGTH_SHORT).show();
                        } else {
                            finish();
                            overridePendingTransition(0, 0);
                            startActivity(getIntent());
                            overridePendingTransition(0, 0);
                        }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(AssociateProfile.this, "Something went wrong:-" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("FO_ID", UserId);
                params.put("Name", email_tv.getText().toString());
                params.put("Mobile", mobile_tv.getText().toString());
                params.put("Address", link_tv.getText().toString());
                params.put("AccountNo", bank_tv.getText().toString());
                params.put("AccountHolder", bank_tv1.getText().toString());
                params.put("BankName", bank_tv2.getText().toString());
                params.put("BankBranch", bank_tv3.getText().toString());
                params.put("IfscCode", bank_tv4.getText().toString());
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(AssociateProfile.this);
        requestQueue.add(stringRequest);
    }


}