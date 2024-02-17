package com.akp.shagun.dashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.akp.shagun.Das_loanReq.EMIPaymentForm;
import com.akp.shagun.ContactUs;
import com.akp.shagun.MainActivity;
import com.akp.shagun.R;
import com.akp.shagun.SplashScreen;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LoanRequest extends AppCompatActivity {
    ImageView back_img;
    LinearLayout emi_payment_ll,coming_soon_ll,non_emi_payment_ll,cash_dep_ll;
    private AlertDialog alertDialog1,alertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_request);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.rlBottom);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        back_img=findViewById(R.id.back_img);
        emi_payment_ll=findViewById(R.id.emi_payment_ll);
        coming_soon_ll=findViewById(R.id.coming_soon_ll);
        non_emi_payment_ll=findViewById(R.id.non_emi_payment_ll);
        cash_dep_ll=findViewById(R.id.cash_dep_ll);

        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        emi_payment_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), EMIPaymentForm.class);
                startActivity(intent);
            }
        });
        coming_soon_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Coming Soon!",Toast.LENGTH_LONG).show();
            }
        });
        non_emi_payment_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nonemipayment_popup();
            }
        });
        cash_dep_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cashdeposite_popup();
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
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                break;
            case 2:
                startActivity(new Intent(getApplicationContext(), ContactUs.class));
                break;
            case 3:
                AlertDialog.Builder builder = new AlertDialog.Builder(LoanRequest.this);
                builder.setMessage("Are you sure want to logout?");
                builder.setPositiveButton(Html.fromHtml("<font color='#E5B80B'>Yes</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        SharedPreferences myPrefs = getSharedPreferences("login_preference", MODE_PRIVATE);
//                        SharedPreferences.Editor editor = myPrefs.edit();
//                        editor.clear();
//                        editor.commit();
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
                builder.show();
                break;
            default:
                break;
        }
    }
    private void cashdeposite_popup() {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = findViewById(android.R.id.content);
        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(this).inflate(R.layout.withdrawpopup, viewGroup, false);
        Button Submit_btn = (Button) dialogView.findViewById(R.id.Submit_btn);
        EditText rupee_et=(EditText)dialogView.findViewById(R.id.amount_et);
        Submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ProfitWithdrawService(rupee_et1.getText().toString(),"wallet");
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

    private void nonemipayment_popup() {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup1 = findViewById(android.R.id.content);
        //then we will inflate the custom alert dialog xml that we created
        View dialogView1 = LayoutInflater.from(this).inflate(R.layout.nonemi_popup, viewGroup1, false);
        Button Submit_btn1 = (Button) dialogView1.findViewById(R.id.Submit_btn1);
        EditText rupee_et1=(EditText)dialogView1.findViewById(R.id.rupee_et1);
        Submit_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ProfitWithdrawService(rupee_et1.getText().toString(),"wallet");
                alertDialog1.dismiss();
            }
        });
        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        //setting the view of the builder to our custom view that we already inflated
        builder1.setView(dialogView1);
        //finally creating the alert dialog and displaying it
        alertDialog1 = builder1.create();
        alertDialog1.show();
    }



}