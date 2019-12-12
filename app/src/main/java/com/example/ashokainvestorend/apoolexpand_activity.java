package com.example.ashokainvestorend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class apoolexpand_activity extends AppCompatActivity {

    private TextView report;
    private Toolbar toolbar;
    String temp="";
    String timedate="";
    private TextView poolname,poollocation,poolprofit,pooltotinvest,pooldescript;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apoolexpand_activity);
        report=findViewById(R.id.apreporttxt);
        poolname=findViewById(R.id.apoolname);
        poollocation=findViewById(R.id.apoollocatxt);
        pooltotinvest=findViewById(R.id.aptotalinvested);
        pooldescript=findViewById(R.id.appooldescrp);
        poolprofit=findViewById(R.id.approfittxt);
        SharedPreferences sharedPreferences=getSharedPreferences("Secrets",MODE_PRIVATE);
        String currentusername=sharedPreferences.getString("username","");
        String currentemail=sharedPreferences.getString("email","");
        String currentph=sharedPreferences.getString("phone","");
        String currentaadhar=sharedPreferences.getString("aadhar","");
        String currenttoken=sharedPreferences.getString("token","");


        toolbar=findViewById(R.id.apooltoolbar);
        Intent intent=getIntent();
        String selectedpoolid=intent.getStringArrayExtra("ID_EXTRA")[0];
        String selectedpoolname=intent.getStringArrayExtra("ID_EXTRA")[1];
        String selectedpoollocation=intent.getStringArrayExtra("ID_EXTRA")[2];
        String selectedpoolinvestments=intent.getStringArrayExtra("ID_EXTRA")[3];
        String selectedpoolprofit=intent.getStringArrayExtra("ID_EXTRA")[4];



        //setting some parameters from previous intent

        poolname.setText(selectedpoolname);
        poollocation.setText(selectedpoollocation);
        pooltotinvest.setText(selectedpoolinvestments);
        poolprofit.setText(selectedpoolprofit);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //for vertical scrolling method
        report.setMovementMethod(new ScrollingMovementMethod());


        //for report and description
        Retrofit.Builder builder=new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5000/")//change it afterwards when everthing is hosted
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit=builder.build();
        ApiInterface apiInterface=retrofit.create(ApiInterface.class);
        Call<Particularpoolinfogetformat> call=apiInterface.getpooldetails(currenttoken,selectedpoolid);
        call.enqueue(new Callback<Particularpoolinfogetformat>() {
            @Override
            public void onResponse(Call<Particularpoolinfogetformat> call, Response<Particularpoolinfogetformat> response) {
                if(response.isSuccessful())
                {
                    //setting description for a pool
                    String descript=response.body().getPool().getDescription();
                    pooldescript.setText(descript);


                    List<Report> repolist=response.body().getReports();
                    for(int i=(repolist.size()-1);i>=0;i--)
                    {
                        double time=repolist.get(i).getTimestamp();
                        Date date = new Date((long) time);
                        timedate= String.valueOf(date);

                        temp+=timedate+"\nTitle:"+repolist.get(i).getTitle()+"\n"+"Description:"+repolist.get(i).getDescription()+"\n"+"\n"+"\n";
                        report.append(temp);
                        temp="";
                        timedate="";
                    }

                }
                else
                {
                    Toast.makeText(apoolexpand_activity.this, "Error:"+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Particularpoolinfogetformat> call, Throwable t) {
                Toast.makeText(apoolexpand_activity.this, "Error:"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });






    }


    //after clicking investment button
    public void investclick(View view) {
        Intent intent=getIntent();
        String selectedpoolid=intent.getStringArrayExtra("ID_EXTRA")[0];
        String selectedpoolname=intent.getStringArrayExtra("ID_EXTRA")[1];
        String selectedpoollocation=intent.getStringArrayExtra("ID_EXTRA")[2];
        String selectedpoolinvestments=intent.getStringArrayExtra("ID_EXTRA")[3];
        String selectedpoolprofit=intent.getStringArrayExtra("ID_EXTRA")[4];

        //redirect to pay page
        Intent i=new Intent(apoolexpand_activity.this, paymentpage.class);
        i.putExtra("poolid",selectedpoolid);
        startActivity(i);



    }
}
