package com.example.ashokainvestorend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

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


        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        new ProfileDrawerItem().withName("Utsav Majhi").withEmail("deadsnipper@gmail.com").withIcon(getResources().getDrawable(R.drawable.profile_photo))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();



        //if you want to update the items at a later time it is recommended to keep it in a variable
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Home");
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName("Settings");

        //create the drawer and remember the `Drawer` result object
        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(mtoolbar)
                .withTranslucentStatusBar(false)
                .withActionBarDrawerToggle(true)
                .withSavedInstance(savedInstanceState)
                .withDisplayBelowStatusBar(false)
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        item1, item2

                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D

                        switch (position)
                        {
                            case 1:
                                Toast.makeText(homepage.this, "working", Toast.LENGTH_SHORT).show();
                                break;
                            case 2:
                                Toast.makeText(homepage.this, "working", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return true;
                    }
                })
                .build();





        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("Ashoka Investor");
        result.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);
        mviewpager=(ViewPager) findViewById(R.id.maintabpager);
        mSectionsPagerAdapter=new SectionsPagerAdapter(getSupportFragmentManager());
        mviewpager.setAdapter(mSectionsPagerAdapter);
        mtablayout=(TabLayout) findViewById(R.id.maintabs);
        mtablayout.setupWithViewPager(mviewpager);

    }


}
