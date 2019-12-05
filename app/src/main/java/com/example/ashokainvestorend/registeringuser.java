package com.example.ashokainvestorend;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class registeringuser extends AppCompatActivity {


    private TextView rname;
    private TextView rpass;
    private TextView raadhar;
    private TextView rphone;
    private TextView rdob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeringuser);
        rname=findViewById(R.id.regisname);
        rpass=findViewById(R.id.regispass);
        raadhar=findViewById(R.id.regisaadhar);
        rphone=findViewById(R.id.regisphone);
        rdob=findViewById(R.id.regisdob);
    }



    //for registeration activity
    public void regisclick(View view) {

        String n1=rname.getText().toString();
        String p1=rpass.getText().toString();
        String aa1=raadhar.getText().toString();
        String ph1=rphone.getText().toString();
        String dob1=rdob.getText().toString();
        if(n1.isEmpty()||p1.isEmpty()||aa1.isEmpty()||ph1.isEmpty()||dob1.isEmpty())
        {
            Toast.makeText(this, "All fields are Mandatory", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Registering Please Wait!", Toast.LENGTH_SHORT).show();
            reisterbackend(n1,p1,aa1,ph1,dob1);
        }
    }


    //BACKEND REGISTRATION
    private void reisterbackend(String n1, String p1, String aa1, String ph1, String dob1) {

        //JSON POST METHOD USING RETROFIT

    }
}
