package com.bignerdranch.android.sc.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.bignerdranch.android.sc.R;

public class PrivateActivity extends AppCompatActivity {

    private ImageButton mBack;
    private Button mTrue, mFalse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        init();
    }

    private void init(){
        mBack = (ImageButton)findViewById(R.id.private_back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrivateActivity.this,SettingPageActivity.class);
                startActivity(intent);
            }
        });
        mTrue = (Button)findViewById(R.id.ture_button);
        mFalse = (Button)findViewById(R.id.false_button);
    }
}
