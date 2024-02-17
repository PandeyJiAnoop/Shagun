package com.akp.shagun;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.akp.shagun.Account.ChangePassword;
import com.akp.shagun.Banking.MoneyTransfer;
import com.akp.shagun.Banking.ViewReport;
import com.akp.shagun.LoanDetails.LoanInsurance;
import com.akp.shagun.LoanDetails.LoanProcessing;
import com.akp.shagun.LoanDetails.LoanRecovery;
import com.akp.shagun.Mobile.DasRechargeListAdapter;
import com.akp.shagun.Mobile.MobileRecharge;
import com.akp.shagun.Mobile.SRSMobileRecharge;
import com.akp.shagun.PlanDetails.AllPlan;
import com.akp.shagun.PlanDetails.VirtualAccountPlan;
import com.akp.shagun.RetrofitAPI.ApiService;
import com.akp.shagun.RetrofitAPI.ConnectToRetrofit;
import com.akp.shagun.RetrofitAPI.RetrofitCallBackListenar;
import com.akp.shagun.dashboard.AccountOverview;
import com.akp.shagun.dashboard.ServicesScreen;
import com.akp.shagun.rechargebills.DTHBill;
import com.akp.shagun.rechargebills.ElectricityBill;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.viewpagerindicator.CirclePageIndicator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;

import static com.akp.shagun.RetrofitAPI.API_Config.getApiClient_ByPost;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    Integer[] imageId = {R.drawable.a, R.drawable.aa, R.drawable.aaa,  R.drawable.d,R.drawable.e};
    String[] imagesName = {"image1","image2","image3","image4"};
    Timer timer;
    private static int currentPage = 0;
    private ViewPager pager;
    CirclePageIndicator indicator;
    LinearLayout deposite_ll,loan_req_ll,renewal_ll,profile_ll,policy_details_ll;
    RelativeLayout account_overview_rl,recent_transaction_rl;
    private SharedPreferences sharedPreferences;
    String UserId,Username;
    private SwipeRefreshLayout srl_refresh;
    LinearLayout daily_plan_ll,recuring_plan_ll,fixed_deposite_plan_ll,monthly_income_ll,saving_plan_ll, virtual_plan_ll;

    List<BannerData> bannerData = new ArrayList<>();

    RecyclerView cust_recyclerView;
    LinearLayout view_report_ll,money_transfer_ll;
    ArrayList<HashMap<String, String>> arrayList1 = new ArrayList<>();
    LinearLayout mobile_ll, dth_ll, electricity_ll;

    ImageView wallet_img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("login_preference", MODE_PRIVATE);
        UserId = sharedPreferences.getString("userid", "");
        Username = sharedPreferences.getString("name", "");
        Toast.makeText(getApplicationContext(),"Welcome:- "+Username+"\n in Shagun FINANCE ASSOCIATION",Toast.LENGTH_LONG).show();

        deposite_ll=findViewById(R.id.deposite_ll);
        loan_req_ll=findViewById(R.id.loan_req_ll);
        renewal_ll=findViewById(R.id.renewal_ll);
        profile_ll=findViewById(R.id.profile_ll);
        account_overview_rl=findViewById(R.id.account_overview_rl);
        recent_transaction_rl=findViewById(R.id.recent_transaction_rl);
//        loan_details_ll=findViewById(R.id.loan_details_ll);
        policy_details_ll=findViewById(R.id.policy_details_ll);


        daily_plan_ll=findViewById(R.id.daily_plan_ll);
        recuring_plan_ll=findViewById(R.id.recuring_plan_ll);
        fixed_deposite_plan_ll=findViewById(R.id.fixed_deposite_plan_ll);
        monthly_income_ll=findViewById(R.id.monthly_income_ll);
        saving_plan_ll=findViewById(R.id.saving_plan_ll);
        virtual_plan_ll=findViewById(R.id.virtual_plan_ll);

        cust_recyclerView = findViewById(R.id.cust_recyclerView);
        wallet_img= findViewById(R.id.wallet_img);
        mobile_ll = findViewById(R.id.mobile_ll);
        dth_ll = findViewById(R.id.dth_ll);
        electricity_ll = findViewById(R.id.electricity_ll);
        mobile_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MobileRecharge.class);
                overridePendingTransition(0, 0);
                intent.putExtra("onlyservice", "Mobile");
                startActivity(intent);
            }
        });
        dth_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DTHBill.class);
                overridePendingTransition(0, 0);
                intent.putExtra("onlyservice", "DTH");
                startActivity(intent);
            }
        });
        electricity_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity(), "Coming Soon!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, ElectricityBill.class);
                overridePendingTransition(0, 0);
                intent.putExtra("onlyservice", "ELECTRICITY");
                startActivity(intent);
            }
        });



        wallet_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddWallet.class);
                overridePendingTransition(0, 0);
                startActivity(intent);
            }
        });
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.rlBottom);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        displayView(1);
        indicator = (CirclePageIndicator) findViewById(R.id.indicator);
        viewPager = findViewById(R.id.pager);
//        getBanner();
        PagerAdapter adapter = new CustomAdapter(MainActivity.this,imageId,imagesName);
        viewPager.setAdapter(adapter);
        viewPager.setClipToPadding(false);
