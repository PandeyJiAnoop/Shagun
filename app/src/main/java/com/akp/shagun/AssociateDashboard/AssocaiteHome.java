package com.akp.shagun.AssociateDashboard;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.akp.shagun.AddWallet;
import com.akp.shagun.Api_Urls;
import com.akp.shagun.CustomAdapter;
import com.akp.shagun.MainActivity;
import com.akp.shagun.Mobile.MobileRecharge;
import com.akp.shagun.NetworkConnectionHelper;
import com.akp.shagun.R;
import com.akp.shagun.SplashScreen;
import com.akp.shagun.WalletTransferHistory;
import com.akp.shagun.WithdrawHistory;
import com.akp.shagun.rechargebills.DTHBill;
import com.akp.shagun.rechargebills.ElectricityBill;
import com.akp.shagun.rechargebills.InsuranceBill;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.viewpagerindicator.CirclePageIndicator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class AssocaiteHome extends AppCompatActivity {

    ViewPager viewPager;
    Integer[] imageId = {R.drawable.a, R.drawable.aa, R.drawable.aaa,  R.drawable.d,R.drawable.e};
    String[] imagesName = {"image1","image2","image3","image4"};
    Timer timer;
    private static int currentPage = 0;
    private ViewPager pager;
    CirclePageIndicator indicator;
    private SharedPreferences sharedPreferences;
    String UserId,Username;
    private SwipeRefreshLayout srl_refresh;

    LinearLayout profile_rl,purchase_plan_rl,downline_rl,total_business_rl;
    CardView logout_rl;

    LinearLayout mobile_ll, dth_ll, electricity_ll,history_ll,insurance_ll;
    TextView amount_tv,fund_tv,raise_add_tv,withdraw_tv;
    LinearLayout payrenewal_rl,payrenewal_history_rl;

    LinearLayout loanrepayment_rl,repayment_history_rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assocaite_home);
        sharedPreferences = getSharedPreferences("login_preference", MODE_PRIVATE);
        UserId = sharedPreferences.getString("userid", "");
        Username = sharedPreferences.getString("name", "");
        Toast.makeText(getApplicationContext(),"Welcome:- "+Username+"\nin Shagun FINANCE ASSOCIATION",Toast.LENGTH_LONG).show();
        GetWallet();
        GetWallet1();
        profile_rl=findViewById(R.id.profile_rl);
        purchase_plan_rl=findViewById(R.id.purchase_plan_rl);
        downline_rl=findViewById(R.id.downline_rl);
        total_business_rl=findViewById(R.id.total_business_rl);
        logout_rl=findViewById(R.id.logout_rl);
        fund_tv= findViewById(R.id.fund_tv);
        amount_tv= findViewById(R.id.amount_tv);
        raise_add_tv= findViewById(R.id.raise_add_tv);
        withdraw_tv= findViewById(R.id.withdraw_tv);
        mobile_ll = findViewById(R.id.mobile_ll);
        dth_ll = findViewById(R.id.dth_ll);
        electricity_ll = findViewById(R.id.electricity_ll);
        history_ll= findViewById(R.id.history_ll);
        insurance_ll= findViewById(R.id.insurance_ll);

        payrenewal_rl= findViewById(R.id.payrenewal_rl);
        payrenewal_history_rl= findViewById(R.id.payrenewal_history_rl);
        loanrepayment_rl= findViewById(R.id.loanrepayment_rl);
        repayment_history_rl= findViewById(R.id.repayment_history_rl);

        mobile_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AssocaiteHome.this, MobileRecharge.class);
                overridePendingTransition(0, 0);
                intent.putExtra("onlyservice", "Mobile");
                startActivity(intent);
            }
        });
        dth_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AssocaiteHome.this, DTHBill.class);
                overridePendingTransition(0, 0);
                intent.putExtra("onlyservice", "DTH");
                startActivity(intent);
            }
        });

        insurance_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AssocaiteHome.this, InsuranceBill.class);
                overridePendingTransition(0, 0);
                intent.putExtra("onlyservice", "Insurance");
                startActivity(intent);
            }
        });

        electricity_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity(), "Coming Soon!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AssocaiteHome.this, ElectricityBill.class);
                overridePendingTransition(0, 0);
                intent.putExtra("onlyservice", "ELECTRICITY");
                startActivity(intent);
            }
        });
        history_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(), WithdrawHistory.class);
                startActivity(intent);
            }
        });

        profile_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),AssociateProfile.class);
                startActivity(intent);
            }
        });
        purchase_plan_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),AssocaitePurchasePlan.class);
                startActivity(intent);
            }
        });
        downline_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),AssociateDownline.class);
                startActivity(intent);
            }
        });
        total_business_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),AssociateTotalBusiness.class);
                startActivity(intent);
            }
        });

        raise_add_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(), AddWallet.class);
                startActivity(intent);
            }
        });
        withdraw_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(), WalletTransferHistory.class);
                startActivity(intent);
            }
        });


        payrenewal_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(), PayRenewal.class);
                startActivity(intent);
            }
        });
        payrenewal_history_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(), PayRenewalHistory.class);
                startActivity(intent);
            }
        });



        loanrepayment_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(), LoanRePayment.class);
                startActivity(intent);
            }
        });
        repayment_history_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),RepaymentHistory.class);
                startActivity(intent);
            }
        });




        logout_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AssocaiteHome.this);
                builder.setMessage("Are you sure want to logout?");
                builder.setPositiveButton(Html.fromHtml("<font color='#E5B80B'>Yes</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences myPrefs = getSharedPreferences("login_preference", MODE_PRIVATE);
                        SharedPreferences.Editor editor = myPrefs.edit();
                        editor.clear();
                        editor.commit();
                        Intent intent = new Intent(getApplicationContext(), SplashScreen.class);
                        intent.putExtra("finish", true);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // To clean up all activities
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton(Html.fromHtml("<font color='#E5B80B'>NO</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                android.app.AlertDialog alert = builder.create();
                alert.show();
                Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
                //Set negative button background
                nbutton.setBackgroundColor(Color.RED);
                //Set negative button text color
                nbutton.setTextColor(Color.WHITE);
                Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
                //Set positive button background
                pbutton.setBackgroundColor(Color.BLUE);
                //Set positive button text color
                pbutton.setTextColor(Color.WHITE);
            }
        });



        indicator = (CirclePageIndicator) findViewById(R.id.indicator);
        viewPager = findViewById(R.id.pager);
        PagerAdapter adapter = new CustomAdapter(AssocaiteHome.this,imageId,imagesName);
        viewPager.setAdapter(adapter);
        viewPager.setClipToPadding(false);
//        viewPager.setPageMargin(24);
//        viewPager.setPadding(48, 8, 130, 8);
        viewPager.setOffscreenPageLimit(3);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                viewPager.post(new Runnable(){
                    @Override
                    public void run() {
                        viewPager.setCurrentItem((viewPager.getCurrentItem()+1)%imageId.length);
                    }});
            }
        };
        timer = new Timer();
        timer.schedule(timerTask, 3000, 3000);


        srl_refresh = findViewById(R.id.swipe_home_container);
        srl_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (NetworkConnectionHelper.isOnline(AssocaiteHome.this)) {
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
                    Toast.makeText(AssocaiteHome.this, "Please check your internet connection! try again...", Toast.LENGTH_SHORT).show();
                } }});

    }



    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton(Html.fromHtml("<font color='#000000'>Yes</font>"), new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id) {
                        //finish();
                        onSuperBackPressed();
                        //super.onBackPressed();
                    }
                })
                .setNegativeButton(Html.fromHtml("<font color='#000000'>No</font>"), new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void onSuperBackPressed(){
        super.onBackPressed();
    }


    public void GetWallet() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://shagunmicrofinance.com/webservicenew.asmx/GetBalance", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                String jsonString = response;
                jsonString = jsonString.replace("<?xml version=\"1.0\" encoding=\"utf-8\"?>", " ");
                jsonString = jsonString.replace("<string xmlns=\"http://tempuri.org/\">", " ");
                jsonString = jsonString.replace("</string>", " ");
                Log.d("res_p",response);
                try {
                    JSONObject object = new JSONObject(jsonString);
                    if (object.getString("status").equalsIgnoreCase("false")){
                        Toast.makeText(getApplicationContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        JSONArray jsonArray = object.getJSONArray("Response");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            amount_tv.setText("\u20B9 "+jsonObject1.getString("Bid"));
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
                Toast.makeText(AssocaiteHome.this, "Something went wrong:-" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("UserId", UserId);
                params.put("Amount","0");
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(AssocaiteHome.this);
        requestQueue.add(stringRequest);
    }
    public void GetWallet1() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Api_Urls.BaseURL+"GetVirtualBalance", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                String jsonString = response;
                jsonString = jsonString.replace("<?xml version=\"1.0\" encoding=\"utf-8\"?>", " ");
                jsonString = jsonString.replace("<string xmlns=\"http://tempuri.org/\">", " ");
                jsonString = jsonString.replace("</string>", " ");
                Log.d("res_p",response);
                try {
                    JSONObject object = new JSONObject(jsonString);
                    if (object.getString("status").equalsIgnoreCase("false")){
                        Toast.makeText(getApplicationContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
                    }
                    else {
//                        JSONArray jsonArray = object.getJSONArray("Response");
//                        for (int i = 0; i < jsonArray.length(); i++) {
//                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        fund_tv.setText("\u20B9 "+object.getString("Balance"));
//                        }
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
                Toast.makeText(AssocaiteHome.this, "Something went wrong:-" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("UserId", UserId);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(AssocaiteHome.this);
        requestQueue.add(stringRequest);
    }
}
