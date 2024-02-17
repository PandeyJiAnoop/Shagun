package com.akp.shagun.RetrofitAPI;
/**
 * Created by Anoop pandey-9696381023.
 */


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GlobalAppApis {
    public String Register(String userid, String name, String email) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("UserId", userid);
            jsonObject1.put("Name", name);
            jsonObject1.put("Email", email);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }

    public String Login(String mobile, String deviceid) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("id", "");
            jsonObject1.put("Password", deviceid);
            jsonObject1.put("Userid", mobile);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }



    public String Operator(String servicename) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("ServiceName", servicename);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }




    public String MembershipCard(String userid) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("UserId", userid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }

    public String OTPVerify(String action, String mobilemo, String otp, String deviceid, String name, String email) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("Action", action);
            jsonObject1.put("MobileNumber", mobilemo);
            jsonObject1.put("Otp", otp);
            jsonObject1.put("DeviceId", deviceid);
            jsonObject1.put("Name", name);
            jsonObject1.put("Email", email);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }

    public String AddInsurance(String checked, String userId, String name, String mobile, String email, String pan, String adhar) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("ID", "");
            jsonObject1.put("UserId", userId);
            jsonObject1.put("Type", checked);
            jsonObject1.put("Name", name);
            jsonObject1.put("Email", email);
            jsonObject1.put("Aadhar", adhar);
            jsonObject1.put("Pan", pan);
            jsonObject1.put("Mobile", mobile);
            jsonObject1.put("ProcMode", "1");


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }

    public String GetInsurance(String userId) {
        JSONObject jsonObject1 = new JSONObject();
        try {

            jsonObject1.put("UserId", userId);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }

    public String Delete(String id, String UserId) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("ID", id);
            jsonObject1.put("UserId", UserId);
            jsonObject1.put("Type", "");
            jsonObject1.put("Name", "");
            jsonObject1.put("Email", "");
            jsonObject1.put("Aadhar", "");
            jsonObject1.put("Pan", "");
            jsonObject1.put("Mobile", "");
            jsonObject1.put("ProcMode", "3");


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }

    public String UpdaeInsurance(String id, String checked, String userId, String name, String mobile, String email, String pan, String adhar) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("ID", id);
            jsonObject1.put("UserId", userId);
            jsonObject1.put("Type", checked);
            jsonObject1.put("Name", name);
            jsonObject1.put("Email", email);
            jsonObject1.put("Aadhar", adhar);
            jsonObject1.put("Pan", pan);
            jsonObject1.put("Mobile", mobile);
            jsonObject1.put("ProcMode", "2");


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }

    public String AddMedicalRecord(String userId, String patientName, String doctorName, String disease, String fees, String status, String checkboxvalue, String encodedImage) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("ID", "");
            jsonObject1.put("UserId", userId);
            jsonObject1.put("PatientName", patientName);
            jsonObject1.put("DoctorName", doctorName);
            jsonObject1.put("Diseacse", disease);
            jsonObject1.put("Fee", fees);
            jsonObject1.put("ProblemType", status);
            jsonObject1.put("ReportType", checkboxvalue);
            jsonObject1.put("File", encodedImage);
            jsonObject1.put("ProcMode", "1");


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }

    public String GetMedicalRecord() {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("ID", "");
            jsonObject1.put("ProcMode", "1");


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }

    public String UpDateMedicalRecord(String id, String userId, String patientName, String doctorName, String disease, String fees, String status, String checkboxvalue, String encodedImage) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("ID", id);
            jsonObject1.put("UserId", userId);
            jsonObject1.put("PatientName", patientName);
            jsonObject1.put("DoctorName", doctorName);
            jsonObject1.put("Diseacse", disease);
            jsonObject1.put("Fee", fees);
            jsonObject1.put("ProblemType", status);
            jsonObject1.put("ReportType", checkboxvalue);
            jsonObject1.put("File", encodedImage);
            jsonObject1.put("ProcMode", "2");


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }


    public String DonationList() {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("ID", "");
            jsonObject1.put("ProcMode", "1");


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }

    public String AddDonation(String userId, String fullname, String age, String adharnumber, String fathername, String mothername, String email, String selectGender, String edBloodDonation, String bloodDonation) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("ID", "");
            jsonObject1.put("UserId", userId);
            jsonObject1.put("OrganName", bloodDonation);
            jsonObject1.put("Name", fullname);
            jsonObject1.put("AadharNo", adharnumber);
            jsonObject1.put("BloodGroup", selectGender);
            jsonObject1.put("Age", age);
            jsonObject1.put("FatherName", fathername);
            jsonObject1.put("MotherName", mothername);
            jsonObject1.put("Email", email);
            jsonObject1.put("ProcMode", "1");
            jsonObject1.put("Gender", edBloodDonation);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }


    public String DeleteDonation(String id) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("ID", id);
            jsonObject1.put("UserId", "");
            jsonObject1.put("OrganName", "");
            jsonObject1.put("Name", "");
            jsonObject1.put("AadharNo", "");
            jsonObject1.put("BloodGroup", "");
            jsonObject1.put("Age", "");
            jsonObject1.put("FatherName", "");
            jsonObject1.put("MotherName", "");
            jsonObject1.put("Email", "");
            jsonObject1.put("ProcMode", "3");
            jsonObject1.put("Gender", "");


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }

    public String UpdateDonation(String userId, String fullname, String age, String adharnumber, String fathername, String mothername, String email, String selectGender, String edBloodDonation, String bloodDonation, String id) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("ID", id);
            jsonObject1.put("UserId", userId);
            jsonObject1.put("OrganName", bloodDonation);
            jsonObject1.put("Name", fullname);
            jsonObject1.put("AadharNo", adharnumber);
            jsonObject1.put("BloodGroup", selectGender);
            jsonObject1.put("Age", age);
            jsonObject1.put("FatherName", fathername);
            jsonObject1.put("MotherName", mothername);
            jsonObject1.put("Email", email);
            jsonObject1.put("ProcMode", "2");
            jsonObject1.put("Gender", edBloodDonation);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }


    public String SpecialistType(String newText, String SpecialistType) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("SpecialistType", SpecialistType);
            jsonObject1.put("HospitalName", newText);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }

    public String MemberBasicdetailadd(String member, String mobile, String fatherhusbend, String registrationdate, String dob, String age, String address, String gender, String branchcity, String UserName) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("Address", address);
            jsonObject1.put("Age", age);
            jsonObject1.put("BranchCode", branchcity);
            jsonObject1.put("DateofBirth", dob);
            jsonObject1.put("FatherHusbandName", fatherhusbend);
            jsonObject1.put("Gender", gender);
            jsonObject1.put("MemberId", "");
            jsonObject1.put("MemberType", "");
            jsonObject1.put("MemberName", member);
            jsonObject1.put("MobileNo", mobile);
            jsonObject1.put("RegistrationDate", registrationdate);
            jsonObject1.put("EntryBy", UserName);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }

    public String CollectorInMemberList(String CollectorId) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("CollectorId", CollectorId);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }

    public String MemberDetailsAPI(String memberid) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("MemberId", memberid);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }



    public String CalculationAddMember(String anual, String installmentMode, String interestCalMethod, String loanProcessing, String loanProccesingFeePer,
                                       String loanSecurity, String loanDuration, String rquiredLoan, String dddLoanType) {
        JSONObject jsonObject1 = new JSONObject();
        try {

            jsonObject1.put("AnnualInterestRate", anual);
            jsonObject1.put("InstallmentMode", installmentMode);
            jsonObject1.put("InterestCalMethod", interestCalMethod);
            jsonObject1.put("LoanProcessingAmt", loanProcessing);
            jsonObject1.put("LoanProcessingPer", loanProccesingFeePer);
            jsonObject1.put("LoanSecurityFee", "0");
            jsonObject1.put("LoanSecurityPer", loanSecurity);
            jsonObject1.put("LoanTenure", loanDuration);
            jsonObject1.put("RequiredLoanAmount", rquiredLoan);
            jsonObject1.put("ddlLoanProcessingType", dddLoanType);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }


    public String MemberLoanpurchase(String ddLoanType, String eligible, String minPlan, String maxLoan, String loanSecurity, String totalIntrest, String interestCalMethod, String annual, String paymentAmount, String loanProcessing, String asstValue, String rquiredLoan, String loanDuration, String assetDescription, String loan, String installmentMode, String loanPlan, String memberId, String loanPlanpurpose, String profarmaNo, String branchCode, String TotalInstallments, double persecur ) {

        JSONObject jsonObject1 = new JSONObject();
        try {


            jsonObject1.put("AnnualInterestRate", annual);
            jsonObject1.put("AssetPropertyDescription", assetDescription);
            jsonObject1.put("AssetValue", asstValue);
            jsonObject1.put("BranchCode", branchCode);
            jsonObject1.put("InstallmentMode", loanPlanpurpose);
            jsonObject1.put("InterestCalMethod", interestCalMethod);
            jsonObject1.put("LoanPlanCode", loanPlan);
            jsonObject1.put("LoanProcessing", loanProcessing);
            jsonObject1.put("LoanTenure", loanDuration);
            jsonObject1.put("RequiredLoanAmount", rquiredLoan);
            jsonObject1.put("MemberId", memberId);
            jsonObject1.put("LoanPurposeId", installmentMode);
            jsonObject1.put("ProfarmaNo", profarmaNo);
            jsonObject1.put("SecurityFeePer", persecur );
            jsonObject1.put("SecurityFeeAmt", loanSecurity );
            jsonObject1.put("TotInterestAmount", totalIntrest);
            jsonObject1.put("TotalInstallments", TotalInstallments);
            jsonObject1.put("TotalRepaymentAmount", paymentAmount);
            jsonObject1.put("LoanPlanTypeId", loan);
            jsonObject1.put("ProccessingFeeCalcType", ddLoanType);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();

    }

    public String Loanrepayment() {
        JSONObject jsonObject1 = new JSONObject();


        return jsonObject1.toString();
    }

    public String GetEmIListbyproformano(String profarmaNo) {
        JSONObject jsonObject1 = new JSONObject();
        try {


            jsonObject1.put("BranchCode", "");
            jsonObject1.put("LoanType", "");
            jsonObject1.put("ProformaNo", profarmaNo);
            jsonObject1.put("mDate", "");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }


    public String Dashboarddetail(String userName, String proformaNo) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("UserName", userName);
            jsonObject1.put("ProformaNo", proformaNo);
            Log.v("DADADADA", String.valueOf(jsonObject1));


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }

    public String proformalist(String userName, String proformaNo) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("UserName", userName);
            jsonObject1.put("ProformaNo", proformaNo);
            Log.v("DADADADA", String.valueOf(jsonObject1));


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }





    public String openpaymentdetail(String advanceAmt, String extraPer, double totalPrice, double lateStatus) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("AdvanceAmt", advanceAmt);
            jsonObject1.put("ExtraPer", extraPer);
            jsonObject1.put("TotPayableAmt", totalPrice);
            jsonObject1.put("TotalLateFee", lateStatus);
            Log.v("DADADADA", String.valueOf(jsonObject1));


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }

    public String PaymentaddEMI(String branchCode, String refrencenumber, String collectionBy, String date, String userName, String extraAmount, String extraPer, String latefee, String loanclose, String paidAmount, String profarmaNo, String remark, String chequeDate, String refrenceId, ArrayList<String> arrRepaymentListt , String selectedName){
    JSONObject jsonObject1 = new JSONObject();
        JSONArray array = new JSONArray();
        try {
            jsonObject1.put("BranchCode", "");
            jsonObject1.put("ChqNo", refrencenumber);
            jsonObject1.put("CollectionBy", "");
            jsonObject1.put("CollectionDate", date);
            jsonObject1.put("EntryBy", userName);
            jsonObject1.put("ExtraAmount", "0");
            jsonObject1.put("ExtraPer", "0");
            jsonObject1.put("LateFee", latefee);
            jsonObject1.put("Loanclose", "0");
            jsonObject1.put("PaidAmount", paidAmount);
            jsonObject1.put("ProfarmaNo", profarmaNo);
            jsonObject1.put("Remark", "");
            jsonObject1.put("eDate", chequeDate);
            jsonObject1.put("txnId", refrenceId);
            jsonObject1.put("PaymentMode", selectedName);
            for (int arrayy = 0; arrayy < arrRepaymentListt.size(); arrayy++) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("EmiAmount", "0");
                jsonObject.put("InstallmentNo", arrRepaymentListt.get(arrayy));
                jsonObject.put("LateFee", "0");
                array.put(jsonObject);
            }
            jsonObject1.put("objemi",array);
            Log.v("DADADADA", String.valueOf(jsonObject1));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }

    public String LoginCustomer(String mobileNo) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("MobileNo", mobileNo);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }
    public String Otpverify(String mobileNo, String otp) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("MobileNo", mobileNo);
            jsonObject1.put("OTP", otp);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }




    public String PostRechargeAPI(String UserId, String amount, String number, String provider_id) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("UserId", UserId);
            jsonObject1.put("amount", amount);
            jsonObject1.put("number", number);
            jsonObject1.put("provider_id", provider_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }


    public String PostGetCustomerAPI(String mob) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("MobileNo", mob);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }




    public String PostAddSenderAPI(String add, String fname, String lname, String mob, String pincode, String state) {
        JSONObject jsonObject1 = new JSONObject();
        try {

            jsonObject1.put("Address", add);
            jsonObject1.put("FirstName", fname);
            jsonObject1.put("LastName", lname);
            jsonObject1.put("MobileNo", mob);
            jsonObject1.put("PinCode", pincode);
            jsonObject1.put("State", state);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }





    public String AddWallet(String Amount, String UserId) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("Amount", Amount);
            jsonObject1.put("UserId", UserId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }



    public String ViewWalletBalance(String UserId) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("UserId", UserId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }


    public String QRGenrate(String mobno) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("Userid", mobno);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }



    public String TransactionReportHistory(String fromdate, String memberid, String reporttype, String status, String todate, String transactiontype) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("FromDate", fromdate);
            jsonObject1.put("MemberId", memberid);
            jsonObject1.put("ReportType", reporttype);
            jsonObject1.put("Status", status);
            jsonObject1.put("ToDate", todate);
            jsonObject1.put("TransactionType", transactiontype);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }


    public String WalletHistory(String UserId) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("UserId", UserId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }




    public String CustomerDashboard(String proformaNo, String branchCode, String memberId) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("BranchCode", "");
            jsonObject1.put("MemberId", memberId);
            jsonObject1.put("ProformaNo", proformaNo);
            Log.v("DADADADA", String.valueOf(jsonObject1));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }
    public String proformanobymemberid(String memberId) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("BranchCode", "");
            jsonObject1.put("MemberId", memberId);
            jsonObject1.put("ProformaNo", "");
            Log.v("DADADADA", String.valueOf(jsonObject1));


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }

    public String CustomerLoanList(String MemberId) {
        JSONObject jsonObject1 = new JSONObject();
        try {

            jsonObject1.put("MemberId", MemberId);

            Log.v("DADADADA", String.valueOf(jsonObject1));


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }

    public String CustomerPaidEMI(String MemberId, String Profarma) {
        JSONObject jsonObject1 = new JSONObject();
        try {

            jsonObject1.put("MemberId", MemberId);
            jsonObject1.put("ProfarmaNo", Profarma);

            Log.v("DADADADA", String.valueOf(jsonObject1));


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }

    public String CustomerUnPaidEMI(String MemberId, String Profarma) {
        JSONObject jsonObject1 = new JSONObject();
        try {

            jsonObject1.put("MemberId", MemberId);
            jsonObject1.put("ProfarmaNo", Profarma);

            Log.v("DADADADA", String.valueOf(jsonObject1));


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }

    public String CustomerProfile(String MemberId) {
        JSONObject jsonObject1 = new JSONObject();
        try {

            jsonObject1.put("MemberId", MemberId);

            Log.v("DADADADA", String.valueOf(jsonObject1));


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }

    public String MemberBasicdetailadd(String member, String mobile, String fatherhusbend, String registrationdate, String dob, String age, String address, String securityamount, String gender, String branchcity, String UserName) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("LoanSecurityAmt", securityamount);
            jsonObject1.put("Address", address);
            jsonObject1.put("Age", age);
            jsonObject1.put("BranchCode", branchcity);
            jsonObject1.put("DateofBirth", dob);
            jsonObject1.put("FatherHusbandName", fatherhusbend);
            jsonObject1.put("Gender", gender);
            jsonObject1.put("MemberId", "");
            jsonObject1.put("MemberType", "");
            jsonObject1.put("MemberName", member);
            jsonObject1.put("MobileNo", mobile);
            jsonObject1.put("RegistrationDate", registrationdate);
            jsonObject1.put("EntryBy", UserName);



        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }
    public String MembershipMemberdetail(String CollectorId) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("CollectorId", CollectorId);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }

    public String CollectSecurityamtlist(String MemberId) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("MemberId", MemberId);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }


    public String GetRenewalHistory(String MemberId) {
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("UserId", MemberId);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject1.toString();
    }
}
