package com.nishatoma.snapapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.nishatoma.snapapp.fragment.ChatFragment;
import com.nishatoma.snapapp.fragment.EmptyFragment;
import com.nishatoma.snapapp.fragment.StoryFragment;

public class MainPagerAdapter extends FragmentPagerAdapter
{

    private static final int NUM_FRAGMENT = 3;

    public MainPagerAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int position)
    {
        switch (position)
        {
            case 0:
                return ChatFragment.create();
            case 1:
                return EmptyFragment.create();
            case 2:
                return StoryFragment.create();
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        switch (position)
        {
            case 0:
                return "Chat";
            case 1:
                return "Search";
            case 2:
                return "Stories";
        }
        return super.getPageTitle(position);
    }

    @Override
    public int getCount()
    {
        return NUM_FRAGMENT;
    }
}
