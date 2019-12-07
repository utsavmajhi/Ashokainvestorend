package com.example.ashokainvestorend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView loginphone;
    private TextView loginpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginpass=findViewById(R.id.lgpass);
        loginphone=findViewById(R.id.lgphone);
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


            //BACKEND ENDS

            Intent i1=new Intent(this,homepage.class);
            i1.putExtra("phone",ph2);



            startActivity(new Intent(this,homepage.class));
        }
        //pass the required token or name

    }

    public void clickregister(View view) {

        startActivity(new Intent(this,registeringuser.class));

    }
}
