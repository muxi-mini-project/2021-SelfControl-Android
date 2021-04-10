package com.bignerdranch.android.sc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SeeUserLabelActivity extends StatusBar {

    private TextView userName;
    private String name;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.see_user_label);

        makeStatusBarTransparent(this);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        Intent intent = getIntent();
        name = intent.getStringExtra("data1");
        id = intent.getStringExtra("data");

        userName = findViewById(R.id.user_name);
        userName.setText(name);





    }
}
