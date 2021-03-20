package com.bignerdranch.android.sc.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.StatusBar;

public class SettingPageActivity extends StatusBar {

    private ImageButton mBack, mBackground, mCoin, mCourse, mPrivate;
    private ConstraintLayout mThemeLayout,mCoinLayout,mCourseLayout,mPrivateLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        init();
    }

    private void init(){
        mBack = (ImageButton)findViewById(R.id.setting_back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mBackground = (ImageButton)findViewById(R.id.setting_background);
        mBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mThemeLayout = findViewById(R.id.theme_layout);
        mThemeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(SettingPageActivity.this,BackgroundActivity.class);
                startActivity(intent5);

            }
        });
        mCoin = (ImageButton)findViewById(R.id.setting_coin);
        mCoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(SettingPageActivity.this,CoinFunctionActivity.class);
                startActivity(intent2);

            }
        });

        mCoinLayout = findViewById(R.id.coin_layout);
        mCoinLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent6 = new Intent(SettingPageActivity.this,CoinFunctionActivity.class);
                startActivity(intent6);

            }
        });

        mCourse = (ImageButton)findViewById(R.id.setting_course);
        mCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(SettingPageActivity.this,CourseActivity.class);
                startActivity(intent3);

            }
        });

        mCourseLayout = findViewById(R.id.course_layout);
        mCourseLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent7 = new Intent(SettingPageActivity.this,CourseActivity.class);
                startActivity(intent7);

            }
        });


        mPrivate = (ImageButton)findViewById(R.id.setting_private);
        mPrivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(SettingPageActivity.this,PrivateActivity.class);
                startActivity(intent4);

            }
        });

        mPrivateLayout = findViewById(R.id.private_layout);
        mPrivateLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent8 = new Intent(SettingPageActivity.this,PrivateActivity.class);
                startActivity(intent8);

            }
        });

        makeStatusBarTransparent(this);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

}
