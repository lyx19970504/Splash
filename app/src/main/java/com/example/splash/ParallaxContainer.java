package com.example.splash;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class ParallaxContainer extends FrameLayout implements ViewPager.OnPageChangeListener{

    private ImageView iv_man;
    private List<ParallaxFragment> fragments = new ArrayList<>();
    private ParallaxAdapter adapter;
    public static final String LAYOUT_ID = "layoutId";
    private static final String TAG = "ParallaxContainer";

    public ParallaxContainer(@NonNull Context context) {
        super(context);
    }

    public ParallaxContainer(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    //为fragment设置布局Id
    public void setUp(int[] ids){
        for (int id : ids) {
            ParallaxFragment fragment = new ParallaxFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(LAYOUT_ID,id);
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }

        adapter = new ParallaxAdapter(((AppCompatActivity) getContext()).getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,fragments);
        ViewPager pager = new ViewPager(getContext());
        pager.setId(R.id.parallax_viewpager);
        pager.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        pager.addOnPageChangeListener(this);   //添加事件回调
        pager.setAdapter(adapter);
        addView(pager); //其实这个自定义ViewGroup只有这么一个ViewPager
    }

    public void setIv_man(ImageView iv_man){
        this.iv_man = iv_man;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        ParallaxFragment outFragment = null;
        try {
            outFragment = fragments.get(position -1);
        }catch (Exception e){}

        if(outFragment != null){
            List<View> outViews = outFragment.getViews();
            if(outViews != null) {
                for (View outView : outViews) {
                    ParallaxTag tag = (ParallaxTag) outView.getTag();
                    if (tag == null) {
                        continue;
                    }
                    outView.setTranslationX((getWidth() - positionOffsetPixels) * tag.xOut);
                    outView.setTranslationY((getWidth() - positionOffsetPixels) * tag.yOut);
                }
            }
        }

        ParallaxFragment inFragment = null;
        try {
            inFragment = fragments.get(position);
        }catch (Exception e){}

        if(inFragment != null){
            List<View> inViews = inFragment.getViews();
            if(inViews != null) {
                for (View inView : inViews) {
                    ParallaxTag tag = (ParallaxTag) inView.getTag();
                    if (tag == null) {
                        continue;
                    }
                    inView.setTranslationX(( - positionOffsetPixels) * tag.xIn);
                    inView.setTranslationY( (- positionOffsetPixels) * tag.yIn);
                }
            }
        }
    }

    @Override
    public void onPageSelected(int position) {
        if(position == fragments.size() - 1){
            iv_man.setVisibility(GONE);
        }else {
            iv_man.setVisibility(VISIBLE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        AnimationDrawable drawable = (AnimationDrawable) iv_man.getBackground();
        switch (state){
            case ViewPager.SCROLL_STATE_DRAGGING:   //当ViewPager在滚动时
                drawable.start();
                break;
            case ViewPager.SCROLL_STATE_IDLE:   //当ViewPager停止滚动时
                drawable.stop();
                break;
        }
    }
}
