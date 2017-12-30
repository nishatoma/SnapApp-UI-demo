package com.nishatoma.snapapp.view;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.nishatoma.snapapp.R;

public class SnapTabsView extends FrameLayout implements ViewPager.OnPageChangeListener
{

    private ImageView mCenterImage;
    private ImageView mStartImage;
    private ImageView mEndImage;
    private ImageView mBottomImage;
    private View mIndicator;

    //ARGB Evaluator for fading colors
    private ArgbEvaluator mArgbEvaluator;
    private int mCenterColor;
    private int mSideColor;

    //For calculations
    private int mEndViewsTranslationX;
    private int mIndicatorTranslation;
    private int mCenterTranslationY;

    public SnapTabsView(@NonNull Context context)
    {
        this(context, null);
    }

    public SnapTabsView(@NonNull Context context,
                        @Nullable AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public SnapTabsView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    //Set the view pager
    public void setUpWithViewPager(final ViewPager viewPager)
    {
        viewPager.addOnPageChangeListener(this);

        mStartImage.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (viewPager.getCurrentItem() != 0)
                {
                    viewPager.setCurrentItem(0);
                }
            }
        });

        mEndImage.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (viewPager.getCurrentItem() != 2)
                {
                    viewPager.setCurrentItem(2);
                }
            }
        });
    }

    private void init()
    {
        //Now that we have the layout, inflate this view!
        LayoutInflater.from(getContext()).inflate(R.layout.view_snap_tabs, this, true);

        //Setup the images
        mCenterImage = findViewById(R.id.vst_center_image);
        mBottomImage = findViewById(R.id.vst_bottom_image);
        mStartImage = findViewById(R.id.vst_start_image);
        mEndImage = findViewById(R.id.vst_end_image);

        //Setup the indicator
        mIndicator = findViewById(R.id.vst_indicator_image);

        //Set the start color and end color!
        mCenterColor = ContextCompat.getColor(getContext(), R.color.white);
        mSideColor = ContextCompat.getColor(getContext(), R.color.dark_grey);

        //Initiate the ARGB Evaluator
        mArgbEvaluator = new ArgbEvaluator();

        mIndicatorTranslation = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80, getResources().getDisplayMetrics());

        mBottomImage.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
        {
            @Override
            public void onGlobalLayout()
            {
                mEndViewsTranslationX = (int) ((-mStartImage.getX() + mBottomImage.getX()) - mIndicatorTranslation);
                mBottomImage.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                mCenterTranslationY = getHeight() - mBottomImage.getBottom();
            }
        });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
    {
        if (position == 0)
        {
            setColor(1 - positionOffset);
            moveViews(1 - positionOffset);

            moveAndScaleCenter(1 - positionOffset);

            mIndicator.setTranslationX((positionOffset - 1) * mIndicatorTranslation);
        } else if (position == 1)
        {
            setColor(positionOffset);
            moveViews(positionOffset);

            moveAndScaleCenter(positionOffset);

            mIndicator.setTranslationX(positionOffset * mIndicatorTranslation);
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

    private void moveAndScaleCenter(float fractionFromCenter)
    {
        float scale = 0.7f + ((1 - fractionFromCenter) * 0.3f);

        mCenterImage.setScaleX(scale);
        mCenterImage.setScaleY(scale);

        int translation = (int) (fractionFromCenter * mCenterTranslationY);

        mCenterImage.setTranslationY(translation);
        mBottomImage.setTranslationY(translation);

        mBottomImage.setAlpha(1 - fractionFromCenter);
    }

    private void moveViews(float fractionFromCenter)
    {
        mStartImage.setTranslationX(fractionFromCenter * mEndViewsTranslationX);
        mEndImage.setTranslationX(-fractionFromCenter * mEndViewsTranslationX);

        mIndicator.setAlpha(fractionFromCenter);
        mIndicator.setScaleX(fractionFromCenter);
    }

    private void setColor(float fractionFromCenter)
    {

        int color = (int) mArgbEvaluator.evaluate(fractionFromCenter, mCenterColor, mSideColor);

        mCenterImage.setColorFilter(color);
        mStartImage.setColorFilter(color);
        mEndImage.setColorFilter(color);
    }

}
