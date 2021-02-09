package com.bignerdranch.android.sc.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.bignerdranch.android.sc.R;

public class CourseActivity extends AppCompatActivity {

    private ImageButton mBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.novice_course);
        init();
    }
    private void init(){

        mBack = (ImageButton)findViewById(R.id.course_back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CourseActivity.this,SettingPageActivity.class);
                startActivity(intent);
            }
        });

    }

}
