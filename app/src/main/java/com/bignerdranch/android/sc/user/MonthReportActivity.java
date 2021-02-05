package com.bignerdranch.android.sc.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.bignerdranch.android.sc.R;

public class MonthReportActivity extends AppCompatActivity {
    private ImageButton mBack;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.month_report);
        init();
    }
    private void init(){
        mBack = findViewById(R.id.month_report_back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MonthReportActivity.this,UserActivity.class);
                startActivity(intent);
            }
        });
    }
}


