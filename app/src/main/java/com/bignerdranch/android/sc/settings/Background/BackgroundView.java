package com.bignerdranch.android.sc.settings.Background;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.StatusBar;
import com.bignerdranch.android.sc.login.User;
import com.bignerdranch.android.sc.settings.BackgroundActivity;

public class BackgroundView extends StatusBar implements BackgroundAPI.VP, View.OnClickListener {

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
        switch (v.getId()) {
            case R.id.theme1:
                change1();
                break;
            case R.id.theme2:
                haveRequest(1);
                break;
            case R.id.theme3:
                haveRequest(2);
                break;
            case R.id.theme4:
                haveRequest(3);
                break;
            case R.id.theme5:
                haveRequest(4);
                break;
            case R.id.theme6:
                haveRequest(5);
                break;
            case R.id.back:
                finish();
                break;

        }
    }

    @Override
    public void haveRequest(int click) {
        mP.haveRequest(click);
    }

    //用于改变选择图标
    public void change1(){
        mChoose1.setBackgroundResource(R.mipmap.choose);
        mChoose1.setAlpha(1f);
        mLayout.setBackgroundResource(R.color.purple);

        mChoose2.setBackgroundResource(R.mipmap.choose);
        mChoose2.setAlpha(0f);
        mChoose3.setBackgroundResource(R.mipmap.choose);
        mChoose3.setAlpha(0f);
        mChoose4.setBackgroundResource(R.mipmap.choose);
        mChoose4.setAlpha(0f);
        mChoose5.setBackgroundResource(R.mipmap.choose);
        mChoose5.setAlpha(0f);
        mChoose6.setBackgroundResource(R.mipmap.choose);
        mChoose6.setAlpha(0f);
    }
    @Override
    public void successChange(int num) {
        switch (num) {
            case 1: {
                mChoose2.setBackgroundResource(R.mipmap.choose);
                mChoose2.setAlpha(1f);
                mLayout.setBackgroundResource(R.color.theme2);

                mChoose1.setBackgroundResource(R.mipmap.choose);
                mChoose1.setAlpha(0f);
                mChoose3.setBackgroundResource(R.mipmap.choose);
                mChoose3.setAlpha(0f);
                mChoose4.setBackgroundResource(R.mipmap.choose);
                mChoose4.setAlpha(0f);
                mChoose5.setBackgroundResource(R.mipmap.choose);
                mChoose5.setAlpha(0f);
                mChoose6.setBackgroundResource(R.mipmap.choose);
                mChoose6.setAlpha(0f);
            };break;
            case 2:{
                mChoose3.setBackgroundResource(R.mipmap.choose);
                mChoose3.setAlpha(1f);
                mLayout.setBackgroundResource(R.color.theme3);

                mChoose1.setBackgroundResource(R.mipmap.choose);
                mChoose1.setAlpha(0f);
                mChoose2.setBackgroundResource(R.mipmap.choose);
                mChoose2.setAlpha(0f);
                mChoose4.setBackgroundResource(R.mipmap.choose);
                mChoose4.setAlpha(0f);
                mChoose5.setBackgroundResource(R.mipmap.choose);
                mChoose5.setAlpha(0f);
                mChoose6.setBackgroundResource(R.mipmap.choose);
                mChoose6.setAlpha(0f);
            };break;
            case 3:{
                mChoose4.setBackgroundResource(R.mipmap.choose);
                mChoose4.setAlpha(1f);
                mLayout.setBackgroundResource(R.mipmap.theme_31);

                mChoose1.setBackgroundResource(R.mipmap.choose);
                mChoose1.setAlpha(0f);
                mChoose3.setBackgroundResource(R.mipmap.choose);
                mChoose3.setAlpha(0f);
                mChoose2.setBackgroundResource(R.mipmap.choose);
                mChoose2.setAlpha(0f);
                mChoose5.setBackgroundResource(R.mipmap.choose);
                mChoose5.setAlpha(0f);
                mChoose6.setBackgroundResource(R.mipmap.choose);
                mChoose6.setAlpha(0f);
            };break;
            case 4:{
                mChoose5.setBackgroundResource(R.mipmap.choose);
                mChoose5.setAlpha(1f);
                mLayout.setBackgroundResource(R.mipmap.theme_41);

                mChoose1.setBackgroundResource(R.mipmap.choose);
                mChoose1.setAlpha(0f);
                mChoose3.setBackgroundResource(R.mipmap.choose);
                mChoose3.setAlpha(0f);
                mChoose4.setBackgroundResource(R.mipmap.choose);
                mChoose4.setAlpha(0f);
                mChoose2.setBackgroundResource(R.mipmap.choose);
                mChoose2.setAlpha(0f);
                mChoose6.setBackgroundResource(R.mipmap.choose);
                mChoose6.setAlpha(0f);
            };break;
            case 5:{
                mChoose6.setBackgroundResource(R.mipmap.choose);
                mChoose6.setAlpha(1f);
                mLayout.setBackgroundResource(R.mipmap.theme_51);

                mChoose1.setBackgroundResource(R.mipmap.choose);
                mChoose1.setAlpha(0f);
                mChoose3.setBackgroundResource(R.mipmap.choose);
                mChoose3.setAlpha(0f);
                mChoose4.setBackgroundResource(R.mipmap.choose);
                mChoose4.setAlpha(0f);
                mChoose5.setBackgroundResource(R.mipmap.choose);
                mChoose5.setAlpha(0f);
                mChoose2.setBackgroundResource(R.mipmap.choose);
                mChoose2.setAlpha(0f);
            };break;
        }
    }

    //显示对话框
    @Override
    public void buyDialog(int click) {
        final AlertDialog.Builder normalDialog = new AlertDialog.Builder(BackgroundView.this);
        normalDialog.setTitle("兑换背景");
        normalDialog.setMessage("确定用金币兑换背景吗?");
        normalDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                buyRequest(click);
            }
        });
        normalDialog.setNegativeButton("关闭", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        // 显示
        normalDialog.show();
    }

    @Override
    public void buyRequest(int click) {
        mP.buyRequest(click);
    }

    @Override
    public void noCoin() {

    }

    @Override
    public void error() {

    }

}
