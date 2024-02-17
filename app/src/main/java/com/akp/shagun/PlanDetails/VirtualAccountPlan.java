package com.akp.shagun.PlanDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
import java.util.Random;

public class VirtualAccountPlan extends AppCompatActivity {
    private ImageView back_img;
    RelativeLayout deposite_ll,withdraw_ll;
    private SharedPreferences sharedPreferences;
    String UserId,getusername;
    private SwipeRefreshLayout srl_refresh;
    RecyclerView wallet_histroy;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    TextView deposite_amount_tv,withdraw_amount_tv;
    private AlertDialog alertDialog;
    TelephonyManager telephonyManager;

    int number;
    EditText ifsc_et,accoount_no_et,name_et,mobile_et;
    ImageView norecord_img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_virtual_account_plan);
        sharedPreferences = getSharedPreferences("login_preference", MODE_PRIVATE);
        UserId = sharedPreferences.getString("userid", "");
        getusername= sharedPreferences.getString("name", "");
        back_img=findViewById(R.id.back_img);
        deposite_ll=findViewById(R.id.deposite_ll);
        withdraw_ll=findViewById(R.id.withdraw_ll);
        wallet_histroy=findViewById(R.id.wallet_histroy);

        deposite_amount_tv=findViewById(R.id.deposite_amount_tv);
        withdraw_amount_tv=findViewById(R.id.withdraw_amount_tv);
        srl_refresh = findViewById(R.id.srl_refresh);
        norecord_img=findViewById(R.id.norecord_img);


        getHistory();

