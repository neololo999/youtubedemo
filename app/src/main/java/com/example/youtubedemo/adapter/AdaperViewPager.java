package com.example.youtubedemo.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.youtubedemo.fragment.VideoFragment;

import java.util.ArrayList;
import java.util.List;

public class AdaperViewPager extends FragmentPagerAdapter {


    private final List<VideoFragment> fragmentList;

    public AdaperViewPager(@NonNull FragmentManager fm,List<VideoFragment> fragmentList) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.fragmentList = fragmentList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentList.get(position).getName();
    }
}
