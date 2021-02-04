package com.bignerdranch.android.sc.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.bignerdranch.android.sc.R;

public class UserActivity extends AppCompatActivity {

    private ImageButton mJinbi,mYuebao,mPaihangbang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);
        init();
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
        mYuebao = findViewById(R.id.user_yuebao);
        mPaihangbang = findViewById(R.id.user_paihangbang);
    }
}
