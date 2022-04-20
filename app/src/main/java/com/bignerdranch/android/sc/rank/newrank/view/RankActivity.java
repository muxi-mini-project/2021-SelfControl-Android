package com.bignerdranch.android.sc.rank.newrank.view;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.Utils;
import com.bignerdranch.android.sc.label.MyFragmentPagerAdapter;
import com.bignerdranch.android.sc.login.User;
import com.bignerdranch.android.sc.net.NetUtil;
import com.bignerdranch.android.sc.settings.view.SettingPageActivity;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.bignerdranch.android.sc.StatusBar.makeStatusBarTransparent;
import static com.bignerdranch.android.sc.login.LoginActivity.token;

public class RankActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Fragment> mList;
    private ViewPager mViewPager;
    private MyFragmentPagerAdapter mMyFragmentPagerAdapter;
    private RankActivity.RankOnPageChangeListener mMyOnPageChangeListener;
    private TextView mMonthRank,mWeekRank,mDate;
    private ImageView mMonthRankIv,mWeekRankIv;
    private ImageButton mBank;
    private ImageButton muser;
    private ImageButton msetting;
    private ConstraintLayout mLayout;
    SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("HH:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rank_background);

        initView();
        //设置状态栏透明
        makeStatusBarTransparent(this);
        //状态栏文字自适应
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    public void initTime(){
        Date date = new Date(System.currentTimeMillis());
        mDate.setText("排行榜更新时间："+mSimpleDateFormat.format(date));
    }

    public void initView(){
        requestBg();

        mMyOnPageChangeListener = new RankActivity.RankOnPageChangeListener();

        mMonthRank = findViewById(R.id.month_rank_tv);
        mMonthRank.setOnClickListener(this);
        mWeekRank = findViewById(R.id.week_rank_tv);
        mWeekRank.setOnClickListener(this);
        mMonthRankIv = findViewById(R.id.month_rank_iv);
        mWeekRankIv = findViewById(R.id.week_rank_iv);
        mBank = findViewById(R.id.rank_back);
        msetting = findViewById(R.id.ranksetting);
        muser = findViewById(R.id.rankkuser);
        mDate = findViewById(R.id.time_bg);
        initTime();

        mBank.setOnClickListener(v -> finish());

        msetting.setOnClickListener(v -> {
            if (Utils.isFastClick()){
                Intent intent = new Intent(RankActivity.this, SettingPageActivity.class);
                startActivity(intent);
            }

        });

        muser.setOnClickListener(v -> {
            if (Utils.isFastClick()){
                Intent intent = new Intent(RankActivity.this, com.bignerdranch.android.sc.user.view.UserActivity.class);
                startActivity(intent);
            }
        });

        mViewPager = findViewById(R.id.rank_viewpager);
        mViewPager.addOnPageChangeListener(mMyOnPageChangeListener);

        mList = new ArrayList<>();
        mList.add(new WeekFragment() );
        mList.add(new MonthFragment() );

        mMyFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), mList);

        mViewPager.setAdapter(mMyFragmentPagerAdapter);
        mViewPager.setCurrentItem(0);

        mLayout = findViewById(R.id.rank_background);
    }
    public class RankOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    mWeekRank.setTextColor(Color.parseColor("#9A9AF3"));
                    mWeekRankIv.setBackgroundResource(R.drawable.rankbar_purple);
                    mMonthRank.setTextColor(Color.parseColor("#A1A1A1"));
                    mMonthRankIv.setBackgroundResource(R.drawable.rankbar_gray);
                    break;
                case 1:
                    mMonthRank.setTextColor(Color.parseColor("#9A9AF3"));
                    mMonthRankIv.setBackgroundResource(R.drawable.rankbar_purple);
                    mWeekRank.setTextColor(Color.parseColor("#A1A1A1"));
                    mWeekRankIv.setBackgroundResource(R.drawable.rankbar_gray);
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
            case R.id.month_rank_tv:
                mViewPager.setCurrentItem(1);
                mMonthRank.setTextColor(Color.parseColor("#9A9AF3"));
                mMonthRankIv.setBackgroundResource(R.drawable.rankbar_purple);
                mWeekRank.setTextColor(Color.parseColor("#A1A1A1"));
                mWeekRankIv.setBackgroundResource(R.drawable.rankbar_gray);
                break;
            case R.id.week_rank_tv:
                mViewPager.setCurrentItem(0);
                mWeekRank.setTextColor(Color.parseColor("#9A9AF3"));
                mWeekRankIv.setBackgroundResource(R.drawable.rankbar_purple);
                mMonthRank.setTextColor(Color.parseColor("#A1A1A1"));
                mMonthRankIv.setBackgroundResource(R.drawable.rankbar_gray);
                break;
        }
    }

    public void requestBg() {
        NetUtil.getInstance().getApi().userInfo(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull User user) {
                        if (user != null) {
                            if (user.getData().getCurrent_backdrop() == 1) {
                                mLayout.setBackgroundResource(R.mipmap.background_default);
                            }
                            if (user.getData().getCurrent_backdrop() == 2) {
                                mLayout.setBackgroundResource(R.mipmap.theme_1);
                            }
                            if (user.getData().getCurrent_backdrop() == 3) {
                                mLayout.setBackgroundResource(R.mipmap.theme_2);
                            }
                            if (user.getData().getCurrent_backdrop() == 4) {
                                mLayout.setBackgroundResource(R.mipmap.theme_3);
                            }
                            if (user.getData().getCurrent_backdrop() == 5) {
                                mLayout.setBackgroundResource(R.mipmap.theme_4);
                            }
                            if (user.getData().getCurrent_backdrop() == 6) {
                                mLayout.setBackgroundResource(R.mipmap.theme_5);
                            }
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onResume(){
        super.onResume();
        requestBg();
    }
}
