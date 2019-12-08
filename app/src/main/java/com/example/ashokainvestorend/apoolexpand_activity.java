package com.example.ashokainvestorend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

public class apoolexpand_activity extends AppCompatActivity {

    private TextView report;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apoolexpand_activity);
        report=findViewById(R.id.apreporttxt);
        toolbar=findViewById(R.id.apooltoolbar);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //for vertical scrolling method
        report.setMovementMethod(new ScrollingMovementMethod());
    }


    //after clicking investment button
    public void investclick(View view) {

        //redirect to pay page

    }
}
