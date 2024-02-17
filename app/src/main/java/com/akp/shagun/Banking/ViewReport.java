package com.akp.shagun.Banking;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.akp.shagun.R;
import com.akp.shagun.RetrofitAPI.ApiService;
import com.akp.shagun.RetrofitAPI.ConnectToRetrofit;
import com.akp.shagun.RetrofitAPI.GlobalAppApis;
import com.akp.shagun.RetrofitAPI.RetrofitCallBackListenar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;

import static com.akp.shagun.RetrofitAPI.API_Config.getApiClient_ByPost;


public class ViewReport extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ImageView ivBack;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    RecyclerView wallet_histroy;
    String UserName;

    private SharedPreferences sharedPreferences;
    Spinner ststus_sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_report);
        sharedPreferences = getSharedPreferences("login_preference", MODE_PRIVATE);
        UserName = sharedPreferences.getString("UserName", "");
        wallet_histroy = findViewById(R.id.wallet_histroy);

        ivBack=findViewById(R.id.ivBack);
        ststus_sp=findViewById(R.id.ststus_sp);


        // Spinner click listener
        ststus_sp.setOnItemSelectedListener(this);
        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("AllTransaction");
        categories.add("MobileRecharge");
        categories.add("OtherRecharge");
        categories.add("LoanAmount");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        ststus_sp.setAdapter(dataAdapter);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        WalletHistoryAPI("",UserName,"AllTransaction","","","");


    }

    private void WalletHistoryAPI(String fromdate, String memberid, String reporttype, String status, String todate, String transactiontype) {
        String otp1 = new GlobalAppApis().TransactionReportHistory(fromdate,memberid,reporttype,status,todate,transactiontype);
        ApiService client1 = getApiClient_ByPost();
        Call<String> call1 = client1.TransactionReportAPI(otp1);
        new ConnectToRetrofit(new RetrofitCallBackListenar() {
            @Override
            public void RetrofitCallBackListenar(String result, String action) throws JSONException {
//                Toast.makeText(getApplicationContext(),""+result, Toast.LENGTH_LONG).show();
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArrayr = jsonObject.getJSONArray("Response");
                    for (int i = 0; i < jsonArrayr.length(); i++) {
                        JSONObject jsonObject1 = jsonArrayr.getJSONObject(i);
                        HashMap<String, String> hm = new HashMap<>();
                        hm.put("Amount", jsonObject1.getString("Amount"));
                        hm.put("Message", jsonObject1.getString("Message"));
                        hm.put("Mobile", jsonObject1.getString("Mobile"));
                        hm.put("Name", jsonObject1.getString("Name"));
                        hm.put("RechargeDate", jsonObject1.getString("RechargeDate"));
                        hm.put("ServiceName", jsonObject1.getString("ServiceName"));
                        hm.put("Status", jsonObject1.getString("Status"));
                        hm.put("TransactionType", jsonObject1.getString("TransactionType"));
                        hm.put("UserId", jsonObject1.getString("UserId"));
                        hm.put("WalletAmount", jsonObject1.getString("WalletAmount"));

                        arrayList.add(hm);
                    }
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(ViewReport.this, 1);
                    Das_ViewReportAdapter customerListAdapter = new Das_ViewReportAdapter(ViewReport.this, arrayList);
                    wallet_histroy.setLayoutManager(gridLayoutManager);
                    wallet_histroy.setAdapter(customerListAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, ViewReport.this, call1, "", true);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        arrayList.clear();
        WalletHistoryAPI("",UserName,item,"","","");

        // Showing selected spinner item
//        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}