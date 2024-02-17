package com.akp.shagun.Mobile;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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

import retrofit2.Call;

import static com.akp.shagun.RetrofitAPI.API_Config.getApiClient_ByPost;


public class GetCategoryDetails extends AppCompatActivity {

    ImageView ivBack;
    RecyclerView cust_recyclerView;
    ArrayList<HashMap<String, String>> arrayList1 = new ArrayList<>();
    String getServicename;

    TextView title_tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_category_details);
        getServicename=getIntent().getStringExtra("service_name");

        title_tv=findViewById(R.id.title_tv);

        title_tv.setText(getServicename+" Services");


        ivBack=findViewById(R.id.ivBack);
        cust_recyclerView = findViewById(R.id.cust_recyclerView);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getHistory(getServicename);
    }


    public void  getHistory(String service){
        String otp1 = new GlobalAppApis().Operator(service);
        ApiService client1 = getApiClient_ByPost();
        Call<String> call1 = client1.OperatorService(otp1);
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
                        hm.put("provider_Name", jsonObject1.getString("provider_Name"));
                        hm.put("provider_icon", jsonObject1.getString("provider_icon"));
                        hm.put("provider_id", jsonObject1.getString("provider_id"));
                        hm.put("service_id", jsonObject1.getString("service_id"));
                        hm.put("service_name", jsonObject1.getString("service_name"));
                        arrayList1.add(hm);
                    }
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(GetCategoryDetails.this, 3);
                    CategoryServiceListAdapter customerListAdapter = new CategoryServiceListAdapter(GetCategoryDetails.this, arrayList1);
                    cust_recyclerView.setLayoutManager(gridLayoutManager);
                    cust_recyclerView.setAdapter(customerListAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, GetCategoryDetails.this, call1, "", true);
    }

}