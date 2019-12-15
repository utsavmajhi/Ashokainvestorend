package com.example.ashokainvestorend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class alltransactionpage extends AppCompatActivity {

    private Toolbar toolbar;

    String timedate="";
    double amt=0;
    private RecyclerView mRecyclerView;
    private alltransactionadapter mtranAdapter;
    private ArrayList<alltransactionitem> malltranlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alltransactionpage);
        mRecyclerView=findViewById(R.id.alltransacrecycler);
        malltranlist=new ArrayList<>();
        toolbar=findViewById(R.id.alltrantoolbar);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        alltransactions();
    }

    private void alltransactions() {

        SharedPreferences sharedPreferences=getSharedPreferences("Secrets",MODE_PRIVATE);
        String currenttoken=sharedPreferences.getString("token","");


        //backend starts
        Retrofit.Builder builder=new Retrofit.Builder()
                .baseUrl("https://ashokabackend.herokuapp.com/")//change it afterwards when everthing is hosted
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit=builder.build();
        ApiInterface apiInterface=retrofit.create(ApiInterface.class);
        Call<Alltransactionpoolgetformat> call=apiInterface.getalltransaction(currenttoken);
        call.enqueue(new Callback<Alltransactionpoolgetformat>() {
            @Override
            public void onResponse(Call<Alltransactionpoolgetformat> call, Response<Alltransactionpoolgetformat> response) {
                if(response.isSuccessful())
                {

                    List<Investment> allinv=response.body().getInvestments();
                    for(int i=(allinv.size()-1);i>=0;i--)
                    {
                        double time=allinv.get(i).getTimestamp();
                        Date date = new Date((long) time);
                        timedate= String.valueOf(date);
                        amt=allinv.get(i).getAmount();
                        String a= String.valueOf(amt);
                        malltranlist.add(new alltransactionitem(timedate,a));
                        timedate="";
                        amt=0;
                    }
                    mtranAdapter=new alltransactionadapter(alltransactionpage.this,malltranlist);
                    mRecyclerView.setAdapter(mtranAdapter);

                }
                else
                {
                    Toast.makeText(alltransactionpage.this, "Error:"+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Alltransactionpoolgetformat> call, Throwable t) {
                Toast.makeText(alltransactionpage.this, "Error:"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
