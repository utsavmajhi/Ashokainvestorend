package com.example.ashokainvestorend;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

class SectionsPagerAdapter extends FragmentPagerAdapter {
    public SectionsPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch(position)
        {
            case 0:
                invested_pools investpool=new invested_pools();
                return investpool;

            case 1:
                all_pools apool=new all_pools();
                return apool;

            default:
                return null;

        }
}

    @Override
    public int getCount() {
        return 2;
    }

    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0:
            { return "Invested Pools";}
            case 1:
            {return  "All Pools";}



            default:
            {return null;}
        }


    }
}
