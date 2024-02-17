package com.akp.shagun.APIServices;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.akp.shagun.R;

public class UPITransfer extends AppCompatActivity {
    ImageView back_img;
    TextView tag_tv,upiname_tv;
    EditText rupee_et;
    EditText edt_pass,edt_msg;
    AppCompatButton buttonOk;

//    String getUPIId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u_p_i_transfer);
        back_img=findViewById(R.id.back_img);
        tag_tv=findViewById(R.id.tag_tv);
        upiname_tv=findViewById(R.id.upiname_tv);
        rupee_et=findViewById(R.id.rupee_et);
        edt_pass=findViewById(R.id.edt_pass);
        edt_msg=findViewById(R.id.edt_msg);

        buttonOk=findViewById(R.id.buttonOk);

     /*   if (getUPIId ==  null){
            finish();
        }
        else {
            tag_tv.setText(getUPIId);
            upiname_tv.setText(getUPIId);

        }*/

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rupee_et.getText().toString().equalsIgnoreCase("")){
                    rupee_et.setError("Fields can't be blank!");
                    rupee_et.requestFocus();
                }
                if (edt_pass.getText().toString().equalsIgnoreCase("")){
                    edt_pass.setError("Fields can't be blank!");
                    edt_pass.requestFocus();
                }
                else {
                    buttonOk.setAlpha((float) 1.0);
                }
            }
        });


        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }
}