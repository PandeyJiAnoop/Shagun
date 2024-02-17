package com.akp.shagun.APIServices;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.akp.shagun.R;

public class IFSCVerification extends AppCompatActivity {
    ImageView ivBack;
    EditText mob_et;
    Button submit_et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i_f_s_c_verification);
        ivBack=findViewById(R.id.ivBack);
        mob_et=findViewById(R.id.mob_et);
        submit_et=findViewById(R.id.submit_et);

        submit_et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mob_et.getText().toString().equalsIgnoreCase("")){
                    mob_et.setError("Fields can't be blank!");
                    mob_et.requestFocus();
                }
                else {
//                    GetCustomer(mob_et.getText().toString());
                }
            }
        });


        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void AlertVersion() {
        final Dialog dialog = new Dialog(IFSCVerification.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_moneytrasfer);
        TextView tvMessage = dialog.findViewById(R.id.tvMessage);
        TextView btnSubmit = dialog.findViewById(R.id.btnSubmit);
        TextView btnCancel = dialog.findViewById(R.id.btnCancel);
        tvMessage.setText("Your number =91-"+mob_et.getText().toString()+" is not registered");
        btnSubmit.setText("REGISTER");
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), IFSCVerification.class);
                intent.putExtra("mobile",mob_et.getText().toString());
                startActivity(intent);
            }
        });
    }


  /*  private void GetCustomer(String mob) {
        String otp1 = new GlobalAppApis().PostGetCustomerAPI(mob);
        ApiService client1 = getApiClient_ByPost();
        Call<String> call1 = client1.GetCustomerAPI(otp1);
        new ConnectToRetrofit(new RetrofitCallBackListenar() {
            @Override
            public void RetrofitCallBackListenar(String result, String action) throws JSONException {
//                Toast.makeText(getApplicationContext(),""+result,Toast.LENGTH_LONG).show();
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    if (jsonObject.getString("Status").equalsIgnoreCase("true")){

                        Toast.makeText(getApplicationContext(),jsonObject.getString("Message"),Toast.LENGTH_LONG).show();

                    }
                    else {
                        AlertVersion();
                        Toast.makeText(getApplicationContext(),jsonObject.getString("Message"),Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, MoneyTransfer.this, call1, "", true);

    }*/


}