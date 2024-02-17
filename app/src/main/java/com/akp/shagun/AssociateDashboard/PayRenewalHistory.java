package com.akp.shagun.AssociateDashboard;

import static com.akp.shagun.RetrofitAPI.API_Config.getApiClient_ByPost;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.akp.shagun.Api_Urls;
import com.akp.shagun.R;
import com.akp.shagun.RetrofitAPI.ApiService;
import com.akp.shagun.RetrofitAPI.ConnectToRetrofit;
import com.akp.shagun.RetrofitAPI.GlobalAppApis;
import com.akp.shagun.RetrofitAPI.RetrofitCallBackListenar;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

public class PayRenewalHistory extends AppCompatActivity {
    ImageView menuImg;
    RecyclerView history_rec;
    String UserId;
    ArrayList<HashMap<String, String>> arrayList2 = new ArrayList<>();
    ImageView norecord_img;
    TextView title_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_renewal_history);
        FindId();
        History();
    }

    private void FindId() {
        SharedPreferences sharedPreferences = getSharedPreferences("login_preference", MODE_PRIVATE);
        UserId = sharedPreferences.getString("userid", "");
        Log.d("Userid", UserId);

        title_tv=findViewById(R.id.title_tv);
        norecord_img=findViewById(R.id.norecord_img);
        menuImg = findViewById(R.id.menuImg);
        history_rec = findViewById(R.id.history_rec);
        menuImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void History() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Api_Urls.BaseURL+"GetRenewalHistory", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                String jsonString = response;
                jsonString = jsonString.replace("<?xml version=\"1.0\" encoding=\"utf-8\"?>", " ");
                jsonString = jsonString.replace("<string xmlns=\"http://tempuri.org/\">", " ");
                jsonString = jsonString.replace("</string>", " ");
                Log.d("PayRenewalhistoryakp",jsonString);
                try {
                    JSONObject jsonObject = new JSONObject(jsonString);
                    if(jsonObject.getString("status").equals("false")){
                        Toast.makeText(getApplicationContext(),jsonObject.getString("Msg"),Toast.LENGTH_LONG).show();
                    }
                    else{
                        JSONArray jsonArray = jsonObject.getJSONArray("Response");
                        Log.d("jsonString",""+jsonArray);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            HashMap<String, String> hm = new HashMap<>();
                            hm.put("Amount", jsonObject1.getString("Amount"));
                            hm.put("AccountNo", jsonObject1.getString("AccountNo"));
                            hm.put("Date", jsonObject1.getString("Date"));
                            hm.put("MemberId", jsonObject1.getString("MemberId"));
                            hm.put("PlanCode", jsonObject1.getString("PlanCode"));
                            hm.put("Remark", jsonObject1.getString("Remark"));
                            hm.put("MemberName", jsonObject1.getString("MemberName"));
                            arrayList2.add(hm);
                        }
                        LinearLayoutManager HorizontalLayout1 = new LinearLayoutManager(PayRenewalHistory.this, LinearLayoutManager.VERTICAL, false);
                        PayRenewalHistory.DasAdapter customerListAdapter = new PayRenewalHistory.DasAdapter(PayRenewalHistory.this, arrayList2);
                        history_rec.setLayoutManager(HorizontalLayout1);
                        history_rec.setAdapter(customerListAdapter);
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
                Toast.makeText(PayRenewalHistory.this, "Something went wrong:-" + error, Toast.LENGTH_SHORT).show();
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
        RequestQueue requestQueue = Volley.newRequestQueue(PayRenewalHistory.this);
        requestQueue.add(stringRequest);
    }


    public class DasAdapter extends RecyclerView.Adapter<PayRenewalHistory.DasAdapter.VH> {
        Context context;
        List<HashMap<String,String>> arrayList;
        public DasAdapter(Context context, List<HashMap<String,String>> arrayList) {
            this.arrayList=arrayList;
            this.context=context;
        }
        @NonNull
        @Override
        public PayRenewalHistory.DasAdapter.VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(context).inflate(R.layout.dynamic_payrenewalhistory, viewGroup, false);
            PayRenewalHistory.DasAdapter.VH viewHolder = new PayRenewalHistory.DasAdapter.VH(view);
            return viewHolder;
        }
        @Override
        public void onBindViewHolder(@NonNull PayRenewalHistory.DasAdapter.VH vh, int i) {
            vh.tv.setText(String.valueOf(i + 1));
            vh.tv1.setText(arrayList.get(i).get("Amount"));
            vh.tv2.setText(arrayList.get(i).get("AccountNo"));
            vh.tv3.setText(arrayList.get(i).get("Date"));
            vh.tv4.setText(arrayList.get(i).get("MemberId"));

            vh.tv5.setText(arrayList.get(i).get("MemberName"));
            vh.tv6.setText(arrayList.get(i).get("PlanCode"));
            vh.tv7.setText(arrayList.get(i).get("Remark"));




        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        public class VH extends RecyclerView.ViewHolder {
            TextView tv,tv1,tv2,tv3,tv4,tv5,tv6,tv7;
            public VH(@NonNull View itemView) {
                super(itemView);
                tv = itemView.findViewById(R.id.tv);
                tv1 = itemView.findViewById(R.id.tv1);
                tv2 = itemView.findViewById(R.id.tv2);
                tv3 = itemView.findViewById(R.id.tv3);
                tv4 = itemView.findViewById(R.id.tv4);
                tv5 = itemView.findViewById(R.id.tv5);
                tv6 = itemView.findViewById(R.id.tv6);
                tv7 = itemView.findViewById(R.id.tv7);
            } }}
}