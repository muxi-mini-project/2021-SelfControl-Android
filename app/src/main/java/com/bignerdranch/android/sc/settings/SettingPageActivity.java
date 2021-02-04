package com.bignerdranch.android.sc.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.bignerdranch.android.sc.R;

public class SettingPageActivity extends AppCompatActivity {

    private ImageButton mBack, mBackground, mCoin, mCourse, mPrivate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        init();
    }

    private void init(){
        mBack = (ImageButton)findViewById(R.id.setting_back);

        mBackground = (ImageButton)findViewById(R.id.setting_background);
        mBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(SettingPageActivity.this,BackgroundActivity.class);
                startActivity(intent1);

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

        mCourse = (ImageButton)findViewById(R.id.setting_course);
        mCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(SettingPageActivity.this,CourseActivity.class);
                startActivity(intent3);

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
    }
}
