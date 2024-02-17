package com.akp.shagun.MPIN;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.akp.shagun.MainActivity;
import com.akp.shagun.R;
import com.hanks.passcodeview.PasscodeView;

public class PinActivity extends AppCompatActivity {
    // initialize variable passcodeview
    PasscodeView passcodeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);
        passcodeView = findViewById(R.id.passcodeview);

        // to set length of password as here
        // we have set the length as 5 digits
        passcodeView.setPasscodeLength(5)
                // to set pincode or passcode
                .setLocalPasscode("12345")
                // to set listener to it to check whether
                // passwords has matched or failed
                .setListener(new PasscodeView.PasscodeViewListener() {
                    @Override
                    public void onFail() {
                        // to show message when Password is incorrect
                        Toast.makeText(PinActivity.this, "Password is wrong!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(String number) {
                        Toast.makeText(PinActivity.this, "Successfully Created MPIN", Toast.LENGTH_SHORT).show();
                        // here is used so that when password
                        // is correct user will be
                        // directly navigated to next activity
                        Intent intent_passcode = new Intent(PinActivity.this, MainActivity.class);
                        startActivity(intent_passcode);
                    }
                });
    }
}