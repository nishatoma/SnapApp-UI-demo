package com.nishatoma.snapapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nishatoma.snapapp.R;

public class StoryFragment extends BaseFragment
{

    public static StoryFragment create()
    {
        return new StoryFragment();
    }

    @Override
    public int getLayoutResId()
    {
        return R.layout.fragment_story;
    }

    @Override
    public void inOnCreateView(View root, @Nullable ViewGroup container,
                               @Nullable Bundle savedInstanceState)
    {

    }

    public StoryFragment()
    {
    }
}
