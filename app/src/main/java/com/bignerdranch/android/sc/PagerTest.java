package com.bignerdranch.android.sc;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class PagerTest extends AppCompatActivity implements View.OnClickListener {

    private List<Fragment> mList;
    private ViewPager mViewPager;
    private MyFragmentPagerAdapter mMyFragmentPagerAdapter;
    private ImageButton mjiankang;
    private ImageButton myundong;
    private ImageButton mxuexi;
    private MyOnPageChangeListener mMyOnPageChangeListener = new MyOnPageChangeListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pager_test);
        mjiankang = (ImageButton) findViewById(R.id.health1_button);
        mjiankang.setBackgroundResource(R.mipmap.nav_jiankang_pressed);
        mjiankang.setOnClickListener(this);
        myundong = (ImageButton) findViewById(R.id.sport1_button);
        myundong.setBackgroundResource(R.mipmap.nav_yundong_normal);
        myundong.setOnClickListener(this);
        mxuexi = (ImageButton) findViewById(R.id.study1_button);
        mxuexi.setBackgroundResource(R.mipmap.nav_xuexi_normal);
        mxuexi.setOnClickListener(this);

        mViewPager = (ViewPager) findViewById(R.id.myViewPager);
        mViewPager.addOnPageChangeListener(mMyOnPageChangeListener);

        mList = new ArrayList<>();
        mList.add(new FragmentHealth() );
        mList.add(new FragmentSport() );
        mList.add(new FragmentStudy() );
        mMyFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), mList);
        mViewPager.setAdapter(mMyFragmentPagerAdapter);
        mViewPager.setCurrentItem(0);

    }

    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {


        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {


        }

        @Override
        public void onPageSelected(int position) {
            switch (position){
                case 0 :
                    mjiankang.setBackgroundResource(R.mipmap.nav_jiankang_pressed);
                    myundong.setBackgroundResource(R.mipmap.nav_yundong_normal);
                    mxuexi.setBackgroundResource(R.mipmap.nav_xuexi_normal);
                    break;
                case 1 :
                    mjiankang.setBackgroundResource(R.mipmap.nav_jiankang_normal);
                    myundong.setBackgroundResource(R.mipmap.nav_yundong_pressed);
                    mxuexi.setBackgroundResource(R.mipmap.nav_xuexi_normal);
                    break;
                case 2 :
                    mjiankang.setBackgroundResource(R.mipmap.nav_jiankang_normal);
                    myundong.setBackgroundResource(R.mipmap.nav_yundong_normal);
                    mxuexi.setBackgroundResource(R.mipmap.nav_xuexi_pressed);
                    break;
            }

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.health1_button:
                //点击"健康"时切换到第一页
                mViewPager.setCurrentItem(0);
                mjiankang.setBackgroundResource(R.mipmap.nav_jiankang_pressed);
                myundong.setBackgroundResource(R.mipmap.nav_yundong_normal);
                mxuexi.setBackgroundResource(R.mipmap.nav_xuexi_normal);
                break;
            case R.id.sport1_button:
                //点击“运动”时切换到第二页
                mViewPager.setCurrentItem(1);
                mjiankang.setBackgroundResource(R.mipmap.nav_jiankang_normal);
                myundong.setBackgroundResource(R.mipmap.nav_yundong_pressed);
                mxuexi.setBackgroundResource(R.mipmap.nav_xuexi_normal);
                break;
            case R.id.study1_button:
                //点击“学习”时切换到第三页
                mViewPager.setCurrentItem(2);
                mjiankang.setBackgroundResource(R.mipmap.nav_jiankang_normal);
                myundong.setBackgroundResource(R.mipmap.nav_yundong_normal);
                mxuexi.setBackgroundResource(R.mipmap.nav_xuexi_pressed);
                break;
        }
    }
}

