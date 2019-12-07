package com.example.ashokainvestorend;;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import static android.media.CamcorderProfile.get;

public class homepage extends AppCompatActivity {
    private ViewPager mviewpager;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private TabLayout  mtablayout;
    private Toolbar mtoolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        mtoolbar=(Toolbar)findViewById(R.id.main_page_toolbar);

      //NAVIGATION BAR DIRECTLY IMPORTED FROM MIKEPENZ
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
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Profile");
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName("Balance");
        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(3).withName("Transactions History");
        PrimaryDrawerItem item4=new PrimaryDrawerItem().withIdentifier(4).withName("Logout");


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
                        item1, item2,item3,item4

                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D

                        switch (position)
                        {
                            case 1:
                                //pass token for getting user details
                                Intent p1=new Intent(homepage.this,profileactivity.class);
                                //pass the token or required details
                                startActivity(p1);
                                break;
                            case 2:
                                Toast.makeText(homepage.this, "working", Toast.LENGTH_SHORT).show();
                                break;
                            case 3:
                                startActivity(new Intent(homepage.this,alltransactionpage.class));
                                break;
                            case 4:
                                  //  logout();
                                Toast.makeText(homepage.this, "Successfully Logged Out", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(homepage.this,MainActivity.class));
                                break;

                        }
                        return true;
                    }
                })
                .build();
        //NAVIGATION DRAWER ENDS

        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("Ashoka Investor");
        result.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);
        mviewpager=(ViewPager) findViewById(R.id.maintabpager);
        mSectionsPagerAdapter=new SectionsPagerAdapter(getSupportFragmentManager());
        mviewpager.setAdapter(mSectionsPagerAdapter);
        mtablayout=(TabLayout) findViewById(R.id.maintabs);
        mtablayout.setupWithViewPager(mviewpager);

    }

//CLICKING ACTIVITY FOR ALLPOOLS

    //CLICKING ACTIVITY ENDS FOR ALL POOLS

}
