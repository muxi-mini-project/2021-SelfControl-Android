package com.bignerdranch.android.sc.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.StatusBar;

public class UserActivity extends StatusBar {

    private ConstraintLayout mJinbi,mYuebao,mPaihangbang;
    private ImageButton mJinbiButton,mYuebaoButton,mPaihangbangButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);
        init();

        makeStatusBarTransparent(this);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }
    private void init(){
        mJinbi = findViewById(R.id.user_jinbi);
        mJinbi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(UserActivity.this,CoinQueryActivity.class);
                startActivity(intent1);
            }
        });
        mJinbiButton = findViewById(R.id.user_jinbi_button);
        mJinbiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(UserActivity.this,CoinQueryActivity.class);
                startActivity(intent4);
            }
        });

        mYuebao = findViewById(R.id.user_yuebao);
        mYuebao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(UserActivity.this,MonthReportActivity.class);
                startActivity(intent2);
            }
        });
        mYuebaoButton = findViewById(R.id.user_jinbi_button);
        mYuebaoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(UserActivity.this,MonthReportActivity.class);
                startActivity(intent4);
            }
        });

        mPaihangbang = findViewById(R.id.user_paihangbang);
        mPaihangbang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(UserActivity.this,RankQueryActivity.class);
                startActivity(intent1);
            }
        });
        mPaihangbangButton = findViewById(R.id.user_jinbi_button);
        mPaihangbangButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(UserActivity.this,RankQueryActivity.class);
                startActivity(intent4);
            }
        });
    }
}
