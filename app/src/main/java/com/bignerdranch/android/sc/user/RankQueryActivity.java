package com.bignerdranch.android.sc.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.bignerdranch.android.sc.R;

public class RankQueryActivity extends AppCompatActivity {
    private ImageButton mBack;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rank_query);
        init();
    }
    private void init(){
        mBack = findViewById(R.id.rank_query_back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RankQueryActivity.this,UserActivity.class);
                startActivity(intent);
            }
        });
    }
}

