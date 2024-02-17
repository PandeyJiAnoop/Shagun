package com.akp.shagun.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.akp.shagun.APIServices.AadharVerification;
import com.akp.shagun.APIServices.AccountVerification;
import com.akp.shagun.APIServices.BillPayment;
import com.akp.shagun.APIServices.IFSCVerification;
import com.akp.shagun.APIServices.Panverification;
import com.akp.shagun.APIServices.UPITransfer;
import com.akp.shagun.APIServices.UPIVerification;
import com.akp.shagun.Banking.MoneyTransfer;
import com.akp.shagun.Mobile.SRSMobileRecharge;
import com.akp.shagun.R;

public class ServicesScreen extends AppCompatActivity {
    LinearLayout mobilerecharge_ll,billpayment_ll,money_transfer_ll,upiqrcode_ll,creditscore_ll,upi_transfer_ll,otpverfication_ll,panverification_ll,ifscverification_ll,accountverification_ll,upi_ll;
    ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_screen);
        mobilerecharge_ll=findViewById(R.id.mobilerecharge_ll);
        billpayment_ll=findViewById(R.id.billpayment_ll);
        money_transfer_ll=findViewById(R.id.money_transfer_ll);
        upiqrcode_ll=findViewById(R.id.upiqrcode_ll);
        creditscore_ll=findViewById(R.id.creditscore_ll);
        upi_transfer_ll=findViewById(R.id.upi_transfer_ll);
        otpverfication_ll=findViewById(R.id.otpverfication_ll);
        panverification_ll=findViewById(R.id.panverification_ll);
        ifscverification_ll=findViewById(R.id.ifscverification_ll);
        accountverification_ll=findViewById(R.id.accountverification_ll);
        upi_ll=findViewById(R.id.upi_ll);


        ivBack=findViewById(R.id.ivBack);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        accountverification_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), AccountVerification.class);
                startActivity(intent);
            }
        });

        upi_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), UPIVerification.class);
                startActivity(intent);
            }
        });
        mobilerecharge_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), SRSMobileRecharge.class);
                startActivity(intent);
            }
        });
        billpayment_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), BillPayment.class);
                startActivity(intent);
//                Toast.makeText(getApplicationContext(),"Coming Soon!",Toast.LENGTH_LONG).show();
            }
        });
        money_transfer_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), MoneyTransfer.class);
                startActivity(intent);            }
        });
        upiqrcode_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Coming soon!",Toast.LENGTH_LONG).show();
            }
        });
        creditscore_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Coming Soon!",Toast.LENGTH_LONG).show();
            }
        });

        upi_transfer_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), UPITransfer.class);
                startActivity(intent);
            }
        });

        otpverfication_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), AadharVerification.class);
                startActivity(intent);
            }
        });
        panverification_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), Panverification.class);
                startActivity(intent);
            }
        });
        ifscverification_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), IFSCVerification.class);
                startActivity(intent);
            }
        });

    }
}