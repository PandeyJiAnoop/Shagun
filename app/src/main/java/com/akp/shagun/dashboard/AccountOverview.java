package com.akp.shagun.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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

public class AccountOverview extends AppCompatActivity {
    ImageView back_img;
    EditText name_et,email_et,father_et,mobile_et,add_et,city_et,state_et,pancard_et,adhar_et,acc_no,et_bankname,et_acctype,ifsccode_et,n_age_et,
    n_name_et,n_rel_et,branch_et;
    TextView txt_userName,txt_userId;
    private SharedPreferences sharedPreferences;
    String UserId,Username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_overview);

        sharedPreferences = getSharedPreferences("login_preference", MODE_PRIVATE);
        UserId = sharedPreferences.getString("userid", "");
        Username = sharedPreferences.getString("name", "");

        back_img=findViewById(R.id.back_img);

        txt_userName=findViewById(R.id.txt_userName);
        txt_userId=findViewById(R.id.txt_userId);

        name_et=findViewById(R.id.name_et);
        email_et=findViewById(R.id.email_et);
        father_et=findViewById(R.id.father_et);
        mobile_et=findViewById(R.id.mobile_et);
        add_et=findViewById(R.id.add_et);
        city_et=findViewById(R.id.city_et);
        state_et=findViewById(R.id.state_et);
        pancard_et=findViewById(R.id.pancard_et);
        adhar_et=findViewById(R.id.adhar_et);
        acc_no=findViewById(R.id.acc_no);
        et_bankname=findViewById(R.id.et_bankname);
        et_acctype=findViewById(R.id.et_acctype);
        ifsccode_et=findViewById(R.id.ifsccode_et);
        n_name_et=findViewById(R.id.n_name_et);
        n_age_et=findViewById(R.id.n_age_et);
        n_rel_et=findViewById(R.id.n_rel_et);
        branch_et=findViewById(R.id.branch_et);


        getdeatils();

        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void getdeatils() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Api_Urls.BaseURL + "ViewProfile", new Response.Listener<String>() {
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
                    JSONArray Jarray = object.getJSONArray("Response");
                    for (int i = 0; i < Jarray.length(); i++) {
                        JSONObject jsonobject = Jarray.getJSONObject(i);
                        if (jsonobject.getString("status").equalsIgnoreCase("false")) {
                            Toast.makeText(AccountOverview.this, "UserId or Password is wrong", Toast.LENGTH_SHORT).show();
                        } else {
                            String branch=jsonobject.getString("Branch");
                            String memberId=jsonobject.getString("MemberId");
                            String memberName=jsonobject.getString("MemberName");
                            String fatherName=jsonobject.getString("FatherName");
                            String registraionDate=jsonobject.getString("RegistraionDate");
                            String mobileNo=jsonobject.getString("MobileNo");
                            String dob=jsonobject.getString("dob");
                            String age=jsonobject.getString("age");
                            String category=jsonobject.getString("Category");
                            String gender=jsonobject.getString("Gender");
                            String identityProof=jsonobject.getString("IdentityProof");
                            String identityNo=jsonobject.getString("IdentityNo");
                            String panno=jsonobject.getString("Panno");
                            String relation=jsonobject.getString("Relation");
                            String nomineename=jsonobject.getString("Nomineename");
                            String nomineeage=jsonobject.getString("Nomineeage");

                            String address=jsonobject.getString("Address");
                            String regfees=jsonobject.getString("Regfees");
                            String applicationNo=jsonobject.getString("ApplicationNo");

                            branch_et.setText(branch);
                            name_et.setText(memberName);
                            father_et.setText(fatherName);
                            mobile_et.setText(mobileNo);
                            add_et.setText(address);
                            city_et.setText(address);
                            state_et.setText(address);
                            pancard_et.setText(panno);
                            adhar_et.setText(identityNo);

                            n_name_et.setText(nomineename);
                            n_age_et.setText(nomineeage);
                            n_rel_et.setText(relation);
                            txt_userName.setText(memberName);
                            txt_userId.setText(memberId);



                        } }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(AccountOverview.this, "Something went wrong:-" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("MemberId", UserId);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(AccountOverview.this);
        requestQueue.add(stringRequest);
    }

}