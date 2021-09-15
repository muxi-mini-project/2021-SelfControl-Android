package com.bignerdranch.android.sc.clockpage.view.flower;

import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class FlowerFragmentPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<FlowerFragment> fragments;
    private FlowerFragment mCurrentFragment;
    private FragmentManager fm;

    public FlowerFragmentPagerAdapter(FragmentManager fm, ArrayList<FlowerFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

}
