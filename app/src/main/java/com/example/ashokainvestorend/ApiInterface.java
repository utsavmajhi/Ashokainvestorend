package com.example.ashokainvestorend;

import com.example.ashokainvestorend.investedpoolrecyclerdata.Getinvestedpoolformat;
import com.example.ashokainvestorend.loginmodels.Getlogindataformat;
import com.example.ashokainvestorend.loginmodels.Sendlogindataformat;
import com.example.ashokainvestorend.registrationmodel.Registgetformat;
import com.example.ashokainvestorend.registrationmodel.Registsendformat;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

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
}
