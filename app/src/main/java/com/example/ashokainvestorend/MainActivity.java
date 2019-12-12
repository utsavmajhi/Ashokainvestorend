package com.example.ashokainvestorend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ashokainvestorend.loginmodels.Getlogindataformat;
import com.example.ashokainvestorend.loginmodels.Sendlogindataformat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView loginphone;
    private TextView loginpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginpass=findViewById(R.id.lgpass);
        loginphone=findViewById(R.id.lgphone);

        SharedPreferences sharedPreferences2=getSharedPreferences("Secrets",MODE_PRIVATE);
        String tk=sharedPreferences2.getString("token","");
        if(tk!="")
        {
            startActivity(new Intent(this,homepage.class));
            finish();
        }
    }

    public void click(View view) {

        String ph2=loginphone.getText().toString().trim();
        String pass2=loginpass.getText().toString().trim();
        if(ph2.isEmpty()||pass2.isEmpty())
        {
            Toast.makeText(this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
        }
        else
        {


            Toast.makeText(this, "Signing In Please Wait!", Toast.LENGTH_SHORT).show();
            //BACKEND JSON PART FOR LOGIN
            Retrofit.Builder builder=new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:5000/")//change it afterwards when everthing is hosted
                    .addConverterFactory(GsonConverterFactory.create());
            Retrofit retrofit=builder.build();
            ApiInterface apiInterface=retrofit.create(ApiInterface.class);
            Sendlogindataformat lgcred=new Sendlogindataformat(ph2,pass2);
            Call<Getlogindataformat> call=apiInterface.getlogindata(lgcred);
            call.enqueue(new Callback<Getlogindataformat>() {
                @Override
                public void onResponse(Call<Getlogindataformat> call, Response<Getlogindataformat> response) {
                    if(response.isSuccessful())
                    {
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

                        //if login successful
                        Toast.makeText(MainActivity.this, "Successfully Logged In", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this,homepage.class));


                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Error:"+response.code(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Getlogindataformat> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Error:"+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });


            //BACKEND ENDS

            /*Intent i1=new Intent(this,homepage.class);
            i1.putExtra("phone",ph2);
            */
        }
        //pass the required token or name

    }

    public void clickregister(View view) {

        startActivity(new Intent(this,registeringuser.class));

    }
}
