package com.akp.shagun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.akp.shagun.AssociateDashboard.AssocaiteHome;
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

public class LoginScreen extends AppCompatActivity {
    AppCompatButton login;
    private SharedPreferences login_preference;
    private SharedPreferences.Editor login_editor;
    EditText reg_name_et,reg_password_et;
    LinearLayout main_ll;
    TextView forget_pass_tv;
    private PopupWindow popupWindow1,popupWindow,popupWindow2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        main_ll=findViewById(R.id.main_ll);
        login=findViewById(R.id.login);
        reg_name_et=findViewById(R.id.reg_name_et);
        reg_password_et=findViewById(R.id.reg_password_et);
        forget_pass_tv=findViewById(R.id.forget_pass_tv);
        forget_pass_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forget_password();
            }
        });

  login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (reg_name_et.getText().toString().equalsIgnoreCase("")){
                    reg_name_et.setError("Fields can't be blank!");
                    reg_name_et.requestFocus(); }
                else if (reg_password_et.getText().toString().equalsIgnoreCase("")){
                    reg_password_et.setError("Fields can't be blank!");
                    reg_password_et.requestFocus(); }
                else {
                    sendOtp(); } }}); }

    public void sendOtp() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Api_Urls.BaseURL + "GetLogin", new Response.Listener<String>() {
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
                            Toast.makeText(LoginScreen.this, "UserId or Password is wrong", Toast.LENGTH_SHORT).show();
                        } else {
                            String username=jsonobject.getString("username");
                            String paasword=jsonobject.getString("paasword");
                            String UserId=jsonobject.getString("UserId");
                            String Role=jsonobject.getString("Role");
                            login_preference = getSharedPreferences("login_preference", MODE_PRIVATE);
                            login_editor = login_preference.edit();
                            login_editor.putString("userid",jsonobject.getString("UserId"));
                            login_editor.putString("Role",jsonobject.getString("Role"));
                            login_editor.putString("name",jsonobject.getString("username"));
                            login_editor.commit();
                            if (Role.equalsIgnoreCase("Associate")){
                                Toast.makeText(LoginScreen.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginScreen.this, AssocaiteHome.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(LoginScreen.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginScreen.this, MainActivity.class);
                                startActivity(intent);
                            }} }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(LoginScreen.this, "Something went wrong:-" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("Username", reg_name_et.getText().toString());
                params.put("Password",reg_password_et.getText().toString());
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(LoginScreen.this);
        requestQueue.add(stringRequest);
    }

    public void forget_password(){
        LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customView = layoutInflater.inflate(R.layout.forget_password,null);
        ImageView Goback = (ImageView) customView.findViewById(R.id.Goback);
        EditText email_et = (EditText) customView.findViewById(R.id.email_et);
        AppCompatButton continue_btn = (AppCompatButton) customView.findViewById(R.id.continue_btn);
        //instantiate popup window
        popupWindow = new PopupWindow(customView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //display the popup window
        popupWindow.showAtLocation(main_ll, Gravity.BOTTOM, 0, 0);
        popupWindow.setFocusable(true);
        // Settings disappear when you click somewhere else
        popupWindow.setOutsideTouchable(true);
        popupWindow.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
        popupWindow.update();
        Goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        continue_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FogetPassword(email_et.getText().toString());
            }});
    }

    public void forget_password_otp(){
        LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customView = layoutInflater.inflate(R.layout.forget_pass_otp,null);
        ImageView Goback1 = (ImageView) customView.findViewById(R.id.Goback1);
        AppCompatButton continue_btn1 = (AppCompatButton) customView.findViewById(R.id.continue_btn1);
        //instantiate popup window
        popupWindow1 = new PopupWindow(customView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //display the popup window
        popupWindow1.showAtLocation(main_ll, Gravity.BOTTOM, 0, 0);
        popupWindow1.setFocusable(true);
        // Settings disappear when you click somewhere else
        popupWindow1.setOutsideTouchable(true);
        popupWindow1.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
        Goback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow1.dismiss();
            }
        });
        continue_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forget_password_reset();
                popupWindow1.dismiss();
            }});
    }

    public void forget_password_reset(){
        LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customView = layoutInflater.inflate(R.layout.forget_pass_reset,null);
        ImageView Goback2 = (ImageView) customView.findViewById(R.id.Goback2);
        AppCompatButton continue_btn2 = (AppCompatButton) customView.findViewById(R.id.continue_btn2);
        //instantiate popup window
        popupWindow2 = new PopupWindow(customView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //display the popup window
        popupWindow2.showAtLocation(main_ll, Gravity.BOTTOM, 0, 0);
        popupWindow2.setFocusable(true);
        // Settings disappear when you click somewhere else
        popupWindow2.setOutsideTouchable(true);
        popupWindow2.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
        Goback2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow2.dismiss();
            }
        });
        continue_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow2.dismiss();
            }
        });
    }

    public void FogetPassword(String email) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Api_Urls.BaseURL + "ForgetPassword", new Response.Listener<String>() {
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
                        if (object.getString("status").equalsIgnoreCase("false")) {
                            Toast.makeText(LoginScreen.this, "Email Id not matched!", Toast.LENGTH_SHORT).show();
                        } else {
                            popupWindow.dismiss();
                            Toast.makeText(LoginScreen.this, "Password sent your Registered Email id", Toast.LENGTH_SHORT).show();
                        }
                } catch (JSONException e) {
                    e.printStackTrace();
                }}
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(LoginScreen.this, "Something went wrong:-" + error, Toast.LENGTH_SHORT).show();
            }}) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("EmailID", email);
                return params;
            }};
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(LoginScreen.this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}