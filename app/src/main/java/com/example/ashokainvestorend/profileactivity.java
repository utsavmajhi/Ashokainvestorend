package com.example.ashokainvestorend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class profileactivity extends AppCompatActivity {


    Toolbar toolbar;
    private TextView profilename,profileemail,profilephn,profileaadhar,profiletotinvest,pronofinvetpools,proreturns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profileactivity);
        toolbar=findViewById(R.id.protoolbar);


        profilename=findViewById(R.id.proname);
        profileemail=findViewById(R.id.proemail);
        profileaadhar=findViewById(R.id.proaadhar);
        profilephn=findViewById(R.id.prophn);
        pronofinvetpools=findViewById(R.id.pronumpools);
        proreturns=findViewById(R.id.proreturns);
        profiletotinvest=findViewById(R.id.prototalinvests);

        SharedPreferences sharedPreferences=getSharedPreferences("Secrets",MODE_PRIVATE);
        String currentusername=sharedPreferences.getString("username","");
        String currentemail=sharedPreferences.getString("email","");
        String currentph=sharedPreferences.getString("phone","");
        String currentaadhar=sharedPreferences.getString("aadhar","");
        String currenttoken=sharedPreferences.getString("token","");
        String currentnofpoolinvested=sharedPreferences.getString("noofinvestedpools","");
        String currenttotalinvestments=sharedPreferences.getString("totalinvestmentstillnow","");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //setting some values from shared preferenes
        profilename.setText(currentusername);
        profileemail.setText(currentemail);
        profileaadhar.setText(currentaadhar);
        profilephn.setText(currentph);
        profiletotinvest.setText("Rs "+currenttotalinvestments);
        pronofinvetpools.setText(currentnofpoolinvested);


    }
}
