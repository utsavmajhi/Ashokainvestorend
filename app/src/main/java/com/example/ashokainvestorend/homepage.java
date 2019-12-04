package com.example.ashokainvestorend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class homepage extends AppCompatActivity  {
    private ViewPager mviewpager;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private TabLayout  mtablayout;
    private Toolbar mtoolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        mtoolbar=(Toolbar)findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("Ashoka Investor");
        mviewpager=(ViewPager) findViewById(R.id.maintabpager);
        mSectionsPagerAdapter=new SectionsPagerAdapter(getSupportFragmentManager());
        mviewpager.setAdapter(mSectionsPagerAdapter);
        mtablayout=(TabLayout) findViewById(R.id.maintabs);
        mtablayout.setupWithViewPager(mviewpager);

    }


}
