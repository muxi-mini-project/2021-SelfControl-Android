package com.bignerdranch.android.sc.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bignerdranch.android.sc.R;

public class BackgroundActivity extends AppCompatActivity {

    private ImageButton mBack;
    private ImageView mTheme1,mTheme2,mTheme3,mTheme4,mTheme5,mTheme6;

    @Override
    protected void onCreate(Bundle SavedInstanceState) {

        super.onCreate(SavedInstanceState);

        init();
    }

    private void init(){
        mBack = (ImageButton)findViewById(R.id.background_back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BackgroundActivity.this,SettingPageActivity.class);
            }
        });

        mTheme1 = (ImageView)findViewById(R.id.theme1);
        mTheme2 = (ImageView)findViewById(R.id.theme2);
        mTheme3 = (ImageView)findViewById(R.id.theme3);
        mTheme4 = (ImageView)findViewById(R.id.theme4);
        mTheme5 = (ImageView)findViewById(R.id.theme5);
        mTheme6 = (ImageView)findViewById(R.id.theme6);


    }
}
