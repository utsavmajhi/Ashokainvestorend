package com.example.ashokainvestorend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ashokainvestorend.registrationmodel.Registgetformat;
import com.example.ashokainvestorend.registrationmodel.Registsendformat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class registeringuser extends AppCompatActivity {


    private TextView rname;
    private TextView rpass;
    private TextView raadhar;
    private TextView rphone;
    private TextView remail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeringuser);
        rname=findViewById(R.id.regisname);
        rpass=findViewById(R.id.regispass);
        raadhar=findViewById(R.id.regisaadhar);
        rphone=findViewById(R.id.regisphone);
        remail=findViewById(R.id.regisemail);
    }



    //for registeration activity
    public void regisclick(View view) {

        String n1=rname.getText().toString();
        String p1=rpass.getText().toString();
        String aa1=raadhar.getText().toString();
        String ph1=rphone.getText().toString();
        String em1=remail.getText().toString();
        if(n1.isEmpty()||p1.isEmpty()||aa1.isEmpty()||ph1.isEmpty()||em1.isEmpty())
        {
            Toast.makeText(this, "All fields are Mandatory", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Registering Please Wait!", Toast.LENGTH_SHORT).show();
           //Backend

            Retrofit.Builder builder=new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:5000/")//change it afterwards when everthing is hosted
                    .addConverterFactory(GsonConverterFactory.create());
            Retrofit retrofit=builder.build();
            ApiInterface apiInterface=retrofit.create(ApiInterface.class);


            Registsendformat regis=new Registsendformat(em1,p1,n1,aa1,3,ph1);
            Call<Registgetformat> call=apiInterface.sendregiscredentials(regis);
            call.enqueue(new Callback<Registgetformat>() {
                @Override
                public void onResponse(Call<Registgetformat> call, Response<Registgetformat> response) {
                    if(response.isSuccessful())
                    {
                        assert response.body() != null;
                        String token=response.body().getToken();
                        String username=response.body().getUser().getName();
                        String aadhar=response.body().getUser().getAadhaar();
                        String email=response.body().getUser().getEmail();
                        String phone=response.body().getUser().getPhone();
                        String id=response.body().getUser().getId();


                        SharedPreferences sharedPreferences=getSharedPreferences("Secrets",MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedPreferences.edit();

                        editor.putString("token",token);
                        editor.putString("username",username);
                        editor.putString("aadhar",aadhar);
                        editor.putString("email",email);
                        editor.putString("phone",phone);
                        editor.putString("id",id);
                        editor.apply();
                        Toast.makeText(registeringuser.this, "Successfully created your account", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(registeringuser.this,homepage.class));
                        finish();

                    }
                    else
                    {
                        Toast.makeText(registeringuser.this, "Error:"+response.message(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Registgetformat> call, Throwable t) {
                    Toast.makeText(registeringuser.this, "Error:"+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            //Backend




        }
    }

}
