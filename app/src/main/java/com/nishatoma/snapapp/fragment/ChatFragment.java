package com.nishatoma.snapapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import com.nishatoma.snapapp.R;

public class ChatFragment extends BaseFragment
{


    public static ChatFragment create()
    {
        return new ChatFragment();
    }

    @Override
    public int getLayoutResId()
    {
        return R.layout.fragment_chat;
    }

    @Override
    public void inOnCreateView(View root, @Nullable ViewGroup container,
                               @Nullable Bundle savedInstanceState)
    {

    }
}
