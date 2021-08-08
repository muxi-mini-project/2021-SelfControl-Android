package com.bignerdranch.android.sc.rank;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bignerdranch.android.sc.GetBackdropAPI;
import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.StatusBar;
import com.bignerdranch.android.sc.Utils;
import com.bignerdranch.android.sc.label.MyFragmentPagerAdapter;
import com.bignerdranch.android.sc.login.User;
import com.bignerdranch.android.sc.settings.SettingPageActivity;
import com.bignerdranch.android.sc.user.UserActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.PUT;

import static com.bignerdranch.android.sc.login.LoginActivity.token;

public class RankBackgroundActivity extends StatusBar implements View.OnClickListener {
    private List<Fragment> mList;
    private ViewPager mViewPager;
    private MyFragmentPagerAdapter mMyFragmentPagerAdapter;
    private RankOnPageChangeListener mMyOnPageChangeListener;
    private TextView mMonthRank,mWeekRank;
    private ImageView mMonthRankIv,mWeekRankIv;
    private ImageButton mBank;
    private ImageButton muser;
    private ImageButton msetting;
    private User mUser;
    private ConstraintLayout mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rank_background);

        mMyOnPageChangeListener = new RankOnPageChangeListener();

        mMonthRank = findViewById(R.id.month_rank_tv);
        mMonthRank.setOnClickListener(this);
        mWeekRank = findViewById(R.id.week_rank_tv);
        mWeekRank.setOnClickListener(this);
        mMonthRankIv = findViewById(R.id.month_rank_iv);
        mWeekRankIv = findViewById(R.id.week_rank_iv);
        mBank = findViewById(R.id.rank_back);
        msetting = findViewById(R.id.ranksetting);
        muser = findViewById(R.id.rankkuser);

        mBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        msetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isFastClick()){
                    Intent intent = new Intent(RankBackgroundActivity.this, SettingPageActivity.class);
                    startActivity(intent);
                }

            }
        });

        muser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isFastClick()){
                    Intent intent = new Intent(RankBackgroundActivity.this, UserActivity.class);
                    startActivity(intent);
                }
            }
        });

        mViewPager = (ViewPager) findViewById(R.id.rank_viewpager);
        mViewPager.addOnPageChangeListener(mMyOnPageChangeListener);

        mList = new ArrayList<>();
        mList.add(new WeekRankFragment() );
        mList.add(new MonthRankFragment() );

        mMyFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), mList);

        mViewPager.setAdapter(mMyFragmentPagerAdapter);
        mViewPager.setCurrentItem(0);

        mLayout = findViewById(R.id.rank_background);
        request();


        //设置状态栏透明
        makeStatusBarTransparent(this);
        //状态栏文字自适应
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
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

    public class Rank{
        private int number;
        private String name;
        private String student_id;
        public Rank(int number,String student_ids,String name){
            this.number = number;
            this.student_id = student_ids;
            this.name = name;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setStudent_id(String student_id) {
            this.student_id = student_id;
        }

        public String getName(){
            return name;
        }
        public int getNumber(){
            return number;
        }
        public String getStudent_id(){
            return student_id;
        }

    }
    private void request() {
        Retrofit.Builder builder1 = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:2333/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit1 = builder1.build();
        GetBackdropAPI client1 = retrofit1.create(GetBackdropAPI.class);
        Log.d("token=","" + token);
        Call<User> call1 = client1.getCurrentBackdrop(token);

        call1.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                mUser = response.body();
                if (mUser != null) {
                    if (mUser.getCurrent_backdrop() == 6) {
                        mLayout.setBackgroundResource(R.color.purple);
                    }
                    if (mUser.getCurrent_backdrop() == 1) {
                        mLayout.setBackgroundResource(R.color.theme2);
                    }
                    if (mUser.getCurrent_backdrop() == 2) {
                        mLayout.setBackgroundResource(R.color.theme3);
                    }
                    if (mUser.getCurrent_backdrop() == 3) {
                        mLayout.setBackgroundResource(R.mipmap.theme_31);
                    }
                    if (mUser.getCurrent_backdrop() == 4) {
                        mLayout.setBackgroundResource(R.mipmap.theme_41);
                    }
                    if (mUser.getCurrent_backdrop() == 5) {
                        mLayout.setBackgroundResource(R.mipmap.theme_51);
                    }
                }else{
                    Toast.makeText(RankBackgroundActivity.this,"失败",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
    public interface BuyRank{
        @PUT("list/month")
        Call<MonthRankFragment.myRank> buyMonthRank(@Header("token")String token, @Body MonthRankFragment.myRank mRank);

        @PUT("list/week")
        Call<MonthRankFragment.myRank> buyWeekRank(@Header("token")String token, @Body WeekRankFragment.myRank mRank);
    }
}
