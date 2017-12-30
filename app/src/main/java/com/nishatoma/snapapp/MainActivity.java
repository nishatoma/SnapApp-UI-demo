package com.nishatoma.snapapp;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.nishatoma.snapapp.adapter.MainPagerAdapter;
import com.nishatoma.snapapp.view.SnapTabsView;

public class MainActivity extends AppCompatActivity
{

    //fragment positions
    private static final int FIRST_PAGE = 0;
    private static final int CAMERA_PAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final View background = findViewById(R.id.am_background_view);
        ViewPager mViewPager = findViewById(R.id.am_view_pager);

        //We created our own adapter!
        MainPagerAdapter mMainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mMainPagerAdapter);

        //Check if we have a reference
        SnapTabsView mSnapTabsView = findViewById(R.id.am_snap_tabs);
        mSnapTabsView.setUpWithViewPager(mViewPager);

        mViewPager.setCurrentItem(1);

        //Colors
        final int colorBlue = ContextCompat.getColor(this, R.color.light_blue);
        final int colorPurple = ContextCompat.getColor(this, R.color.light_purple);


        //Every time we scroll horizontally, this listener will get t r i g g e r e d
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {
                if (position == FIRST_PAGE)
                {
                    setViewPagerStyle(background, colorBlue, 1 - positionOffset);
                } else if (position == CAMERA_PAGE)
                {
                    setViewPagerStyle(background, colorPurple, positionOffset);
                }
            }

            @Override
            public void onPageSelected(int position)
            {

            }

            @Override
            public void onPageScrollStateChanged(int state)
            {

            }
        });

    }

    public void setViewPagerStyle(View background, int backGroundColor, float alpha)
    {
        background.setBackgroundColor(backGroundColor);
        background.setAlpha(alpha);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        ViewPager mViewPager = findViewById(R.id.am_view_pager);
        mViewPager.setCurrentItem(1);
    }
}
