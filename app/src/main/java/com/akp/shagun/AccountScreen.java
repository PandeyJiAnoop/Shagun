package com.akp.shagun;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.akp.shagun.Account.ChangePassword;
import com.akp.shagun.Account.HelpActivity;
import com.akp.shagun.Account.PolicyActivity;
import com.akp.shagun.Account.WalletActivity;
import com.akp.shagun.MPIN.CustomPinActivity;
import com.akp.shagun.dashboard.AccountOverview;
import com.akp.shagun.dashboard.ServicesScreen;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.omadahealth.lollipin.lib.managers.AppLock;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AccountScreen extends AppCompatActivity {
    ImageView back_img;
    LinearLayout edit_profile_ll,wallet_ll,help_ll,policy_ll,mpin_ll,change_pass_ll,logout_ll;
    TextView first_name_tv,mobile_tv,name_tv;
    private SharedPreferences sharedPreferences;
    String UserId,Username;

    LinearLayout add_fund_ll, add_fund_history_ll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_screen);
        sharedPreferences = getSharedPreferences("login_preference", MODE_PRIVATE);
        UserId = sharedPreferences.getString("userid", "");
        Username = sharedPreferences.getString("name", "");

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.rlBottom);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        displayView(3);

        back_img=findViewById(R.id.back_img);
        edit_profile_ll=findViewById(R.id.edit_profile_ll);
        wallet_ll=findViewById(R.id.wallet_ll);
        help_ll=findViewById(R.id.help_ll);
        policy_ll=findViewById(R.id.policy_ll);
        mpin_ll=findViewById(R.id.mpin_ll);
        change_pass_ll=findViewById(R.id.change_pass_ll);
        logout_ll=findViewById(R.id.logout_ll);

        first_name_tv=findViewById(R.id.first_name_tv);
        mobile_tv=findViewById(R.id.mobile_tv);
        name_tv=findViewById(R.id.name_tv);


        add_fund_ll=findViewById(R.id.add_fund_ll);
        add_fund_history_ll=findViewById(R.id.add_fund_history_ll);

        getdeatils();
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        edit_profile_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), AccountOverview.class);
                startActivity(intent);
            }
        });
        wallet_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), WalletActivity.class);
                startActivity(intent);
            }
        });

        add_fund_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), AddWallet.class);
                startActivity(intent);
            }
        });

        add_fund_history_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), WalletTransferHistory.class);
                startActivity(intent);
            }
        });
        help_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), HelpActivity.class);
                startActivity(intent);
            }
        });

        mpin_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountScreen.this, CustomPinActivity.class);
                intent.putExtra(AppLock.EXTRA_TYPE, AppLock.CHANGE_PIN);
                startActivity(intent);
            }
        });
        policy_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), PolicyActivity.class);
                startActivity(intent);
            }
        });
        change_pass_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), ChangePassword.class);
                startActivity(intent);
            }
        });


        logout_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AccountScreen.this);
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
    }

    public void getdeatils() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Api_Urls.BaseURL + "ViewProfile", new Response.Listener<String>() {
            //        StringRequest stringRequest = new StringRequest(Request.Method.POST, Api_Urls.Signature_BASE_URL + url, new  Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("sdsdsd","sd"+UserId);

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
                            Toast.makeText(AccountScreen.this, "UserId or Password is wrong", Toast.LENGTH_SHORT).show();
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

                            name_tv.setText(memberName);
                            first_name_tv.setText(memberName);
                            mobile_tv.setText(mobileNo);



                        } }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(AccountScreen.this, "Something went wrong:-" + error, Toast.LENGTH_SHORT).show();
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
        RequestQueue requestQueue = Volley.newRequestQueue(AccountScreen.this);
        requestQueue.add(stringRequest);
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
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                break;
            case 2:
                startActivity(new Intent(getApplicationContext(), ServicesScreen.class));
                break;
            case 3:
                break;
            default:
                break;
        }
    }

}