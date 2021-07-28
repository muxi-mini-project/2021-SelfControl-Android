package com.bignerdranch.android.sc.settings.Background;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.StatusBar;
import com.bignerdranch.android.sc.login.User;
import com.bignerdranch.android.sc.settings.BackgroundActivity;

public class BackgroundView extends StatusBar implements BackgroundAPI.VP ,View.OnClickListener{

    private ImageButton mBack;
    private ImageView mTheme1, mTheme2, mTheme3, mTheme4, mTheme5, mTheme6;
    private ImageView mChoose1, mChoose2, mChoose3, mChoose4, mChoose5, mChoose6;
    private Integer number;
    private BackgroundP mP = new BackgroundP();
    private BackgroundActivity.myBackground mMyBackground;
    private User mUser;

    private ConstraintLayout mLayout;

    @Override
    protected void onCreate(Bundle SavedInstanceState) {

        super.onCreate(SavedInstanceState);
        setContentView(R.layout.backgroud_theme);
        mP.bindView(this);

        initView();
        makeStatusBarTransparent(this);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    private void initView() {
        mLayout = findViewById(R.id.background_layout);

        mChoose1 = findViewById(R.id.choose1);
        mChoose2 = findViewById(R.id.choose2);
        mChoose3 = findViewById(R.id.choose3);
        mChoose4 = findViewById(R.id.choose4);
        mChoose5 = findViewById(R.id.choose5);
        mChoose6 = findViewById(R.id.choose6);

        mBack = findViewById(R.id.background_back);

        mTheme1 = findViewById(R.id.theme1);
        mTheme2 = findViewById(R.id.theme2);
        mTheme3 = findViewById(R.id.theme3);
        mTheme4 = findViewById(R.id.theme4);
        mTheme5 = findViewById(R.id.theme5);
        mTheme6 = findViewById(R.id.theme6);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.theme1 :
            case R.id.theme2 :
            case R.id.theme3 :
            case R.id.theme4 :
            case R.id.theme5 :
            case R.id.theme6 : onRequest(); break;
            case R.id.back : finish(); break;

        }
    }

    @Override
    public void onRequest() {
        mP.onRequest();
    }

    @Override
    public void successChange() {

    }

    @Override
    public void noCoin() {

    }

    @Override
    public void error() {

    }

}
