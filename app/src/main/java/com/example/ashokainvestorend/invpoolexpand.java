package com.example.ashokainvestorend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class invpoolexpand extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invpoolexpand);

        toolbar=findViewById(R.id.invpooltoolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void investclickalready(View view) {
    }


    //More details clicking activity
    public void fulltrandetailsclick(View view)
    {
        startActivity(new Intent(invpoolexpand.this,particularpalltransa.class));



    }

    //more details clicking activity ends
}
