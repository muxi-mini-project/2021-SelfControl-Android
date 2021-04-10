package com.bignerdranch.android.sc.label;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.StatusBar;
import com.bignerdranch.android.sc.Utils;

import java.util.ArrayList;
import java.util.List;

public class LabelPagerActivity extends StatusBar implements View.OnClickListener {

    private List<Fragment> mList;
    private ViewPager mViewPager;
    private ImageButton mjiankang;
    private ImageButton myundong;
    private ImageButton mxuexi;
    private MyFragmentPagerAdapter mMyFragmentPagerAdapter;
    private MyOnPageChangeListener mMyOnPageChangeListener;
    private ImageButton mfeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pager_label);

        mMyOnPageChangeListener = new MyOnPageChangeListener();

        mjiankang = (ImageButton) findViewById(R.id.health_button);
        mjiankang.setBackgroundResource(R.mipmap.nav_jiankang_pressed);
        mjiankang.setOnClickListener(this);

        myundong = (ImageButton) findViewById(R.id.sport_button);
        myundong.setBackgroundResource(R.mipmap.nav_yundong_normal);
        myundong.setOnClickListener(this);

        mxuexi = (ImageButton) findViewById(R.id.study_button);
        mxuexi.setBackgroundResource(R.mipmap.nav_xuexi_normal);
        mxuexi.setOnClickListener(this);

        mViewPager = (ViewPager) findViewById(R.id.myViewPager);
        mViewPager.addOnPageChangeListener(mMyOnPageChangeListener);

        mList = new ArrayList<>();
        mList.add(new HealthFragment());
        mList.add(new SportFragment());
        mList.add(new StudyFragment());

        mfeedback = findViewById(R.id.feedback);
        mfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isFastClick()){
                    Toast.makeText(LabelPagerActivity.this,"这个功能还未开发完成，敬请期待哦",Toast.LENGTH_SHORT).show();
                }
            }
        });

        mMyFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), mList);

        mViewPager.setAdapter(mMyFragmentPagerAdapter);
        mViewPager.setCurrentItem(0);
        mViewPager.setOffscreenPageLimit(2);

        //设置状态栏透明
        makeStatusBarTransparent(this);
        //状态栏文字自适应
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    mjiankang.setBackgroundResource(R.mipmap.nav_jiankang_pressed);
                    myundong.setBackgroundResource(R.mipmap.nav_yundong_normal);
                    mxuexi.setBackgroundResource(R.mipmap.nav_xuexi_normal);
                    break;
                case 1:
                    mjiankang.setBackgroundResource(R.mipmap.nav_jiankang_normal);
                    myundong.setBackgroundResource(R.mipmap.nav_yundong_pressed);
                    mxuexi.setBackgroundResource(R.mipmap.nav_xuexi_normal);
                    break;
                case 2:
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
            case R.id.health_button:
                //点击"健康"时切换到第一页
                mViewPager.setCurrentItem(0);
                mjiankang.setBackgroundResource(R.mipmap.nav_jiankang_pressed);
                myundong.setBackgroundResource(R.mipmap.nav_yundong_normal);
                mxuexi.setBackgroundResource(R.mipmap.nav_xuexi_normal);
                break;
            case R.id.sport_button:
                //点击“运动”时切换到第二页
                mViewPager.setCurrentItem(1);
                mjiankang.setBackgroundResource(R.mipmap.nav_jiankang_normal);
                myundong.setBackgroundResource(R.mipmap.nav_yundong_pressed);
                mxuexi.setBackgroundResource(R.mipmap.nav_xuexi_normal);
                break;
            case R.id.study_button:
                //点击“学习”时切换到第三页
                mViewPager.setCurrentItem(2);
                mjiankang.setBackgroundResource(R.mipmap.nav_jiankang_normal);
                myundong.setBackgroundResource(R.mipmap.nav_yundong_normal);
                mxuexi.setBackgroundResource(R.mipmap.nav_xuexi_pressed);
                break;
        }
    }


}

