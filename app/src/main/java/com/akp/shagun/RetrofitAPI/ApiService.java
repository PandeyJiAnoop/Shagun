package com.akp.shagun.RetrofitAPI;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {

    @Headers("Content-Type: application/json")
    @GET("Versionlistapp")
    Call<String> Versionlistapp();


    @Headers("Content-Type: application/json")
    @GET("GetServiceList")
    Call<String> OperatorList();



    @Headers("Content-Type: application/json")
    @POST("LoginService")
    Call<String> LoginService(
            @Body String body);



    @Headers("Content-Type: application/json")
    @POST("GetOperatorList")
    Call<String> OperatorService(
            @Body String body);




    @Headers("Content-Type: application/json")
    @POST("MemberBasicdetailadd")
    Call<String> MemberBasicdetailadd(
            @Body String body);

    @Headers("Content-Type: application/json")
    @POST("CollectorInMemberList")
    Call<String> CollectorInMemberList(
            @Body String body);

    @Headers("Content-Type: application/json")
    @POST("CalculationAddMember")
    Call<String> CalculationAddMember(
            @Body String body);

    @Headers("Content-Type: application/json")
    @POST("MemberLoanpurchase")
    Call<String> MemberLoanpurchase(
            @Body String body);

    @Headers("Content-Type: application/json")
    @POST("Memberdetailbymemberid")
    Call<String> MemberBasicDetails(
            @Body String body);

    @Headers("Content-Type: application/json")
    @POST("Loanrepayment")
    Call<String> Loanrepayment(
            @Body String body);

    @Headers("Content-Type: application/json")
    @POST("GetEmIListbyproformano")
    Call<String> GetEmIListbyproformano(
            @Body String body);

    @Headers("Content-Type: application/json")
    @POST("Dashboarddetail")
    Call<String> Dashboarddetail(
            @Body String body);

    @Headers("Content-Type: application/json")
    @POST("proformalist")
    Call<String> proformalist(
            @Body String body);

    @Headers("Content-Type: application/json")
    @POST("openpaymentdetail")
    Call<String> openpaymentdetail(
            @Body String body);

    @Headers("Content-Type: application/json")
    @POST("PaymentaddEMI")
    Call<String> PaymentaddEMI(
            @Body String body);

    @Headers("Content-Type: application/json")
    @POST("LoginCustomer")
    Call<String> LoginCustomer(
            @Body String body);

    @Headers("Content-Type: application/json")
    @POST("Otpverify")
    Call<String> Otpverify(
            @Body String body);


    @Headers("Content-Type: application/json")
    @POST("PostRecharge")
    Call<String> PostRecharge(
            @Body String body);




    @Headers("Content-Type: application/json")
    @POST("GetCustomer")
    Call<String> GetCustomerAPI(
            @Body String body);


    @Headers("Content-Type: application/json")
    @POST("AddSender")
    Call<String> AddSenderAPI(
            @Body String body);




    @Headers("Content-Type: application/json")
    @POST("AddWalletAmount")
    Call<String> AddWalletAPI(
            @Body String body);

    @Headers("Content-Type: application/json")
    @POST("GetWalletBalance")
    Call<String> ViewWalletAPI(
            @Body String body);


    @Headers("Content-Type: application/json")
    @POST("QRValue")
    Call<String> GenrateQRAPI(
            @Body String body);


    @Headers("Content-Type: application/json")
    @POST("GetTransactionHistory")
    Call<String> WalletHistoryAPI(
            @Body String body);

    @Headers("Content-Type: application/json")
    @POST("TransactionReport")
    Call<String> TransactionReportAPI(
            @Body String body);

    @Headers("Content-Type: application/json")
    @POST("proformanobymemberid")
    Call<String> proformanobymemberid(
            @Body String body);

    @Headers("Content-Type: application/json")
    @POST("CustomerDashboard")
    Call<String> CustomerDashboard(
            @Body String body);

    @Headers("Content-Type: application/json")
    @POST("CustomerLoanList")
    Call<String> CustomerLoanList(
            @Body String body);

    @Headers("Content-Type: application/json")
    @POST("CustomerPaidEMI")
    Call<String> CustomerPaidEMI(
            @Body String body);

    @Headers("Content-Type: application/json")
    @POST("CustomerUnPaidEMI")
    Call<String> CustomerUnPaidEMI(
            @Body String body);

    @Headers("Content-Type: application/json")
    @POST("CustomerProfile")
    Call<String> CustomerProfile(
            @Body String body);

    @Headers("Content-Type: application/json")
    @POST("MembershipMemberBasicdetailadd")
    Call<String> MembershipMemberBasicdetailadd(
            @Body String body);
    @Headers("Content-Type: application/json")
    @POST("MembershipMemberdetail")
    Call<String> MembershipMemberdetail(
            @Body String body);

    @Headers("Content-Type: application/json")
    @POST("CollectSecurityamtlist")
    Call<String> CollectSecurityamtlist(
            @Body String body);


    @Headers("Content-Type: application/json")
    @POST("GetRenewalHistory")
    Call<String> GetRenewalHistory(
            @Body String body);
}



