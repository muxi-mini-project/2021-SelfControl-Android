package com.bignerdranch.android.sc.settings.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.StatusBar;
import com.bignerdranch.android.sc.Utils;
import com.bignerdranch.android.sc.login.User;
import com.bignerdranch.android.sc.user.model.GetBackdropAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.bignerdranch.android.sc.login.LoginActivity.token;

public class SettingPageActivity extends StatusBar {

    private ImageButton mBack, mBackground, mCoin, mCourse, mPrivate;
    private ConstraintLayout mThemeLayout,mCoinLayout,mCourseLayout,mPrivateLayout;
    private User.DataDTO mUser;
    private ConstraintLayout mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        init();
    }

    private void init(){

        mLayout = findViewById(R.id.setting_layout);
        requestBg();

        mBack = findViewById(R.id.setting_back);
        mBack.setOnClickListener(v -> finish());
        mBackground = findViewById(R.id.setting_background);
        mBackground.setOnClickListener(v -> {
            if (Utils.isFastClick()){
                Intent intent5 = new Intent(SettingPageActivity.this, BackgroundView.class);
                startActivity(intent5);
            }

        });

        mThemeLayout = findViewById(R.id.theme_layout);
        mThemeLayout.setOnClickListener(v -> {
            if (Utils.isFastClick()){
                Intent intent5 = new Intent(SettingPageActivity.this, BackgroundView.class);
                startActivity(intent5);
            }


        });
        mCoin = findViewById(R.id.setting_coin);
        mCoin.setOnClickListener(v -> {
            if (Utils.isFastClick()){
                Intent intent2 = new Intent(SettingPageActivity.this, CoinFunctionActivity.class);
                startActivity(intent2);
            }


        });

        mCoinLayout = findViewById(R.id.coin_layout);
        mCoinLayout.setOnClickListener(v -> {
            if (Utils.isFastClick()){
                Intent intent6 = new Intent(SettingPageActivity.this,CoinFunctionActivity.class);
                startActivity(intent6);
            }

        });

        mCourse = findViewById(R.id.setting_course);
        mCourse.setOnClickListener(v -> {
            if (Utils.isFastClick()){
                Intent intent3 = new Intent(SettingPageActivity.this, CourseActivity.class);
                startActivity(intent3);

            }

        });

        mCourseLayout = findViewById(R.id.course_layout);
        mCourseLayout.setOnClickListener(v -> {
            if (Utils.isFastClick()){
                Intent intent7 = new Intent(SettingPageActivity.this,CourseActivity.class);
                startActivity(intent7);
            }


        });


        mPrivate = findViewById(R.id.setting_private);
        mPrivate.setOnClickListener(v -> {
            if (Utils.isFastClick()){
                Intent intent4 = new Intent(SettingPageActivity.this, PrivateActivity.class);
                startActivity(intent4);
            }


        });

        mPrivateLayout = findViewById(R.id.private_layout);
        mPrivateLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isFastClick()){
                    Intent intent8 = new Intent(SettingPageActivity.this, PrivateActivity.class);
                    startActivity(intent8);
                }


            }
        });

        makeStatusBarTransparent(this);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }
    public void requestBg() {
        Retrofit.Builder builder1 = new Retrofit.Builder()
                .baseUrl("http://39.99.53.8:2333/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit1 = builder1.build();
        GetBackdropAPI client1 = retrofit1.create(GetBackdropAPI.class);
        Call<User> call1 = client1.getCurrentBackdrop(token);

        call1.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User.DataDTO mUser = new User.DataDTO();
                mUser = response.body().getData();
                if (mUser != null) {
                    if (mUser.getCurrent_backdrop() == 1) {
                        mLayout.setBackgroundResource(R.color.purple);
                    }
                    if (mUser.getCurrent_backdrop() == 2) {
                        mLayout.setBackgroundResource(R.color.theme2);
                    }
                    if (mUser.getCurrent_backdrop() == 3) {
                        mLayout.setBackgroundResource(R.color.theme3);
                    }
                    if (mUser.getCurrent_backdrop() == 4) {
                        mLayout.setBackgroundResource(R.mipmap.theme_31);
                    }
                    if (mUser.getCurrent_backdrop() == 5) {
                        mLayout.setBackgroundResource(R.mipmap.theme_41);
                    }
                    if (mUser.getCurrent_backdrop() == 6) {
                        mLayout.setBackgroundResource(R.mipmap.theme_51);
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        requestBg();
    }
}
