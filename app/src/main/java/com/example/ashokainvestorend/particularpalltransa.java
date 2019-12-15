package com.example.ashokainvestorend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ashokainvestorend.particulartransactionmodels.Investment;
import com.example.ashokainvestorend.particulartransactionmodels.Particulartransacpoolgetformat;
import com.example.ashokainvestorend.particulartransactionmodels.transactionAdapter;
import com.example.ashokainvestorend.particulartransactionmodels.transactionitem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class particularpalltransa extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView mRecyclerView;
    private transactionAdapter mtranAdapter;
    private ArrayList<transactionitem> mtranlist;
    String timedate="";
    double amt=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_particularpalltransa);
        mRecyclerView=findViewById(R.id.particularrecycler);
        mtranlist=new ArrayList<>();
        toolbar=findViewById(R.id.ptrantoolbar);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        transactionsforapool();





    }

    private void transactionsforapool() {

        SharedPreferences sharedPreferences=getSharedPreferences("Secrets",MODE_PRIVATE);
        String currenttoken=sharedPreferences.getString("token","");

        Intent intent=getIntent();
        String poolid=intent.getStringExtra("poolid");



        Retrofit.Builder builder=new Retrofit.Builder()
                .baseUrl("https://ashokabackend.herokuapp.com/")//change it afterwards when everthing is hosted
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit=builder.build();
        ApiInterface apiInterface=retrofit.create(ApiInterface.class);
        Call<Particulartransacpoolgetformat> call=apiInterface.getpooltransaction(currenttoken,poolid);
        call.enqueue(new Callback<Particulartransacpoolgetformat>() {
            @Override
            public void onResponse(Call<Particulartransacpoolgetformat> call, Response<Particulartransacpoolgetformat> response) {
                if(response.isSuccessful())
                {
                    List<Investment> investslist=response.body().getInvestments();
                    for(int i=(investslist.size()-1);i>=0;i--)
                    {
                        double time=investslist.get(i).getTimestamp();
                        Date date = new Date((long) time);
                        timedate= String.valueOf(date);
                        amt=investslist.get(i).getAmount();
                        String a= String.valueOf(amt);

                        mtranlist.add(new transactionitem(timedate,a));
                        timedate="";
                        amt=0;
                    }
                    mtranAdapter=new transactionAdapter(particularpalltransa.this,mtranlist);
                    mRecyclerView.setAdapter(mtranAdapter);


                }
                else
                {
                    Toast.makeText(particularpalltransa.this, "Error:"+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Particulartransacpoolgetformat> call, Throwable t) {
                Toast.makeText(particularpalltransa.this, "Error:"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}
