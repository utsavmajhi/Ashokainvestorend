package com.example.ashokainvestorend;

import com.example.ashokainvestorend.allpoolrecyclerdata.Getallpoolsformat;
import com.example.ashokainvestorend.investedpoolrecyclerdata.Getinvestedpoolformat;
import com.example.ashokainvestorend.loginmodels.Getlogindataformat;
import com.example.ashokainvestorend.loginmodels.Sendlogindataformat;
import com.example.ashokainvestorend.particulartransactionmodels.Particulartransacpoolgetformat;
import com.example.ashokainvestorend.paymentspagemodels.Paymentgetformat;
import com.example.ashokainvestorend.paymentspagemodels.Paymentsendformat;
import com.example.ashokainvestorend.registrationmodel.Registgetformat;
import com.example.ashokainvestorend.registrationmodel.Registsendformat;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

    //login credentials
    @POST("users/signin")
    Call<Getlogindataformat> getlogindata(@Body Sendlogindataformat sendlogindataformat);

    //registration
    @POST("users/signup")
    Call<Registgetformat> sendregiscredentials(@Body Registsendformat registsendformat);

    //get pools which he had already invested
    @GET("investors/pools")
    Call<Getinvestedpoolformat> getinvestedpools(@Header("Authorization") String header);

    //get all pools
    @GET("admins/pools")
    Call<Getallpoolsformat> getallpools(@Header("Authorization") String header);

    //investing money to a pool
    @POST("investors/invest")
    Call<Paymentgetformat> sendpaymentdet(@Header("Authorization") String header, @Body Paymentsendformat paymentsendformat);

    //get info for a particular pool
    @GET("admins/pool/{poolid}")
    Call<Particularpoolinfogetformat> getpooldetails(@Header("Authorization") String header, @Path("poolid") String poolid);

    //get transaction for a particular pool
    @GET("investors/getinvestorsinvestmentsonapool/{poolid}")
    Call<Particulartransacpoolgetformat> getpooltransaction(@Header("Authorization") String header, @Path("poolid") String poolid);


    //get all transactions for all pools till now
    @GET("investors/getinvestorsinvestments")
    Call<Alltransactionpoolgetformat> getalltransaction(@Header("Authorization") String header);



}
