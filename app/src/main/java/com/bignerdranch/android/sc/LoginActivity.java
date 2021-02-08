package com.bignerdranch.android.sc;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.bignerdranch.android.sc.label.HealthFragment;
import com.bignerdranch.android.sc.label.LabelPagerActivity;

public class LoginActivity extends StatusBar {

    private EditText musername;
    private EditText mpassword;
    private Button mloginbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        mloginbutton=(Button)findViewById(R.id.login_B);
        mloginbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, LabelPagerActivity.class);
                startActivity(intent);
            }
        });

        //设置状态栏透明
        makeStatusBarTransparent(this);
        //状态栏文字自适应
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }
}