//        viewPager.setPageMargin(24);
//        viewPager.setPadding(48, 8, 130, 8);
        viewPager.setOffscreenPageLimit(4);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                viewPager.post(new Runnable(){
                    @Override
                    public void run() { viewPager.setCurrentItem((viewPager.getCurrentItem()+1)%imageId.length);
                    }});
            }
        };
        timer = new Timer();
        timer.schedule(timerTask, 3000, 3000);

        money_transfer_ll =findViewById(R.id.money_transfer_ll);
        view_report_ll = findViewById(R.id.view_report_ll);

        money_transfer_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, MoneyTransfer.class);
                startActivity(intent);
            }
        });
        view_report_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, ViewReport.class);
                startActivity(intent);
            }
        });


        getHistory();

        account_overview_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AccountOverview.class));
            }});
        recent_transaction_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), WithdrawHistory.class)); }
        });

        srl_refresh = findViewById(R.id.swipe_home_container);
        srl_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (NetworkConnectionHelper.isOnline(MainActivity.this)) {
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
                    Toast.makeText(MainActivity.this, "Please check your internet connection! try again...", Toast.LENGTH_SHORT).show();
                } }});
        policy_details_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ChangePassword.class));
            }
        });
//        loan_details_ll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), LoanDetails.class));
//
//            }
//        });
        renewal_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoanRecovery.class));
            }
        });
        deposite_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoanProcessing.class));
            }
        });
        loan_req_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoanInsurance.class));
            }
        });
        profile_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AccountScreen.class));
            }
        });

        daily_plan_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), AllPlan.class);
                intent.putExtra("type","dd");
                startActivity(intent);
            }
        });
        recuring_plan_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AllPlan.class);
                intent.putExtra("type","rd");
                startActivity(intent);            }
        });
        fixed_deposite_plan_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AllPlan.class);
                intent.putExtra("type","fd");
                startActivity(intent);            }
        });
        monthly_income_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AllPlan.class);
                intent.putExtra("type","mis");
                startActivity(intent);            }
        });
        saving_plan_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AllPlan.class);
                intent.putExtra("type","SP");
                startActivity(intent);
            }
        });
        virtual_plan_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), VirtualAccountPlan.class);
                startActivity(intent);
            }
        });


    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home:
                    displayView(1);
                    // hitFilterApi();
                    return true;
                case R.id.contactus:
                    displayView(2);
                    return true;
                case R.id.logout:
                    displayView(3);
                    return true;
            }
            return false;
        }
    };
    private void displayView(int position) {
        switch (position) {
            case 1:
                break;
            case 2:
                startActivity(new Intent(getApplicationContext(), AccountOverview.class));
                break;
            case 3:
                startActivity(new Intent(getApplicationContext(), AccountScreen.class));
                break;
            default:
                break;
        }
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
    /*
    if (handleCancel()){
        super.onBackPressed();
    }
    */
    }
    public void onSuperBackPressed(){
        super.onBackPressed();
    }


    public void getBanner() {
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.show();
        progressDialog.setMessage("Loading");
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://srsnidhi.com/webservicenew.asmx/GetBanner", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                String jsonString = response;
                jsonString = jsonString.replace("<?xml version=\"1.0\" encoding=\"utf-8\"?>"," ");
                jsonString = jsonString.replace("<string xmlns=\"http://tempuri.org/\">"," ");
                jsonString = jsonString.replace("</string>"," ");
                try {
                    JSONObject jsonObject = new JSONObject(jsonString);
//                        JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                    String JsonInString = jsonObject.getString("Response");
                    bannerData = BannerData.createJsonInList(JsonInString);
                    pager.setAdapter(new AdapterForBanner(MainActivity.this, bannerData));
                    indicator.setViewPager(pager);
                    indicator.setFillColor(Color.RED);

                    final float density = getResources().getDisplayMetrics().density;
//Set circle indicator radius
                    indicator.setRadius(5 * density);
                    // Auto start of viewpager
                    final Handler handler = new Handler();
                    final Runnable Update = new Runnable() {
                        public void run() {
                            if (currentPage == bannerData.size()) {
                                currentPage = 0;
                            }
                            pager.setCurrentItem(currentPage++, true);
                        }
                    };
                    Timer swipeTimer = new Timer();
                    swipeTimer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            handler.post(Update);
                        }
                    }, 5000, 3000);
                    // Pager listener over indicator
                    indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @Override
                        public void onPageSelected(int position) {
                            currentPage = position;
                        }
                        @Override
                        public void onPageScrolled(int pos, float arg1, int arg2) { }
                        @Override
                        public void onPageScrollStateChanged(int pos) { }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //  Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Please check your Internet Connection! try again...", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);
    }


    public void getHistory() {
        ApiService client1 = getApiClient_ByPost();
        Call<String> call1 = client1.OperatorList();
        new ConnectToRetrofit(new RetrofitCallBackListenar() {
            @Override
            public void RetrofitCallBackListenar(String result, String action) throws JSONException {
//                Toast.makeText(getApplicationContext(),""+result,Toast.LENGTH_LONG).show();
                try {
//                jsonString = jsonString.replace("<?xml version=\"1.0\" encoding=\"utf-8\"?>"," ");
//                jsonString = jsonString.replace("<string xmlns=\"http://tempuri.org/\">"," ");
//                jsonString = jsonString.replace("</string>"," ");
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArrayr = jsonObject.getJSONArray("Response");
                    for (int i = 0; i < jsonArrayr.length(); i++) {
                        JSONObject jsonObject1 = jsonArrayr.getJSONObject(i);
                        HashMap<String, String> hm = new HashMap<>();
                        hm.put("service_name", jsonObject1.getString("service_name"));
                        hm.put("service_icon", jsonObject1.getString("service_icon"));
                        arrayList1.add(hm);
                    }
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
                    DasRechargeListAdapter customerListAdapter = new DasRechargeListAdapter(getApplicationContext(), arrayList1);
                    cust_recyclerView.setLayoutManager(gridLayoutManager);
                    cust_recyclerView.setAdapter(customerListAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, MainActivity.this, call1, "", true);
    }

}