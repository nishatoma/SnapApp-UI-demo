package com.nishatoma.snapapp.fragment;


import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public abstract class BaseFragment extends Fragment
{

    private View mRoot;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState)
    {
        //Everytime we create a Fragment, it will inflate whatever layout we give it.
        mRoot = inflater.inflate(getLayoutResId(), container, false);
        inOnCreateView(mRoot, container, savedInstanceState);
        return mRoot;
    }

    @LayoutRes
    public abstract int getLayoutResId();

    public abstract void inOnCreateView(View root, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState);
}
