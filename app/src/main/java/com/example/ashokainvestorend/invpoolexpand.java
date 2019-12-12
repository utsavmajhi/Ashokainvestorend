package com.example.ashokainvestorend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ashokainvestorend.particulartransactionmodels.Investment;
import com.example.ashokainvestorend.particulartransactionmodels.Particulartransacpoolgetformat;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class invpoolexpand extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView plname,pllocation,plprofit,pltotinvest,plreport,plinvestyou,plarea;
    String temp="";
    String timedate="";
    double a=0;
    double totalamt=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invpoolexpand);

        plname=findViewById(R.id.inexpoolname);
        pllocation=findViewById(R.id.inexlocal);
        plprofit=findViewById(R.id.inexprofits);
        pltotinvest=findViewById(R.id.inextotinvest);
        plinvestyou=findViewById(R.id.inexowninv);
        plreport=findViewById(R.id.inexreport);
        plarea=findViewById(R.id.inexarea);





        Intent intent=getIntent();
        String poolid=intent.getStringArrayExtra("ID_EXTRA")[0];
        String poolname=intent.getStringArrayExtra("ID_EXTRA")[1];
        String poollocation=intent.getStringArrayExtra("ID_EXTRA")[2];
        String poolreport=intent.getStringArrayExtra("ID_EXTRA")[3];
        String pooltotinvest=intent.getStringArrayExtra("ID_EXTRA")[4];
        String poolprofit=intent.getStringArrayExtra("ID_EXTRA")[5];

        //setting parameters

        plname.setText(poolname);
        pllocation.setText(poollocation);
        plprofit.setText("Rs "+poolprofit);
        pltotinvest.setText("Rs "+pooltotinvest);

        toolbar=findViewById(R.id.invpooltoolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getreport();

        //remeber it gives the error for getinvestmentsonapool route due to get 2body format
        gettotalinvestmentsonpool();
    }

    private void gettotalinvestmentsonpool() {

        SharedPreferences sharedPreferences=getSharedPreferences("Secrets",MODE_PRIVATE);
        String currentusername=sharedPreferences.getString("username","");
        String currentemail=sharedPreferences.getString("email","");
        String currentph=sharedPreferences.getString("phone","");
        String currentaadhar=sharedPreferences.getString("aadhar","");
        String currenttoken=sharedPreferences.getString("token","");
        Intent intent=getIntent();
        String selectedpoolid=intent.getStringArrayExtra("ID_EXTRA")[0];

        Retrofit.Builder builder=new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5000/")//change it afterwards when everthing is hosted
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit=builder.build();
        ApiInterface apiInterface=retrofit.create(ApiInterface.class);
        Call<Particulartransacpoolgetformat> call=apiInterface.getpooltransaction(currenttoken,selectedpoolid);
        call.enqueue(new Callback<Particulartransacpoolgetformat>() {
            @Override
            public void onResponse(Call<Particulartransacpoolgetformat> call, Response<Particulartransacpoolgetformat> response) {
                if(response.isSuccessful())
                {
                    List<Investment> inpoollist=response.body().getInvestments();
                    for(int i=0;i<inpoollist.size();i++)
                    {
                        a+=inpoollist.get(i).getAmount();
                    }
                    totalamt=a;//total ivestments on this pool
                    String k= String.valueOf(totalamt);
                    plinvestyou.setText("Rs "+k);

                }
                else
                {
                    Toast.makeText(invpoolexpand.this, "Error:"+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Particulartransacpoolgetformat> call, Throwable t) {
                Toast.makeText(invpoolexpand.this, "Error:"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void getreport() {

        SharedPreferences sharedPreferences=getSharedPreferences("Secrets",MODE_PRIVATE);
        String currentusername=sharedPreferences.getString("username","");
        String currentemail=sharedPreferences.getString("email","");
        String currentph=sharedPreferences.getString("phone","");
        String currentaadhar=sharedPreferences.getString("aadhar","");
        String currenttoken=sharedPreferences.getString("token","");
        Intent intent=getIntent();
        String selectedpoolid=intent.getStringArrayExtra("ID_EXTRA")[0];

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
                    List<Report> repolist=response.body().getReports();
                    for(int i=(repolist.size()-1);i>=0;i--)
                    {
                        double time=repolist.get(i).getTimestamp();
                        Date date = new Date((long) time);
                        timedate= String.valueOf(date);

                        temp+=timedate+"\nTitle:"+repolist.get(i).getTitle()+"\n"+"Description:"+repolist.get(i).getDescription()+"\n"+"\n"+"\n";
                        plreport.append(temp);
                        temp="";
                        timedate="";
                    }

                }
                else
                {
                    Toast.makeText(invpoolexpand.this, "Error:"+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Particularpoolinfogetformat> call, Throwable t) {
                Toast.makeText(invpoolexpand.this, "Error:"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }


    //invest money
    public void investclickalready(View view) {
        Intent intent=getIntent();
        String poolid=intent.getStringArrayExtra("ID_EXTRA")[0];

        Intent i=new Intent(invpoolexpand.this,paymentpage.class);
        i.putExtra("poolid",poolid);
        startActivity(i);
        finish();

    }


    //More details clicking activity
    public void fulltrandetailsclick(View view)
    {
        Intent intent=getIntent();
        String poolid=intent.getStringArrayExtra("ID_EXTRA")[0];

        Intent i=new Intent(invpoolexpand.this, particularpalltransa.class);
        i.putExtra("poolid",poolid);
        startActivity(i);



    }

    //more details clicking activity ends
}