//        deviceId();
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        number = rnd.nextInt(999999);


        srl_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (NetworkConnectionHelper.isOnline(VirtualAccountPlan.this)) {
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
                    Toast.makeText(VirtualAccountPlan.this, "Please check your internet connection! try again...", Toast.LENGTH_SHORT).show();
                }
            }
        });
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        deposite_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Deposit Clicked!",Toast.LENGTH_LONG).show();
            }
        });
        withdraw_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                withdraw_popup();
            }
        });

    }


    public void getHistory() {
        final ProgressDialog progressDialog = new ProgressDialog(VirtualAccountPlan.this);
        progressDialog.setMessage("Loading");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Api_Urls.BaseURL+"GetVirtualAccountDetails", new Response.Listener<String>() {
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
                    if (jsonObject.getString("status").equals("false")){
                        norecord_img.setVisibility(View.VISIBLE);
                        Toast.makeText(getApplicationContext(),"No Record Found",Toast.LENGTH_LONG).show();
                    }
                    else {
                        norecord_img.setVisibility(View.GONE);
                    }
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
                        hm.put("bankname", jsonObject1.getString("bankname"));
                        hm.put("IfscCode", jsonObject1.getString("IfscCode"));

                        hm.put("deposit", jsonObject1.getString("deposit"));
                        hm.put("withdrawal", jsonObject1.getString("withdrawal"));
                        hm.put("balance", jsonObject1.getString("balance"));


                        hm.put("BeneficiaryName", jsonObject1.getString("BeneficiaryName"));
                        deposite_amount_tv.setText(jsonObject1.getString("deposit")+" \u20B9");
                        withdraw_amount_tv.setText(jsonObject1.getString("withdrawal")+" \u20B9");

                        arrayList.add(hm);
                    }
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(VirtualAccountPlan.this, 1);
                    VirtualAccountPlanAdapter customerListAdapter = new VirtualAccountPlanAdapter(VirtualAccountPlan.this, arrayList);
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
                params.put("PlanType", "vir");
                return params;

            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue = Volley.newRequestQueue(VirtualAccountPlan.this);
        requestQueue.add(stringRequest);
    }

    private void withdraw_popup() {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = findViewById(android.R.id.content);
        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(this).inflate(R.layout.withdrawpopup, viewGroup, false);
        AppCompatButton Submit_btn = (AppCompatButton) dialogView.findViewById(R.id.Submit_btn);
        EditText rupee_et=(EditText)dialogView.findViewById(R.id.amount_et);
        ifsc_et=findViewById(R.id.ifsc_et);
        accoount_no_et=findViewById(R.id.accoount_no_et);
        name_et=findViewById(R.id.name_et);
        mobile_et=findViewById(R.id.mobile_et);

        Submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ProfitWithdrawService("ecaea59e-ffe2-4a6a-88c5-68f4b40f1364",ifsc_et.getText().toString(),name_et.getText().toString(),accoount_no_et.getText().toString(), "001",rupee_et.getText().toString(),mobile_et.getText().toString());
                final ProgressDialog progressDialog = new ProgressDialog(VirtualAccountPlan.this);
                progressDialog.setMessage("Loading...");
                progressDialog.show();
                StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://shagunmicrofinance.com/AccountApi.asmx/PayAmount", new  Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                        String jsonString = response;
                        try {
                            JSONObject jsonObject = new JSONObject(jsonString);
                            Log.v("withdrawreponse", String.valueOf(jsonObject));
                            if(jsonObject.getString("status").equalsIgnoreCase("true")){
                                Toast.makeText(getApplicationContext(),jsonObject.getString("status")+jsonObject.getString("mess"),Toast.LENGTH_LONG).show();
//                                Toast.makeText(getApplicationContext(),"Wallet Request send Successfully",Toast.LENGTH_LONG).show();
                                alertDialog.dismiss();
                                finish();
                                overridePendingTransition(0, 0);
                                startActivity(getIntent());
                                overridePendingTransition(0, 0);
                            }
                            else {
                                Toast.makeText(getApplicationContext(),jsonObject.getString("mess"),Toast.LENGTH_LONG).show();
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
                        Toast.makeText(VirtualAccountPlan.this, "Internet connection is slow Or no internet connection", Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String, String> params = new HashMap<>();
                        params.put("MemberID",UserId);
                        params.put("AmountPay",name_et.getText().toString());
                        params.put("Cifsc",ifsc_et.getText().toString());
                        params.put("memName",getusername);
                        params.put("Acnumber",accoount_no_et.getText().toString());
                        params.put("CustomerMobileNo",mobile_et.getText().toString());
                        params.put("AccNo", "");

//                        params.put("apiid",UserId);
//                        params.put("token",imeiNumber);
//                        params.put("methodName","sendamount");
//                        params.put("ifsc",ifsc_et.getText().toString().trim());
//                        params.put("name",name_et.getText().toString().trim());
//                        params.put("number",accoount_no_et.getText().toString().trim());
//                        params.put("orderID", String.valueOf(number));
//                        params.put("Amount","AmountPay");
//                        params.put("PaymentType","UPI");
//                        params.put("CustomerMobileNo",mobile_et.getText().toString().trim());
                        return params;
                    }
                };
                stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                RequestQueue requestQueue = Volley.newRequestQueue(VirtualAccountPlan.this);
                requestQueue.add(stringRequest);
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


/*
    public void ProfitWithdrawService(String id,
                                      String token,String method,
                                      String ifsc,String name, String account_name,String orderid,String amount,
                                      String type,String mobile_number) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://apidashboard.idspay.in/API/payout.aspx", new  Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
                String jsonString = response;
                try {
                    JSONObject jsonObject = new JSONObject(jsonString);
                    Log.v("withdrawreponse", String.valueOf(jsonObject));
                    if(jsonObject.getString("status").equalsIgnoreCase("true")){
                        Toast.makeText(getApplicationContext(),"Wallet Request send Successfully",Toast.LENGTH_LONG).show();
                        alertDialog.dismiss();
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(getIntent());
                        overridePendingTransition(0, 0);
                    }
                    else {
                        Toast.makeText(getApplicationContext(),jsonObject.getString("mess"),Toast.LENGTH_LONG).show();
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
                Toast.makeText(VirtualAccountPlan.this, "Internet connection is slow Or no internet connection", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("apiid",id);
                params.put("token",token);
                params.put("methodName",method);
                params.put("ifsc",ifsc);
                params.put("name",name);
                params.put("number",account_name);
                params.put("orderID",orderid);
                params.put("Amount",amount);
                params.put("PaymentType",type);
                params.put("CustomerMobileNo",mobile_number);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(VirtualAccountPlan.this);
        requestQueue.add(stringRequest);
    }
*/
    private void deviceId() {
        telephonyManager = (TelephonyManager) getSystemService(this.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 101);
            return;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        switch (requestCode) {
            case 101:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 101);
                        return;
                    }
//                    imeiNumber = telephonyManager.getDeviceId();
//                    Toast.makeText(VirtualAccountPlan.this,imeiNumber,Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(VirtualAccountPlan.this,"Without permission we check",Toast.LENGTH_LONG).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
