package com.example.ashokainvestorend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ashokainvestorend.paymentspagemodels.Paymentgetformat;
import com.example.ashokainvestorend.paymentspagemodels.Paymentsendformat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class paymentpage extends AppCompatActivity {
    private Toolbar toolbar;
    private EditText amountinvest;
    private EditText email,cardname,expiry,cvvcode,cardno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentpage);
        toolbar=findViewById(R.id.paytoolbar);
        amountinvest=findViewById(R.id.amtinvested);
        email=findViewById(R.id.em);
        cardname=findViewById(R.id.nc);
        expiry=findViewById(R.id.ed);
        cvvcode=findViewById(R.id.cvv);
        cardno=findViewById(R.id.cm);



        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




    }

    public void payclick(View view) {
        int myamt;
        String cardn=cardname.getText().toString();
        String cardnum=cardno.getText().toString();
        String cardemail=email.getText().toString();
        String cardexpiry=expiry.getText().toString();
        String cardcvv=cvvcode.getText().toString();
        SharedPreferences sharedPreferences=getSharedPreferences("Secrets",MODE_PRIVATE);
        String currenttoken=sharedPreferences.getString("token","");
        Intent intent=getIntent();
        String poolid=intent.getStringExtra("poolid");
        //Toast.makeText(this, poolid, Toast.LENGTH_SHORT).show();
        //backend send transaction data
         String amount= amountinvest.getText().toString();
        try
        {
             myamt= Integer.parseInt(amount);
        }
        catch (NumberFormatException e)
        {
            // handle the exception
            myamt=0;
        }
        if(myamt==0||cardn.isEmpty()||cardnum.isEmpty()||cardemail.isEmpty()||cardexpiry.isEmpty()||cardcvv.isEmpty())
        {

            Toast.makeText(this, "All fields are mandatory", Toast.LENGTH_SHORT).show();

        }
        else
        {
            Retrofit.Builder builder=new Retrofit.Builder()
                    .baseUrl("https://ashokabackend.herokuapp.com/")//change it afterwards when everthing is hosted
                    .addConverterFactory(GsonConverterFactory.create());
            Retrofit retrofit=builder.build();
            ApiInterface apiInterface=retrofit.create(ApiInterface.class);
            Paymentsendformat sendpaydet=new Paymentsendformat(poolid,myamt);
            Call<Paymentgetformat> call=apiInterface.sendpaymentdet(currenttoken,sendpaydet);
            call.enqueue(new Callback<Paymentgetformat>() {
                @Override
                public void onResponse(Call<Paymentgetformat> call, Response<Paymentgetformat> response) {
                    if(response.isSuccessful())
                    {
                        Toast.makeText(paymentpage.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(paymentpage.this, homepage.class));
                        finish();
                    }
                    else
                    {
                        Toast.makeText(paymentpage.this, "error:"+response.message(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Paymentgetformat> call, Throwable t) {
                    Toast.makeText(paymentpage.this, "Error:"+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }


    }
}
