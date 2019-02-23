package com.example.boris.mamafoodjob.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.boris.mamafoodjob.Fragments.FirstFragment;
import com.example.boris.mamafoodjob.Fragments.ForthFragment;
import com.example.boris.mamafoodjob.Fragments.SecondFragment;
import com.example.boris.mamafoodjob.Fragments.ThirdFragment;
import com.example.boris.mamafoodjob.Preview;

public class AdapterPreview extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 4;
    private Preview preview;

    public AdapterPreview(FragmentManager fragmentManager, Preview preview) {
        super(fragmentManager);
        this.preview = preview;
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return FirstFragment.newInstance(preview);
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return SecondFragment.newInstance(preview);
            case 2: // Fragment # 1 - This will show SecondFragment
                return ThirdFragment.newInstance(preview);
            case 3: // Fragment # 1 - This will show SecondFragment
                return ForthFragment.newInstance(preview);
            default:
                return null;
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }

}